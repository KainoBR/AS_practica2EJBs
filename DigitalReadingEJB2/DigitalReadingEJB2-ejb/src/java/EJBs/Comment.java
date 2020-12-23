/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 *
 * @author Miki
 */
@Stateless
public class Comment implements CommentRemote {

    static String classMSG = "Comment";

    protected int id;
    protected int bookID;
    protected int userID;

    protected String comment;
    protected String username;

    public Comment() {
        System.out.println(classMSG + "::constructor()");
    }

    public Comment(int id, int bookID, int userID, String comment, String username) {
        this.id = id;
        this.bookID = bookID;
        this.userID = userID;
        this.comment = comment;
        this.username = username;
        System.out.println(classMSG + "::constructor(id, bookID, userID, comment, username)");
    }

    public Comment(int bookID, int userID, String comment, String username) {
        this.bookID = bookID;
        this.userID = userID;
        this.comment = comment;
        this.username = username;
        System.out.println(classMSG + "::constructor(bookID, userID, comment, username)");
    }

    @Override
    public int getId() {
        System.out.println(classMSG + "::getId() >> " + id);
        return id;
    }

    @Override
    public void setId(int id) {
        System.out.println(classMSG + "::setId(int) >> " + id);
        this.id = id;
    }

    @Override
    public int getBookID() {
        System.out.println(classMSG + "::getBookID() >> " + bookID);
        return bookID;
    }

    @Override
    public void setBookID(int bookID) {
        System.out.println(classMSG + "::setBookID(" + bookID + ")");
        this.bookID = bookID;
    }

    @Override
    public int getUserID() {
        System.out.println(classMSG + "::getUserID() >> " + userID);
        return userID;
    }

    @Override
    public void setUserID(int userID) {
        System.out.println(classMSG + "::setUserID("+userID + ")");
        this.userID = userID;
    }

    @Override
    public String getComment() {
        System.out.println(classMSG + "::getComment() >> " + comment);
        return comment;
    }

    @Override
    public String getUsername() {
        System.out.println(classMSG + "::getUsername() >> " + username);
        return username;
    }

    @Override
    public void setUsername(String username) {
        System.out.println(classMSG + "::setUsername("+username + ")");
        this.username = username;
    }

    @Override
    public void setComment(String comment) {
        System.out.println(classMSG + "::setComment("+comment + ")");
        this.comment = comment;
    }

    @Override
    public String toString() {
        System.out.println(classMSG + "::toString()");
        return comment;
    }

    @PostConstruct
    public void init() {
        System.out.println(classMSG + "::init - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println(classMSG + "::kill - @PreDestroy ");
    }

}
