/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
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
public class removeAllBooksFromCart extends FrontCommand {

    static String MSG = "removeAllBooksFromCart";
    // clearLogs the book cart

    @Override
    public void process() {

        System.out.println(MSG);
        
        try {
            
            Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
            log.addLogs(MSG);

            HttpSession session = request.getSession();
            
            CartLocal carrito = (CartLocal) session.getAttribute("carrito");

             // --- clearLogs and remove CartBook component ---//
            carrito.clearCartBook();
            
            carrito.remove(); //@remove

            session.setAttribute("carrito", null);
            // --- \\  ----  \\ ---//

            forward("/mainPage.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(removeAllBooksFromCart.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
