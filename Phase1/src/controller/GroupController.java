package controller;

import database.DBGetter;
import database.UpdateDB;
import models.Group;
import models.GroupMessage;
import models.User;

import java.util.Date;

public class GroupController extends Controller{

    public void handelEditMessage(String editedText, int messageID) {
        GroupMessage message = DBGetter.findMessageByMessageID(messageID);
        message.editGroupMessage(editedText);
    }


    public void handleGroupNameChange(String groupName, int groupNumberID, String newName) {
        if(newName.length() >= 25){
            System.out.println("group name is too long");
            return;
        }
        if(groupName.equals(newName)){
            System.out.println("the new name is the same as before");
            return;
        }

        UpdateDB.changeGroupName(groupNumberID, newName);
        System.out.println("group name changed to " + newName + " successfully" );
    }

    public void handleGroupIDChange(int groupNumberID, String groupID, String newGroupID) {
        if(newGroupID.length() >= 25){
            System.out.println("group id is too long");
            return;
        }
        if(newGroupID.equals(groupID)){
            System.out.println("the new id is the same as before");
            return;
        }

        if( DBGetter.findGroupByGroupID(newGroupID) != null){
            System.out.println("this groupID is taken");
            return;
        }


        UpdateDB.changeGroupID(groupNumberID, newGroupID);
        System.out.println("ID changed successfully");
    }

    public void handleSendMessage(String message, int senderID, int groupNumberID, Date dateOfNow, int i, int inReplyTo) {
        // ban check
        if(DBGetter.banCheck(groupNumberID, senderID)){
            System.out.println("you're banned from sending a message in this chat");
            return;
        }


        UpdateDB.messageCreationInGroup(message, senderID, groupNumberID, dateOfNow, i, inReplyTo );
        System.out.println("your message is sent:" +
                message);
    }

    public void handleAddMember(String memberID, Group group, int adminID) {
        if(adminID != group.getGroupAdminNumberID()){
            System.out.println("you are not the admin of this group");
            return;
        }
        User newMember = DBGetter.findUserByUserID(memberID);
        if(newMember == null){
            System.out.println("the member id doesn't belong to any user");
            return;
        }
        // check whether the admin is blocked by user or not ]
        if(DBGetter.BlockedByBLocker(adminID, newMember.getId() )){
            System.out.println("you are blocked by the user and can't add him");
            return;
        }
        // check whether the member isn't already in the chat
        if(DBGetter.checkMembership(newMember.getId(), group.getGroupNumberID())){
            System.out.println("user is already in the group");
            return;
        }

        UpdateDB.addMemberToGroup(group.getGroupNumberID(), newMember.getId());
        System.out.println("user successfully added to group");
    }

    public void handleRemoveMember(String memberID, Group group, int adminID) {
        if(adminID != group.getGroupAdminNumberID()){
            System.out.println("you are not the admin of this group");
            return;
        }
        User newMember = DBGetter.findUserByUserID(memberID);
        if(newMember == null){
            System.out.println("the member id doesn't belong to any user");
            return;
        }
        // check whether user is a member of the group
        if(!DBGetter.checkMembership(newMember.getId(), group.getGroupNumberID())){
            System.out.println("user isn't a member of the group");
            return;
        }

        UpdateDB.removeMemberFromGroup(newMember.getId(), group);
        System.out.println("user successfully removed from group");
    }

    public void handleBanMember(String memberID, Group group, int adminID) {
        if(adminID != group.getGroupAdminNumberID()){
            System.out.println("you are not the admin of this group");
            return;
        }
        User newMember = DBGetter.findUserByUserID(memberID);
        if(newMember == null){
            System.out.println("the member id doesn't belong to any user");
            return;
        }
        // check whether user is a member of the group
        if(!DBGetter.checkMembership(newMember.getId(), group.getGroupNumberID())){
            System.out.println("user isn't a member of the group");
            return;
        }
        // check whether user is already banned
        if(DBGetter.banCheck(group.getGroupNumberID(),newMember.getId())){
            System.out.println("user is already banned from messaging.");
            return;
        }
        UpdateDB.banMemberInGroup(newMember.getId(), group);
        System.out.println("user successfully banned from sending messages in group");
    }

    public void handleUnbanMember(String memberID, Group group, int adminID) {
        if(adminID != group.getGroupAdminNumberID()){
            System.out.println("you are not the admin of this group");
            return;
        }
        User newMember = DBGetter.findUserByUserID(memberID);
        if(newMember == null){
            System.out.println("the member id doesn't belong to any user");
            return;
        }
        // check whether user is a member of the group
        if(!DBGetter.checkMembership(newMember.getId(), group.getGroupNumberID())){
            System.out.println("user isn't a member of the group");
            return;
        }
        // check whether user is already banned
        if(!DBGetter.banCheck(group.getGroupNumberID(),newMember.getId())){
            System.out.println("user is already unbanned");
            return;
        }
        UpdateDB.unbanMemberInGroup(newMember.getId(), group);
    }

    public void handleLeaveGroup(Group group, int memberNumberID) {
        UpdateDB.removeMemberFromGroup( memberNumberID, group);
    }
}
