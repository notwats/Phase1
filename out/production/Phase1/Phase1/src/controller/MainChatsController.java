package controller;

import database.DBGetter;
import database.UpdateDB;
import models.Group;
import models.Personal;
import models.User;

import java.util.ArrayList;

public class MainChatsController  extends Controller{

    public ArrayList<Group> handleShowGroups(int id) {
        ArrayList<Group> groups =  DBGetter.findGroupsWithMemberID(id);
        if(groups.size() == 0){
            System.out.println("you are not added in any group");
        }
        return groups;
    }

    public void handleCreateGroup(String groupID, String groupName, int adminID) {
        if(DBGetter.findGroupByGroupID(groupID) != null) {
            System.out.println("this ID is already taken");
            return;
        }

        UpdateDB.createGroup(groupID, groupName, adminID);
        System.out.println("created successfully");
    }

    public void handleDeleteGroup(Group group, int userID) {
        if(group.getGroupAdminNumberID() != userID){
            System.out.println("you are not the admin of this group you can't delete it");
            return;
        }

        UpdateDB.deleteGroup(group.getGroupNumberID());
    }

    public boolean handleEnteringGroup(Group group, int id) {


        return DBGetter.checkMembership(id, group.getGroupNumberID());
    }

    public void handleCreatePrivateChat(String receiverID, int senderID) {
        User receiver = DBGetter.findUserByUserID(receiverID);
        if(receiver == null){
            System.out.println("the member id doesn't belong to any user");
            return;
        }
        if(DBGetter.checkPrivateChat(receiver.getId(), senderID)){
            System.out.println("you already have a chat with this user");
            return;
        }

        if(receiver.getId() == senderID){
            System.out.println("you can't message yourself! :))");
            return;
        }

        UpdateDB.createPrivateChat(receiver.getId(), senderID);
        System.out.println("successfully made the private chat");
    }

    public ArrayList<Personal> handleShowPrivateChats(int numberID) {
        ArrayList<Personal> chats = DBGetter.findChatsWithMemberID(numberID);
        if(chats.size() == 0){
            System.out.println("you have no chats");
        }
        return chats;
    }

    public void handleDeletePrivateChat(int id1, int id2) {

        if(id1 == id2){
            System.out.println("you can't have a chat with yourself");
            return;
        }
        if(!DBGetter.checkPrivateChat(id1, id2)){
            System.out.println("you don't have a chat with this user");
            return;
        }

        UpdateDB.deletePrivateChat(id1, id2);
        System.out.println("chat was deleted successfully");
    }
}
