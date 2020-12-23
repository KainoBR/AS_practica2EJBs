/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.Log;
import EJBs.StatefulBeans;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showAllStatefulBeans extends FrontCommand {

    static String MSG = "showAllStatefulBeans";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            HttpSession session = request.getSession();

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            StatefulBeans statefulBean;
            statefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/StatefulBeans");

            session.setAttribute("statefulBean", statefulBean);
            
            forward("/showAllStatefullBeans.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            Logger.getLogger(addBookToBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
