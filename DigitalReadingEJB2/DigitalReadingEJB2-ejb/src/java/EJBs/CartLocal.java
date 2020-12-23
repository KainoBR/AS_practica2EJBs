/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Miki
 */
@Local
public interface CartLocal {

    public void clearCartBook();

    public void addBookToCart(Book book);

    public boolean isEmpty();

    public List<Book> getCart();

    public void removeBookFromCart(Book bookToRemove);

    public boolean contains(Book o);

    public double getTotalPrice();

    public String cartToString();
    
    public void remove(); //must have @Remove
     
}
