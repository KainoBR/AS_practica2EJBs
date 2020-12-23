/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJBs.Book;
import EJBs.Comment;
import EJBs.UserRemote;
import Biblioteca.ModelBiblioteca;
import Book.ModelBook;
import EJBs.Log;
import EJBs.Stats;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class removeBookFromBookshelf extends FrontCommand {

    static String MSG = "removeBookFromBookshelf";

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

            int bookID = Integer.parseInt(request.getParameter("bookID"));
            UserRemote user = (UserRemote) session.getAttribute("user");

            int userID = user.getId();

            ModelBiblioteca.deleteBookFromBookShelf(userID, bookID);

            user.setAccount(user.getAccount() + ModelBook.getBook(bookID).getPrice());
            
            stats.sellABook();

            session.setAttribute("biblioteca", ModelBiblioteca.getBookShelf(userID));
            session.setAttribute("user", user);

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(removeBookFromBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
