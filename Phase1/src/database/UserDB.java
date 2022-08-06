package database;

import models.User;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static database.DBInfo.getConnection;

public class UserDB {

    public static void addUser(User user) {
        try {
            if (user == null)
                return;
            Connection con = getConnection();
            String query = "insert into `user` values(NULL,'" + user.getUserID() + "'" +
                    ",'" + user.getUsername() + "','" + user.getPassword() + "','" +
                    user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                    "','" + user.getSecurityAnswer() + "'," + user.getSecurityQuestion() + ","
                    + ((user.getIsNormal()) ? "1" : "0") +",NULL)";
            // System.out.println(query);
            con.createStatement().execute(query);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateUser(User user) {
        try {
            Connection con = getConnection();
            String query = "update `user` set user_id = '" + user.getUserID() +
                    "', username = '"+ user.getUsername()+
                    "', password = '" + user.getPassword()+
//                    "', type = " +
//                    ((user.getIsNormal()) ? "1" : "0")+
                    "';";
            // System.out.println(query);
            con.createStatement().execute(query);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(User user) {
       try {
           Connection con = DBInfo.getConnection();
           Statement st = con.createStatement();
           st.execute("delete from `user` where user_id = '" + user.getUserID()+"';");
           con.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static ArrayList<Integer> getFollowings(Integer userID){
        ArrayList<Integer> following= new ArrayList<>();
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            String query = "select * from followship where is_following_id = " + userID + ";" ;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                following.add(rs.getInt(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return following;


    }


    public static ArrayList<Integer> getFollowers(Integer userID){
        ArrayList<Integer> followers= new ArrayList<>();
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            String query = "select * from followship where is_followed_id = " + userID + ";" ;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                followers.add(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return followers;
    }

    public static void unFollow(Integer loggedInUser, Integer currentProfile) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
           st.execute("delete from `followship` where is_following_id = "+ loggedInUser+ " and is_followed_id= "+ currentProfile );
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void follow(Integer loggedInUser, Integer currentProfile) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            st.execute("INSERT INTO followship( is_following_id , is_followed_id )  VALUES( "
                   +loggedInUser+"," +currentProfile  + ")");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
