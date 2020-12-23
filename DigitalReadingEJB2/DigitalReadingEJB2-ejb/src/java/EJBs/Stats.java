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
public class Stats {

    //usuarios logeados
    //.size() total de usuarios logeados
    List<String> usersLogged;

    
    //número de libros comprados, negativo es que se han devuelto libros. 
    int Bookspurchased;
    
    int CartsCompleted;

    public Stats() {
        this.usersLogged = new ArrayList<>();

        CartsCompleted = 0;
        Bookspurchased = 0;
    }

    public void removeUser(String user) {
        usersLogged.remove(user);
    }

    public int getPurchasedBook() {
        return Bookspurchased;
    }
    
     public int getCartsCompleted() {
        return CartsCompleted;
    }
    
    public List<String> getUsersLogged() {
        return usersLogged;
    }

    public void addUser(String user) {
        usersLogged.add(user);
    }

    public void buyABook() {
        Bookspurchased++;
    }
    
    public void completedACart(){
        CartsCompleted++;
    }

    public void sellABook() {
        Bookspurchased--;
    }

    @PostConstruct
    public void init() {
        System.out.println("Stats::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println("Stats::kill() - @PreDestroy ");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
