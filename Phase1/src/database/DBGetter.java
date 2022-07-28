package database;
// line 118
import models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DBGetter {

//    public static void connecting() {
//        try{
//            DBInfo connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user`");
//
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void showPrivateChats(ArrayList<String> chats) {
        //\
    }

    public static User findUserByUserNumberID(int senderID) {
        User user = null;
        try{
            Connection connection = DBInfo.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user` WHERE user_number_id = " + senderID);
           user = new NormalAcc( resultSet.getString("user_id"),
                   resultSet.getString("username"),
                   resultSet.getString("password") ,
                    resultSet.getString("security_answer"),
                   resultSet.getInt("security_num"));
           user.setId(senderID);
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }



    public static User findUserByUserID(String userID) {
        User user = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaldb", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user` WHERE user_id = " + userID);
            user = new NormalAcc(  userID,
                    resultSet.getString("username"),
                    resultSet.getString("password") ,
                    resultSet.getString("security_answer"),
                    resultSet.getInt("security_num"));

       user.setId(resultSet.getInt("user_number_id"));

        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }



    public static  ArrayList<Group> findGroupsWithMemberID(int memberID){
        ArrayList<Group> groups = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM membership WHERE user_id = " + memberID);

            while(resultSet.next()){
                ResultSet groupSet = statement.executeQuery("SELECT * FROM `group` WHERE group_id = " + resultSet.getString("group_id"));
                Group newGroup = new Group();
                newGroup.setGroupNumberID(Integer.parseInt(groupSet.getString("group_number_id")));
                newGroup.setGroupID(groupSet.getString("group_id"));
                newGroup.setGroupName(groupSet.getString("group_name"));
                newGroup.setGroupAdminID(Integer.parseInt(groupSet.getString("group_admin_id")));
                newGroup.setGroupName(groupSet.getString("group_name"));
                groups.add(newGroup);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return groups;
    }

    public static ArrayList<GroupMessage> findGroupMessagesByGroupID(int groupID){
        ArrayList<GroupMessage> messages = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM group_message WHERE group_id = " + groupID + " ORDER BY creation_date");

            while(resultSet.next()){
                int senderID = Integer.parseInt(resultSet.getString("sender_id"));
                int messageID = Integer.parseInt(resultSet.getString("message_id"));
                int inReplyTo = Integer.parseInt(resultSet.getString("replied_message_id"));
                int forwardedFrom = Integer.parseInt(resultSet.getString("forwarded_from"));
                Date creationDate = resultSet.getDate("creation_date");
                GroupMessage newMessage = new GroupMessage( groupID, senderID , messageID, resultSet.getString("text"), inReplyTo, creationDate,forwardedFrom);

                messages.add(newMessage);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return messages;
    }


    public static GroupMessage findMessageByMessageID(int messageID) {
        GroupMessage newMessage = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM group_message WHERE message_id = " + messageID);

            while(resultSet.next()){
                int senderID = Integer.parseInt(resultSet.getString("sender_id"));
                int groupID = Integer.parseInt(resultSet.getString("group_id"));
                int inReplyTo = Integer.parseInt(resultSet.getString("replied_message_id"));
                int forwardedFrom = Integer.parseInt(resultSet.getString("forwarded_from"));
                Date creationDate = resultSet.getDate("creation_date");
                newMessage = new GroupMessage( groupID, senderID , messageID, resultSet.getString("text"), inReplyTo, creationDate,forwardedFrom);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return newMessage;
    }

    public static Group findGroupByGroupNumberID(int groupNumberID) {

        Group group = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet groupSet = statement.executeQuery("SELECT * FROM `group` WHERE group_number_id = " + groupNumberID);
            group = new Group();
            group.setGroupNumberID(groupNumberID);
            group.setGroupID(groupSet.getString("group_id"));
            group.setGroupName(groupSet.getString("group_name"));
            group.setGroupAdminID(Integer.parseInt(groupSet.getString("group_admin_id")));
            group.setGroupName(groupSet.getString("group_name"));


        } catch (Exception e){
            e.printStackTrace();
        }

        return group;
    }

    public static Group findGroupByGroupID(String groupID) {
        Group group = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet groupSet = statement.executeQuery("SELECT * FROM `group` WHERE group_id = " + groupID);
            group = new Group();
            group.setGroupID(groupID);
            group.setGroupName(groupSet.getString("group_name"));
            group.setGroupAdminID(Integer.parseInt(groupSet.getString("group_admin_id")));
            group.setGroupName(groupSet.getString("group_name"));


        } catch (Exception e){
            e.printStackTrace();
        }

        return group;
    }

    public static boolean banCheck(int groupNumberID, int senderID) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet groupSet = statement.executeQuery("SELECT * FROM `group` WHERE group_id = " + groupNumberID + "AND user_id = " + senderID );

            return groupSet.wasNull();


        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    public static boolean BlockedByBLocker(int blockedID, int blocker) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet groupSet = statement.executeQuery("SELECT * FROM block_list WHERE blocked_id = " + blockedID + "AND blocked_by_id = " + blocker );

            // if the set is empty it means that no one is blocked
            return !groupSet.wasNull();


        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public static boolean checkMembership(int memberID, int groupNumberID) {

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet groupSet = statement.executeQuery("SELECT * FROM membership WHERE group_id = " + groupNumberID + "AND user_id = " + memberID );

            return !groupSet.wasNull();


        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkPrivateChat(int firstID, int secondID) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet privateSet1 = statement.executeQuery("SELECT * FROM private_chat WHERE first_user_id = " + firstID + "AND second_user_id = " + secondID);
            ResultSet privateSet2 = statement.executeQuery("SELECT * FROM private_chat WHERE first_user_id = " + secondID + "AND second_user_id = " + firstID);
            return !(privateSet1.wasNull() && privateSet2.wasNull());


        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Personal> findChatsWithMemberID(int numberID) {
        ArrayList<Personal> chats = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM private_chat WHERE first_id = " + numberID + "OR second_id =" + numberID );

            while(resultSet.next()){
                ResultSet chatSet = statement.executeQuery("SELECT * FROM `group` WHERE group_id = " + resultSet.getString("group_id"));
                Personal personal  = new Personal(Integer.parseInt(chatSet.getString("first_user_id")),Integer.parseInt(chatSet.getString("second_user_id")), chatSet.getString("creation_date"));

                chats.add(personal);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return chats;
    }

    public static ArrayList<PrivateMessage> findPrivateMessagesWithBothMembersID(int id1, int id2) {
        ArrayList<PrivateMessage> messages = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM group_message WHERE sender_id = "+id1 + " ORDER BY creation_date");

            while(resultSet.next()){
                int senderID = Integer.parseInt(resultSet.getString("sender_id"));
                int messageID = Integer.parseInt(resultSet.getString("message_id"));
                int inReplyTo = Integer.parseInt(resultSet.getString("replied_message_id"));
                int forwardedFrom = Integer.parseInt(resultSet.getString("forwarded_from"));
                Date creationDate = resultSet.getDate("creation_date");
 //               PrivateMessage newMessage = new PrivateMessage( groupID, senderID , messageID, resultSet.getString("text"), inReplyTo, creationDate,forwardedFrom);

   //             messages.add(newMessage);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return messages;
    }
}
