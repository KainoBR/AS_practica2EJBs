/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Book.ModelBook;
import Controller.FrontCommand;

import Biblioteca.ModelBiblioteca;

import EJBs.Book;
import EJBs.CartLocal;
import EJBs.Log;
import EJBs.StatefulBeans;
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
public class addBookToCart extends FrontCommand {

    static String MSG = "addBookToCart";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = request.getSession();

            UserRemote user = (UserRemote) session.getAttribute("user");

            CartLocal carrito = (CartLocal) session.getAttribute("carrito");

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            List<Book> bookShelf = ModelBiblioteca.getBookShelf(user.getId());

            Book bookToBuy = (Book) ModelBook.getBook(bookID);
            if (!carrito.contains(bookToBuy) & !aux(bookShelf, bookToBuy)) {

                carrito.addBookToCart(bookToBuy);

                StatefulBeans allStatefulBean;
                
                allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/StatefulBeans");
                
                
                
                allStatefulBean.updateCart(user.getId(), carrito.cartToString());

                session.setAttribute("carrito", carrito);
            }
            forward("/mainPage.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addBookToCart.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

    private boolean aux(List<Book> bookShelf, Book o) {
        for (Book book : bookShelf) {
            if (book.getId() == o.getId()) {
                return true;
            }
        }
        return false;
    }

}
