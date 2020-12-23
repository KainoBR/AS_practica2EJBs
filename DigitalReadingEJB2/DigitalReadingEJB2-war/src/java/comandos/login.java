/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Book.ModelBook;
import Controller.FrontCommand;
import User.ModelUser;

import EJBs.UserRemote;
import Biblioteca.ModelBiblioteca;
import EJBs.Book;
import EJBs.Cart;
import EJBs.CartLocal;

import EJBs.Log;
import EJBs.StatefulBeans;
import EJBs.Stats;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class login extends FrontCommand {

    static String MSG = "login";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            //@Singleton sessions beans LOG
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/StatefulBeans");

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Stats");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            int userID = ModelUser.login(username, password);

            if (userID > 0) {
                HttpSession session = request.getSession(true);

                UserRemote user = ModelUser.getUser(userID);
                session.setAttribute("user", user);

                List<Book> catalogo = ModelBook.getCatalogo();
                
                session.setAttribute("catalogo", catalogo );
                List<Book> biblioteca = ModelBiblioteca.getBookShelf(userID);
                session.setAttribute("biblioteca", biblioteca );

                //@Statfull
                CartLocal carrito;
                carrito = (CartLocal) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Cart!EJBs.CartLocal");
                session.setAttribute("carrito", carrito);
                
                //@Singleton STATS
                stats.addUser(user.toString());
            
                //@Singleton ALL STATEFUL BEAN
                allStatefulBean.addSession(user, password);
               
                forward("/mainPage.jsp");
            } else {
                forward("/index.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            System.out.println("check DB connection");
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
