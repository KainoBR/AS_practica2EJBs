package Book;

import Controller.ConexionBBDD;
import EJBs.Book;
import EJBs.Log;
import java.util.*;
import java.sql.*;
import java.util.Date;
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 *
 */
public class ModelBook {

    static String classMSG = "#== ModelBook::";

    /**
     *
     * @return List<Book> of all books in then DB
     * @throws Exception
     */
    public static List<Book> getCatalogo() throws Exception {

        String methodMSG = "getCatalogo()";

        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        List<Book> books = new ArrayList<>();

        Statement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            // --- crear sentencia sql --- //
            //statemnet, preparación de sentencia (impedir inyeccion SQL) 
            DBstatement = DBconnection.createStatement();

            ResultSet DBresultset = DBstatement.executeQuery("SELECT * FROM LIBROS");

            while (DBresultset.next()) {

                //campos: ID, titulo, autor, genero, fecha de venta, descripcion, precio 
                int id = DBresultset.getInt("ID");

                String title = DBresultset.getString("Title");
                String author = DBresultset.getString("Author");
                String genre = DBresultset.getString("genre");

                Date sellingDate = DBresultset.getDate("SellingDate");

                Double price = DBresultset.getDouble("Price");

                int likes = DBresultset.getInt("likes");
                String portadaLibro = DBresultset.getString("Foto");

                Book tempBook = new Book(id, title, author, genre, sellingDate, price, likes, portadaLibro);

                books.add(tempBook);
            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);
        return books;
    }

    /**
     *
     * Agrega un nuevo book a la BBDD
     *
     * @param newBook
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void insert(Book newBook) throws Exception {

        String methodMSG = "insert(" + newBook + ")";

        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();
            DBstatement = DBconnection.prepareStatement("INSERT INTO LIBROS (title, author, genre, sellingDate, price)"
                    + "VALUES(?,?,?,?,?)"); //sentencia SQL

            DBstatement.setString(1, newBook.getTitle());
            DBstatement.setString(2, newBook.getAuthor());
            DBstatement.setString(3, newBook.getGenre());

            //el método setDate utiliza Date de java.SQL pero el Date
            //con el que trabajamos es de java.util 
            //La diferencia es que java.util utiliza horas, java.sql no
            java.util.Date utilDate = newBook.getSellingDate();

            java.sql.Date fechaValidaBBDD = new java.sql.Date(utilDate.getTime());

            DBstatement.setDate(4, fechaValidaBBDD);
            DBstatement.setDouble(5, newBook.getPrice());

            DBstatement.execute();

        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }
        System.out.println(classMSG + methodMSG);
    }

    /**
     *
     * Actualiza un book de la BBDD
     *
     * @param newBook
     * @return true si ha actualizado correctamente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean update(Book newBook) throws Exception {

        String methodMSG = "update(" + newBook + ")";

        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();
            DBstatement = DBconnection.prepareStatement("UPDATE libros SET title=?, author=?, genre=?, sellingDate=?, price=? WHERE id=?");

            DBstatement.setString(1, newBook.getTitle());
            DBstatement.setString(2, newBook.getAuthor());
            DBstatement.setString(3, newBook.getGenre());

            //el método setDate utiliza Date de java.SQL pero el Date
            //con el que trabajamos es de java.util 
            //La diferencia es que java.util utiliza horas, java.sql no
            java.util.Date utilDate = newBook.getSellingDate();

            java.sql.Date fechaValidaBBDD = new java.sql.Date(utilDate.getTime());

            DBstatement.setDate(4, fechaValidaBBDD);
            DBstatement.setDouble(5, newBook.getPrice());
            DBstatement.setInt(6, newBook.getId());

            DBstatement.execute();
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
            return false;
        } finally {
            DBstatement.close();
            DBconnection.close();
        }
        System.out.println(classMSG + methodMSG);
        return true;
    }

    /**
     *
     * @param bookID
     * @return el B
     * @throws Exception
     */
    public static Book getBook(int bookID) throws Exception {

        String methodMSG = "getBook(" + bookID + ")";

        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        Book book = null;

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();
            // --- crear sentencia sql --- //
            //statement, preparación de sentencia (impedir inyeccion SQL) 
            DBstatement = DBconnection.prepareStatement("SELECT * FROM libros WHERE id=?");

            DBstatement.setInt(1, bookID);

            // --- ejecutar sql --- // 
            ResultSet DBresultset = DBstatement.executeQuery();

            //Obtener datos del resulset 
            if (DBresultset.first()) {

                String title = DBresultset.getString("Title");
                String author = DBresultset.getString("Author");
                String genre = DBresultset.getString("genre");
                Date sellingDate = DBresultset.getDate("SellingDate");
                Double price = DBresultset.getDouble("Price");

                String portadaLibro = DBresultset.getString("Foto");

                int likes = DBresultset.getInt("likes");
                book = new Book(bookID, title, author, genre, sellingDate, price, likes, portadaLibro);

            } else {
                System.out.println(classMSG + methodMSG + " warning; book not found in DB");
            }

        } catch (Exception e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
            return null;
        } finally {
            DBstatement.close();
            DBconnection.close();
        }
        //System.out.println(classMSG + methodMSG);
        return book;

    }

    /**
     * Método DELETE
     *
     * @param bookID del BOOK a eliminar
     * @return true si ha conseguido eliminarlo, false, en caso contrario
     * @throws Exception
     */
    public static void delete(int bookID) throws Exception {

        String methodMSG = "delete(" + bookID + ")";

        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("DELETE FROM libros WHERE id=?");

            DBstatement.setInt(1, bookID);

            DBstatement.execute();

        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }
        System.out.println(classMSG + methodMSG);
    }
}
