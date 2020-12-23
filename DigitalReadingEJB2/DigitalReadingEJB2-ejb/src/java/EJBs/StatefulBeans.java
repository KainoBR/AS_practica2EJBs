/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;

/**
 *
 * @author Miki
 */
@Singleton
@LocalBean
public class StatefulBeans {

    List<String> users;
    HashMap<Integer, String> carritos;

    public StatefulBeans() {
        users = new ArrayList<>();
        carritos = new HashMap<>();
    }

    @Lock(LockType.READ) //permit multiple access
    public List<String> getAllUsers() {
        return users;
    }

    public void addSession(UserRemote user, String carrito) {
        users.add(user.toString());
        carritos.put(user.getId(), carrito);
    }

    public void removeSession(UserRemote user) {
        users.remove(user.toString());
        carritos.remove(user.getId());
    }

    @Lock(LockType.READ) //permit multiple access
    public HashMap<Integer, String> getAllCarts() {
        return carritos;
    }
    
    public void addCart(int userID, String carrito){
        carritos.put(userID, carrito);
    }
    
    public void updateCart(int userID, String carrito){
        carritos.replace(userID, carrito);
    }
    
    public void updateUser(String oldUser, String newUser){
        users.remove(oldUser);
        users.add(newUser);
    }
    
    
    
    
    

    @PostConstruct
    public void init() {
        System.out.println("StatefulBeans::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println("StatefulBeans::kill() - @PreDestroy ");
    }

}
