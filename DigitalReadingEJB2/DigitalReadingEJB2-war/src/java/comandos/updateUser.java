/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.Log;
import EJBs.StatefulBeans;
import EJBs.Stats;
import EJBs.User;
import EJBs.UserRemote;
import EJBs.UserRemote;
import User.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class updateUser extends FrontCommand {

    static String MSG = "updateUser";

    @Override
    public void process() {

        System.out.println(MSG);
        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            //@Singleton STATS
            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Stats");

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/StatefulBeans");

            HttpSession session = request.getSession();
            UserRemote newUser = updateUserForm();

            UserRemote user = (UserRemote) session.getAttribute("user");

            newUser.setId(user.getId());

            if (ModelUser.update(newUser)) {
                session.setAttribute("user", ModelUser.getUser(newUser.getId()));

                stats.removeUser(user.toString());
                stats.addUser(newUser.toString());
                
                allStatefulBean.updateUser(user.toString(), newUser.toString());

            }

            forward("/showMyProfile.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

    private UserRemote updateUserForm() {
        String name = request.getParameter("username");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new User(name, lastname, email, password, 0);
    }
}
