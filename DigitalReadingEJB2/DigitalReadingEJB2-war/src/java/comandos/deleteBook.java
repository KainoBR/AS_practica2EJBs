/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Biblioteca.ModelBiblioteca;
import Book.ModelBook;
import Comentarios.ModelComment;
import Controller.FrontCommand;
import EJBs.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class deleteBook extends FrontCommand {

    static String MSG = "deleteBook";

    @Override
    public void process() {

        System.out.println(MSG);
        
        try {
            
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            HttpSession session = request.getSession();
            
            ModelBook.delete(bookID);
            ModelComment.deleteAllCommentFromThisBook(bookID);
            ModelBiblioteca.deleteBookFromAllBookShelf(bookID);


            session.setAttribute("catalogo", ModelBook.getCatalogo());

            forward("/showCatalog.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(deleteBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
