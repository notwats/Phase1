package database;
// line 118
import models.Group;
import models.Message;
import models.NormalAcc;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionLink {

    public static void connecting() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user`");

            while(resultSet.next()){
                System.out.println(resultSet.getString("username"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showPrivateChats(ArrayList<String> chats) {
        //\
    }

    public static  ArrayList<Group> findGroupsWithMemberID(int memberID){
        ArrayList<Group> groups = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM membership WHERE user_id = " + memberID);

            while(resultSet.next()){
                ResultSet groupSet = statement.executeQuery("SELECT * FROM group_ WHERE group_id = " + resultSet.getString("group_id"));
                Group newGroup = new Group();
                newGroup.setGroupNumberID(Integer.parseInt(groupSet.getString("group_number_id")));
                newGroup.setGroupID(groupSet.getString("group_id"));
                newGroup.setGroupName(groupSet.getString("group_name"));
                newGroup.setGroupAdminID(Integer.parseInt(groupSet.getString("group_admin_id")));
                newGroup.setGroupName(groupSet.getString("group_name"));
                newGroup.setDescription(groupSet.getString("description"));
                groups.add(newGroup);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return groups;
    }

    public static void messageCreationInGroup(String message, int senderID, int groupID, Date creationDate, int forwardedFromID, int repliedToID){

        // have to include time of sending the message too

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();
            if(forwardedFromID == -1 && repliedToID == -1 )
                statement.executeQuery("INSERT INTO group_messages( sender_id, group_id, text, creation_time)  VALUES( "+senderID+","+groupID+","+message+","+senderID+","+creationDate+","+groupID+")");
            else
                statement.executeQuery("INSERT INTO group_messages( sender_id, group_id, text, creation_time, forwarded_from, replied_to)  VALUES( "+senderID+","+groupID+","+message+","+senderID+","+creationDate+","+groupID+","+forwardedFromID+","+repliedToID +")");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Message> findGroupMessagesByGroupID(int groupID){
        ArrayList<Message> messages = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM group_messages WHERE group_id = " + groupID + " ORDER BY creation_date");

            while(resultSet.next()){
                int senderID = Integer.parseInt(resultSet.getString("sender_id"));
                int messageID = Integer.parseInt(resultSet.getString("message_id"));
                int inReplyTo = Integer.parseInt(resultSet.getString("replied_message_id"));
                int forwardedFrom = Integer.parseInt(resultSet.getString("forwarded_from"));
                Date creationDate = resultSet.getDate("creation_date");
                Message newMessage = new Message( groupID, senderID , messageID, resultSet.getString("text"), inReplyTo, creationDate,forwardedFrom);

               messages.add(newMessage);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return messages;
    }

    public static User findUserByUserNumberID(int senderID) {
        User user = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user` WHERE user_number_id = " + senderID);
  //          user = new NormalAcc(senderID, resultSet.getString("username"), resultSet.getString("password"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

}

/*
    ALTER TABLE `finaldb`.`user`
        ADD COLUMN `security_num` TINYINT(4) NULL AFTER `login_time`,
        ADD COLUMN `security_answer` VARCHAR(30) NULL AFTER `security_num`;

 */