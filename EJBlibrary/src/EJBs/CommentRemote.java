/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import javax.ejb.Remote;

/**
 *
 * @author Miki
 */
@Remote
public interface CommentRemote {

    public int getId();

    public void setId(int id);

    public int getBookID();

    public void setBookID(int bookID);

    public int getUserID();

    public void setUserID(int userID);

    public String getComment();

    public void setComment(String comment);
    
    public String getUsername();

    public void setUsername(String username);
    
    @Override
    public String toString();
    
}
