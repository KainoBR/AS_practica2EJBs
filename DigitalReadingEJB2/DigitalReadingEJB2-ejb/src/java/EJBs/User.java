/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Miki
 */
@Stateful
public class User implements UserRemote {

    private static String classMSG = "User";

    private int id;

    private String username;
    private String lastname;
    private String email;
    private String password;

    private int admin;

    double account;

    public User() {
        System.out.println(classMSG + "constructor()");
    }

    public User(int id, String username, String lastname, String email, String password, int admin, double account) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.account = account;

        System.out.println(classMSG + "constructor(id, username, lastname, email, password"
                + "admin, account)");
    }

    public User(String username, String lastname, String email, String password, double account) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.account = account;

        System.out.println(classMSG + "constructor(username, lastname, email, password"
                + "admin, account)");
    }

    @Override
    public int getAdmin() {
        System.out.println(classMSG + "::getAdmin() >> " + admin);
        return admin;
    }

    @Override
    public void setAdmin(int admin) {
        System.out.println(classMSG + "::setAdmin(" + admin + ") #1 == admin, 0 == standar");
        this.admin = admin;
    }

    @Override
    public double getAccount() {
        System.out.println(classMSG + "::getAccount() >> " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        System.out.println(classMSG + "::setAccount(" + account + ") ");
        this.account = account;
    }

    @Override
    public int getId() {
        System.out.println(classMSG + "::getId() >> " + id);
        return id;
    }

    @Override
    public void setId(int id) {
        System.out.println(classMSG + "::setId(" + id + ") ");
        this.id = id;
    }

    @Override
    public String getEmail() {
        System.out.println(classMSG + "::getEmail() >> " + email);
        return email;
    }

    @Override
    public void setEmail(String email) {
        System.out.println(classMSG + "::setEmail(" + email + ") ");
        this.email = email;
    }

    @Override
    public String getPassword() {
        System.out.println(classMSG + "::getPassword() >> " + password);
        return password;
    }

    @Override
    public void setPassword(String password) {
        System.out.println(classMSG + "::setPassword(" + password + ") ");
        this.password = password;
    }

    @Override
    public String getUsername() {
        System.out.println(classMSG + "::getUsername() >> " + username);
        return username;
    }

    @Override
    public void setUsername(String username) {
        System.out.println(classMSG + "::setUsername(" + username + ")");
        this.username = username;
    }

    @Override
    public String getLastname() {
        System.out.println(classMSG + "::getLastname() >> " + lastname);
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        System.out.println(classMSG + "::setLastname(" + lastname + ")");
        this.lastname = lastname;
    }

    @Override
    public int isAdmin() {
        System.out.println(classMSG + "::isAdmin()");
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println(classMSG + "::equals(Object)");
        UserRemote user = (UserRemote) o;
        return this.username.equals(user.getUsername()) & this.lastname.equals(user.getLastname());

    }

    @Override
    public String toString() {
        System.out.println(classMSG + "::toString()");
        return "User: " + username + lastname + ", email=" + email;
    }

    @Remove
    @Override
    public void remove() {
        System.out.println(classMSG + "::remove() - @Remove ");
    }

    @PostConstruct
    public void init() {
        System.out.println(classMSG + "::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println(classMSG + "::kill() - @PreDestroy ");
    }

    @PostActivate
    public void postActivate() {
        System.out.println(classMSG + "::postActivate() - @PostActivate ");
    }

    @PrePassivate
    public void prePassivate() {
        System.out.println(classMSG + "::prePassivate() - @PrePassivate ");
    }
}
