package Comentarios;

import Controller.ConexionBBDD;
import EJBs.Log;
import EJBs.Comment;
import EJBs.UserRemote;
import User.ModelUser;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class ModelComment {

   static String classMSG = "#== ModelComment::";

    public static boolean insertComment(Comment newComment) throws ClassNotFoundException, SQLException, Exception {

        String methodMSG = "insertComment(" + newComment + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);


        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();
            DBstatement = DBconnection.prepareStatement("INSERT INTO COMENTARIOS (IDuser, IDbook, Username, Comentario)"
                    + "VALUES(?,?,?,?)");

            DBstatement.setInt(1, newComment.getUserID());
            DBstatement.setInt(2, newComment.getBookID());

            String username = ModelUser.getUser(newComment.getUserID()).getUsername();

            DBstatement.setString(3, username);
            DBstatement.setString(4, newComment.getComment());

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

    public static boolean updateComment(Comment newComment) throws ClassNotFoundException, SQLException, Exception {

        String methodMSG = "updateComment(" + newComment + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();
            DBstatement = DBconnection.prepareStatement("UPDATE comentarios SET Comentario=? WHERE id=?");

            DBstatement.setString(1, newComment.getComment());
            DBstatement.setInt(2, newComment.getUserID());

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
     * Elimina un comentario de la BBDD
     *
     * @param commentID
     * @return
     * @throws Exception
     */
    public static boolean deleteComment(int commentID) throws Exception {

        String methodMSG = "deleteComment(" + commentID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("DELETE FROM COMENTARIOS WHERE ID=?");

            DBstatement.setInt(1, commentID);

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
    
        public static boolean deleteAllCommentFromThisBook(int bookID) throws Exception {

        String methodMSG = "deleteAllCommentFromThisBook(" + bookID + ")";

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("DELETE FROM COMENTARIOS WHERE IDbook=?");

            DBstatement.setInt(1, bookID);

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
    
    public static boolean deleteAllCommentFromThisUser(int userID) throws Exception {

        String methodMSG = "deleteAllCommentFromThisUser(" + userID + ")";

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("DELETE FROM COMENTARIOS WHERE IDuser=?");

            DBstatement.setInt(1, userID);

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
     * @param bookID
     * @return todos los comentarios del libro Formato >> List<String> donde
     * String ("\"" + comentario + "\" - " + usuario)
     * @throws Exception
     */
    public static List<Comment> getCommentsFromThisBook(int bookID) throws Exception {

        String methodMSG = "getBookCommentsToPrint(" + bookID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        List<Comment> bookComments = new ArrayList<>();

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("SELECT * FROM COMENTARIOS WHERE IDbook=?");

            DBstatement.setInt(1, bookID);

            ResultSet DBresultset = DBstatement.executeQuery();

            while (DBresultset.next()) {
                int id = DBresultset.getInt("ID");
                int userID = DBresultset.getInt("IDuser");
                String comment = DBresultset.getString("Comentario");
                String username = DBresultset.getString("Username");

                Comment newComment = new Comment(id, bookID, userID, comment, username);

                bookComments.add(newComment);
            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);

        return bookComments;
    }

    public static List<UserRemote> getListOfUserWhoCommentThisBook(int bookID) throws SQLException, Exception {

        String methodMSG = "getListOfUserWhoCommentThisBook(" + bookID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        List<UserRemote> userWhoComment = new ArrayList<>();

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("SELECT * FROM COMENTARIOS WHERE IDbook=?");

            DBstatement.setInt(1, bookID);

            ResultSet DBresultset = DBstatement.executeQuery();

            while (DBresultset.next()) {
                int userID = DBresultset.getInt("IDuser");
                UserRemote user = ModelUser.getUser(userID);

                userWhoComment.add(user);
            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);

        return userWhoComment;
    }

    public static List<Comment> getCommentFromThisUserOfThisBook(int userID, int bookID) throws Exception {

        String methodMSG = "getBookCommentFromThisUserOfThisBook(" + userID + "," + bookID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        List<Comment> comentarios = new ArrayList<>();

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("SELECT * FROM COMENTARIOS WHERE IDuser=? AND IDbook=?");

            DBstatement.setInt(1, userID);
            DBstatement.setInt(2, bookID);

            ResultSet DBresultset = DBstatement.executeQuery();

            while (DBresultset.next()) {
                int id = DBresultset.getInt("ID");
                String comment = DBresultset.getString("Comentario");
                String username = DBresultset.getString("Username");

                Comment newComment = new Comment(id, bookID, userID, comment, username);

                comentarios.add(newComment);
            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);

        return comentarios;
    }

    public static List<Comment> getAllCommentsOfThisUser(int userID) throws Exception {

        String methodMSG = "getAllCommentsOfOneUser(" + userID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        List<Comment> comentarios = new ArrayList<>();

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("SELECT * FROM COMENTARIOS WHERE IDuser=?");

            DBstatement.setInt(1, userID);

            ResultSet DBresultset = DBstatement.executeQuery();

            while (DBresultset.next()) {
                int id = DBresultset.getInt("ID");
                int bookID = DBresultset.getInt("IDbook");
                String comment = DBresultset.getString("Comentario");
                String username = DBresultset.getString("Username");

                Comment newComment = new Comment(id, bookID, userID, comment, username);

                comentarios.add(newComment);
            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);

        return comentarios;
    }

    public static Comment getThisComment(int commentID) throws Exception {
        String methodMSG = "getThisComment(" + commentID + ")";
        
        Log log = (Log) InitialContext.doLookup("java:global/DigitalReadingEJB2/DigitalReadingEJB2-ejb/Log");
        log.addLogs("\n |_ " + classMSG + methodMSG);

        PreparedStatement DBstatement = null;
        Connection DBconnection = null;

        ConexionBBDD conexionConLaBBDD = new ConexionBBDD();

        Comment comment = null;

        try {
            DBconnection = conexionConLaBBDD.getConnection();

            DBstatement = DBconnection.prepareStatement("SELECT * FROM COMENTARIOS WHERE ID=?");

            DBstatement.setInt(1, commentID);

            ResultSet DBresultset = DBstatement.executeQuery();

            while (DBresultset.next()) {
                int bookID = DBresultset.getInt("IDbook");
                int userID = DBresultset.getInt("IDuser");
                String bookComment = DBresultset.getString("Comentario");
                String username = DBresultset.getString("Username");

                comment = new Comment(commentID, bookID, userID, bookComment, username);

            }
        } catch (SQLException e) {
            System.out.println(classMSG + methodMSG + " SQL ERROR");
        } finally {
            DBstatement.close();
            DBconnection.close();
        }

        System.out.println(classMSG + methodMSG);

        return comment;
    }
}
