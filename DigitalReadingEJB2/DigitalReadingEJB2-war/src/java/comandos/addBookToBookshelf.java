/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import User.ModelUser;
import Biblioteca.ModelBiblioteca;
import EJBs.Book;
import EJBs.Cart;
import EJBs.CartLocal;
import EJBs.Log;
import EJBs.StatefulBeans;
import EJBs.Stats;
import EJBs.UserRemote;
import EJBs.UserRemote;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class addBookToBookshelf extends FrontCommand {

    //addBookToBookShelf equivale a comprar los libros
    static String MSG = "addBookToBookshelf";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            //@Singleton STATS
            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Stats");

            HttpSession session = request.getSession();

            UserRemote user = (UserRemote) session.getAttribute("user");
            CartLocal carrito = (CartLocal) session.getAttribute("carrito");

            int userID = user.getId();

            //ya está comprobado en el addToCart 
            //pero por precausión
            if (carrito.getTotalPrice() > user.getAccount()) {
                forward("/mainPage.jsp");
            }

            double totalprice = 0.0;
            for (Book book : carrito.getCart()) {
                if (!ModelBiblioteca.isAlreadyContains(userID, book.getId())) {

                    stats.buyABook();

                    totalprice += book.getPrice();
                    ModelBiblioteca.insertBookInBookShelf(user.getId(), book.getId());
                }
            }

            user.setAccount(user.getAccount() - totalprice);

            ModelUser.changeAccount(userID, user.getAccount());

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/StatefulBeans");

            allStatefulBean.addCart(user.getId()+100, carrito.cartToString()+" Cart completed");
            allStatefulBean.updateCart(user.getId(), "");
            
            
            stats.completedACart();

            carrito.clearCartBook();

            session.setAttribute("carrito", carrito);
            session.setAttribute("user", user);
            session.setAttribute("biblioteca", ModelBiblioteca.getBookShelf(userID));

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addBookToBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
