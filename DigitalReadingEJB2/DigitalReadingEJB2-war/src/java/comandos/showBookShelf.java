/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Biblioteca.ModelBiblioteca;
import Controller.FrontCommand;
import EJBs.Log;
import EJBs.UserRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showBookShelf extends FrontCommand {

    static String MSG = "showBookShlef";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = (HttpSession) request.getSession();

            UserRemote usuario = (UserRemote) session.getAttribute("user");

            int userID = usuario.getId();

            session.setAttribute("biblioteca", ModelBiblioteca.getBookShelf(userID));

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBookShelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
