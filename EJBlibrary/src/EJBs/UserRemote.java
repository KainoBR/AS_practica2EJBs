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
public interface UserRemote {

    public int getAdmin();

    public void setAdmin(int admin);

    public double getAccount();

    public void setAccount(double account);

    public int getId();

    public void setId(int id);

    public String getEmail();

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);

    public String getUsername();
    public void setUsername(String username);

    public String getLastname();
    public void setLastname(String lastname);

    public int isAdmin();

    @Override
    public String toString();

    @Override
    public boolean equals(Object o);

    public void remove();
}
