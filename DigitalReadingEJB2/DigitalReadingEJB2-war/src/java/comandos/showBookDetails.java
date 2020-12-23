/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Book.ModelBook;
import Comentarios.ModelComment;
import Controller.FrontCommand;
import EJBs.Book;
import EJBs.Log;
import EJBs.UserRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showBookDetails extends FrontCommand {

    static String MSG = "showBookDetails";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);
            
            HttpSession session = (HttpSession) request.getSession();

            int bookID = Integer.parseInt(request.getParameter("bookID"));
            UserRemote user = (UserRemote) session.getAttribute("user");

            Book book = ModelBook.getBook(bookID);

            book.setComentarios(ModelComment.getCommentsFromThisBook(bookID));

            session.setAttribute("libro", book);

            if (book != null) {
                forward("/showBookDetails.jsp");
            } else {
                forward("/mainPage.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBookDetails.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
