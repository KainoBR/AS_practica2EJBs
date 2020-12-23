/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Book.ModelBook;
import Controller.FrontCommand;
import EJBs.Book;
import EJBs.Comment;
import EJBs.CartLocal;
import EJBs.Log;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class removeBookFromCart extends FrontCommand {

    static String MSG = "removeBookFromCart";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = request.getSession();

            int bookID = Integer.parseInt(request.getParameter("bookID"));
            CartLocal carrito = (CartLocal) session.getAttribute("carrito");

            Book bookToRemove = (Book) ModelBook.getBook(bookID);
            

            carrito.removeBookFromCart(bookToRemove);
            
            
            session.setAttribute("carrito", carrito);
            
            forward("/mainPage.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(removeBookFromCart.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
