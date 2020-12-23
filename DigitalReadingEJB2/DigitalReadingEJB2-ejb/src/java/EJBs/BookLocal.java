/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Miki
 */
@Local
public interface BookLocal {
    public int getId();

    public String getAuthor();

    public String getTitle();

    public String getGenre();

    public Date getSellingDate();

    public Double getPrice();

    public int getLikes();

    public void setLikes(int likes);

    public String getPortada();

    public void setPortada(String portada);

    public List<Comment> getComentarios();

    public void setComentarios(List<Comment> comentarios);

    @Override
    public String toString();

    @Override
    public boolean equals(Object o);

    public boolean isAlreadyContains(List<Book> books);
    
}
