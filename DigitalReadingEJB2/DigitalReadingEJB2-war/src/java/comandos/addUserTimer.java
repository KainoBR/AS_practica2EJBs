/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.Log;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class addUserTimer extends FrontCommand {

    static String MSG = "addUserTimer";

    @Override
    public void process() {

        try {
            Log timer = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            timer.addLogs(MSG);

            int miliseconds = Integer.parseInt(request.getParameter("miliseconds"));
            boolean loop = ("on").equals(request.getParameter("loop"));
            
            timer.setLoop(loop);
            timer.setMiliseconds(miliseconds*1000);
           
            timer.setUserTimer();

            forward("/showSystemLog.jsp");
        } catch (Exception ex) {
            Logger.getLogger(addUserTimer.class.getName()).log(Level.SEVERE, null, ex);
            forward("/commandErrorPage.jsp");
        }

    }

}
