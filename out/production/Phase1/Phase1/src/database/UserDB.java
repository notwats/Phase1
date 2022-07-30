package database;

import models.NormalAcc;
import models.User;

import java.sql.*;
import java.time.format.DateTimeFormatter;

import static database.DBInfo.getConnection;
import static database.DBInfo.username;

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
                    + ((user.getIsNormal()) ? "1" : "0") + ")";
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

}
