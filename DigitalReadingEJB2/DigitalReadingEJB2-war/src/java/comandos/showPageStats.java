/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Biblioteca.ModelBiblioteca;
import Controller.FrontCommand;
import EJBs.Book;
import EJBs.CartLocal;
import EJBs.Log;
import EJBs.Stats;
import EJBs.UserRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showPageStats extends FrontCommand {

    static String MSG = "showPageData";

    @Override
    public void process() {

        try {
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);
            
            HttpSession session = (HttpSession) request.getSession();

            UserRemote user = (UserRemote) session.getAttribute("user");
            int userID = user.getId();

            List<Book> biblioteca = ModelBiblioteca.getBookShelf(userID);

            CartLocal carrito = (CartLocal) session.getAttribute("carrito");

            //@Singleton STATS
            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Stats");

            session.setAttribute("stats", stats);

            forward("/showPageStats.jsp");
        } catch (Exception ex) {
            Logger.getLogger(showPageStats.class.getName()).log(Level.SEVERE, null, ex);
            forward("/commandErrorPage.jsp");
        }

    }

    private String makeString(List<Book> listOfBook) {
        String s = "";
        for (Book book : listOfBook) {
            s += book.toString() + "<br>";
        }
        return s;
    }

}
