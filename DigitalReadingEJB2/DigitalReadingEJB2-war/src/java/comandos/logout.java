/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.CartLocal;
import EJBs.Log;
import EJBs.Stats;
import EJBs.UserRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class logout extends FrontCommand {

    static String MSG = "logout";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            HttpSession session = request.getSession();

            UserRemote user = (UserRemote) session.getAttribute("user");
            
            CartLocal cart = (CartLocal) session.getAttribute("carrito");

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);
            
            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Stats");

            stats.removeUser(user.toString());
            
            session.invalidate();
            
            //@remove the 2 stateful bean
            
            //@remove
            cart.remove();
            
            //@remove
            user.remove();

            forward("/index.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
