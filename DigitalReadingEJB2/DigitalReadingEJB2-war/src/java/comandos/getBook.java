/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Book.ModelBook;
import Controller.FrontCommand;
import EJBs.Book;
import EJBs.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class getBook extends FrontCommand {

    static String MSG = "getBook";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            int bookId = Integer.parseInt(request.getParameter("bookID"));

            Book uploadBook = ModelBook.getBook(bookId);

            if (uploadBook != null) {
                //atributo especial para actualizar! (muestra los campos en el formulario
                request.setAttribute("bookToUpload", uploadBook);
                forward("/updateBook.jsp");
            } else {
                forward("/showAllBooks.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            Logger.getLogger(getBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
