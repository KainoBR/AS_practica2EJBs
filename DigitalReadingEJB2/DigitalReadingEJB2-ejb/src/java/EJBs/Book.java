/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 *
 * @author Miki
 */
@Stateless
public class Book implements BookLocal {

    static String classMSG = "Book";

    private int id;
    private String title;
    private String author;
    private String genre;
    private Date sellingDate;
    private Double price;
    private int likes;
    private String portada;

    private List<Comment> comentarios;

    public Book() {
    }

    public Book(int id, String title, String author, String genre, Date sellingDate, double price, int likes, String portada) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.sellingDate = sellingDate;
        this.price = price;
        this.likes = likes;
        this.portada = portada;

        System.out.println(classMSG + "::constructor(id, title, author, genre, "
                + "sellingDate, price, likes, portada)::"
                + id + ", " + title + ", " + author + ", " + genre + ", " + sellingDate
                + ", " + price + ", " + likes + ", " + portada);
    }

    //sin ID
    public Book(String title, String author, String genre, Date sellingDate,  double price, int likes, String portada) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.sellingDate = sellingDate;
        this.price = price;
        this.likes = likes;
        this.portada = portada;

        System.out.println(classMSG + "::constructor(title, author, genre, "
                + "sellingDate, price, likes, portada)::"
                + title + ", " + author + ", " + genre + ", " + sellingDate
                + ", " + price + ", " + likes + ", " + portada);
    }

    //contrusctor base: sin ID, likes y portada
    public Book(String title, String author, String genre, Date sellingDate, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.sellingDate = sellingDate;
        this.price = price;

        System.out.println(classMSG + "::constructor(title, author, genre, "
                + "sellingDate, price)::"
                + title + ", " + author + ", " + genre + ", " + sellingDate
                + ", " + price);
    }

    @Override
    public int getId() {
        System.out.println(classMSG + "::getID() >> " + id);
        return id;
    }

    @Override
    public String getAuthor() {
        System.out.println(classMSG + "::getAuthor() >> " + author);
        return author;
    }

    @Override
    public String getTitle() {
        System.out.println(classMSG + "::getTitle() >> " + title);
        return title;
    }

    @Override
    public String getGenre() {
        System.out.println(classMSG + "::getGenre() >> " + genre);
        return genre;
    }

    @Override
    public Date getSellingDate() {
        System.out.println(classMSG + "::getSellingDate() >> " + sellingDate);
        return sellingDate;
    }

   
    @Override
    public Double getPrice() {
        System.out.println(classMSG + "::getPrice() >> " + price);
        return price;
    }

    @Override
    public int getLikes() {
        System.out.println(classMSG + "::getLikes() >> " + likes);
        return likes;
    }

    @Override
    public void setLikes(int likes) {
        System.out.println(classMSG + "::setLikes(int)::" + likes);
        this.likes = likes;
    }

    @Override
    public String getPortada() {
        System.out.println(classMSG + "::getPortada() >> " + portada);
        return portada;
    }

    @Override
    public void setPortada(String portada) {
        System.out.println(classMSG + "::setPortada(String)::" + portada);
        this.portada = portada;
    }

    @Override
    public List<Comment> getComentarios() {
        System.out.println(classMSG + "::getComentarios() >> " + comentarios);
        return comentarios;
    }

    @Override
    public void setComentarios(List<Comment> comentarios) {
        System.out.println(classMSG + "::setComentarios(List<Comment>) >> " + comentarios);
        this.comentarios = comentarios;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        System.out.println(classMSG + "::toString()");
        return title + "\nAuthor: " + author + "\nPrice: " + price;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        System.out.println(classMSG + "::equals(Object)");
        Book compare = (Book) o;

        if (!this.getTitle().equals(compare.getTitle())
                & !this.getAuthor().equals(compare.getAuthor())
                & this.getSellingDate() != compare.getSellingDate()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isAlreadyContains(List<Book> books) {
        System.out.println(classMSG + "::isAlreadyContains(List<Book>)");
        for (Book book : books) {
            if (this.getId() == book.getId()) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void init() {
        System.out.println(classMSG + "::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println(classMSG + "::kill() - @PreDestroy ");
    }
}
