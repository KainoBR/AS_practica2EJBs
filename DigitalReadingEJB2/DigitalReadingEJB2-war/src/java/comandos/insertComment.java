/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Comentarios.ModelComment;
import Controller.FrontCommand;
import EJBs.Book;
import EJBs.Comment;
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
public class insertComment extends FrontCommand {

    static String MSG = "insertComment";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = request.getSession();

            String comment = request.getParameter("comment");
            UserRemote user = (UserRemote) session.getAttribute("user");

            Book book = (Book) session.getAttribute("libro");

            Comment newComment = new Comment(book.getId(), user.getId(), comment, user.getUsername());

            ModelComment.insertComment(newComment);

            book.setComentarios(ModelComment.getCommentsFromThisBook(book.getId()));

            session.setAttribute("libro", book);
            forward("/showBookDetails.jsp");
            
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(insertBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
