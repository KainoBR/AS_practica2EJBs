/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Biblioteca.ModelBiblioteca;
import Comentarios.ModelComment;
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
public class showProfile extends FrontCommand {

    static String MSG = "showProfile";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = (HttpSession) request.getSession();

            UserRemote user = (UserRemote) session.getAttribute("user");
            int id = user.getId();

            int numComments = ModelComment.getAllCommentsOfThisUser(id).size();
            int likes = 0;
            int numBooks = ModelBiblioteca.getBookShelf(id).size();

            session.setAttribute("numComments", numComments);
            session.setAttribute("numBooks", numBooks);
            session.setAttribute("numLikes", likes);

            forward("/showMyProfile.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showProfile.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
