/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.Log;
import java.util.ArrayList;
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
public class deleteSystemLogs extends FrontCommand {

    static String MSG = "deleteSystemLogs";

    @Override
    public void process() {

        try {
            HttpSession session = (HttpSession) request.getSession();

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            
            log.clearLogs();

            List<String> logs = log.getLogs();
            
            session.setAttribute("logs", logs);

            forward("/showSystemLog.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(deleteSystemLogs.class.getName()).log(Level.SEVERE, null, ex);
            forward("/commandErrorPage.jsp");
        }

    }
    
}
