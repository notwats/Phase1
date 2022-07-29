package view.ChatsMenu;
// method select message and two last methods comment

import controller.GroupController;
import database.DBGetter;
import database.UpdateDB;
import models.Group;
import models.GroupMessage;
import view.Menu;
import java.util.ArrayList;
import java.util.Date;

import static view.Menu.loggedInUser;

public class GroupView {
    public static Group group;
    private static final GroupController controller = new GroupController();

    static public void run(Group group) {
        GroupView.group = group;
        boolean bool = true;

        while(bool && group != null) {
            showOptions();
            String choice = Menu.getChoice();

            switch (choice) {
                case "1", "send message" -> sendMessage(-1);
                case "2", "check group profile" -> checkGroupProfile();
                case "3", "return to main chats view" -> bool = false;
                case "4", "select a message"-> selectMessage();
                default -> System.out.println(enums.Message.INVALID_CHOICE);
            }
        }
    }

    private static void selectMessage() {
        System.out.println("please enter the number of the selected message");
        ArrayList<GroupMessage> messages = DBGetter.findGroupMessagesByGroupID(group.getGroupNumberID());
        int counter = 0;
        for(GroupMessage message : messages){
            counter++;
            System.out.print(counter + " ");
            message.show();
        }
        int choice;
        try {
           choice = Integer.parseInt(Menu.getChoice());
        } catch(Exception NumberFormatException){
            System.out.println("invalid input");
            return;
        }

        GroupMessage message = messages.get(choice);
        boolean bool = true;
        while(bool) {
            System.out.println("""
                    what do you wish to do ?\s
                    1. forward message\s
                    2. reply to message\s
                    3. discard\s
                    """);

            String thisChoice = Menu.getChoice();

            switch (thisChoice) {
                case "1", "forward message" -> {
                    forwardMessage(message);
                    bool = false;
                }
                case "2", "reply to message" -> {
                    sendMessage(message.messageID);
                    bool = false;
                }
                case "3", "edit message" -> {
                    String editedText = Menu.getInput("Please Enter Edited Text");
                    editMessage( editedText, message.messageID);
                    bool = false;}
                case "4", "discard" -> bool = false;
                default -> System.out.println("invalid command");
            }
        }
    }



    public static void editMessage(String editedText, int messageID) {
        controller.handelEditMessage(editedText, messageID);
    }

    private static void forwardMessage(GroupMessage message) {
        ArrayList<Group> groupNames = DBGetter.findGroupsWithMemberID(loggedInUser.getId());
        //this has to change to chat
        for (int i = 0; i < groupNames.size(); i++) {
            System.out.println(i +". " +groupNames.get(i).getGroupName());
        }

        String choice = Menu.getChoice();

        for (int i = 0; i < groupNames.size(); i++) {
            if(choice.equals(groupNames.get(i).getGroupName()) || i == Integer.parseInt(choice)){
                Group group = groupNames.get(i);
                sendForwardedMessage(group, message);
            }
        }
    }

    private static void checkGroupProfile() {
        boolean bool = true;
        while (bool) {
            System.out.println("""
                    what do you wish to do?\s
                    1. change group name
                    2. change group id
                    3. add member
                    4. remove member
                    5. ban member
                    6. unban member
                    7. leave group
                    8. return to group view
                    """);


            String choice = Menu.getChoice();

            switch (choice){
                case "1", "change group name" -> {
                    String newName = Menu.getInput("what is the new name?");
                    controller.handleGroupNameChange(group.getGroupName(), group.getGroupNumberID(), newName);
                }
                case "2", "change group id" -> {
                    String newID = Menu.getInput("what is the new ID?");
                    controller.handleGroupIDChange(group.getGroupNumberID(), group.getGroupID(), newID);
                }
                case "3", "add member" -> addMember();
                case "4", "remove member" -> removeMember();
                case "5", "ban member" ->banMember();
                case "6", "unban member"-> unbanMember();
                case "7", "leave group" -> {
                    leaveGroup();
                    bool = false;
                }
                case "8", "return to group view" -> bool = false;
                default -> System.out.println(enums.Message.INVALID_CHOICE);
            }
        }
    }

    private static void leaveGroup() {
        controller.handleLeaveGroup(group, loggedInUser.getId());
        System.out.println("you left the group");
        group = null;
    }

    private static void banMember() {
        String memberID = Menu.getInput("who do you want to ban in the group? (Enter ID)");

        controller.handleBanMember(memberID, group, loggedInUser.getId());
        System.out.println("user successfully banned from sending messages in group");
    }

    private static void unbanMember(){
        String memberID = Menu.getInput("who do you want to unban in the group? (Enter ID)");

        controller.handleUnbanMember(memberID, group, loggedInUser.getId());
        System.out.println("user successfully unbanned. now they can send messages");
    }

    private static void removeMember() {
        String memberID = Menu.getInput("who do you want to remove from the group? (Enter ID)");

        controller.handleRemoveMember(memberID, group, loggedInUser.getId());
        System.out.println("user successfully removed from group");
    }

    private static void addMember() {
        String memberID = Menu.getInput("who do you want to add to the group? (Enter ID)");

        controller.handleAddMember(memberID, group, loggedInUser.getId());
        System.out.println("user successfully added to group");
    }

    protected static void showOptions() {
        System.out.println("""
                what do you wish to do?\s
                1. send message\s
                2. check group profile\s
                3. return to main chats view\s
                4. select a message\s
                """);

    }

    private static void sendMessage(int inReplyTo){
        String message = Menu.getInput("Message");
        Date dateOfNow = new Date();
        controller.handleSendMessage(message, loggedInUser.getId(), group.getGroupNumberID(), dateOfNow, -1, inReplyTo);
        System.out.println("your message is sent:" +
                message);
    }

    private static void sendForwardedMessage(Group group, GroupMessage  message){
        String message2 = message.getMessageText();
        Date dateOfNow = new Date();
        UpdateDB.messageCreationInGroup(message2, loggedInUser.getId(), group.getGroupNumberID(), dateOfNow,  message.messageID, -1 );
        System.out.println("forwarded to group " + group.getGroupName() + " successfully " );
    }


}
