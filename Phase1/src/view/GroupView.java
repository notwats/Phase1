package view;

import database.ConnectionLink;
import models.Group;
import models.Message;

import java.util.ArrayList;
import java.util.Date;

import static view.Menu.loggedInUser;

public class GroupView {
    public static Group group;
    static public void run(Group group) {
        GroupView.group = group;
        boolean bool = true;

        while(bool) {
            showOptions();
            String choice = Menu.getChoice();

            switch (choice) {
                case "1":
                case "send message":
                    sendMessage(-1);
                case "2":
                case "check group profile":
                    checkGroupProfile();
                case "3":
                case "return to main chats view":
                    bool = false;
                    break;
                case "4":
                case "select a message":
                    selectMessage();

            }
        }
    }

    private static void selectMessage() {
        System.out.println("please enter the number of the selected message");
        ArrayList<Message> messages = ConnectionLink.findGroupMessagesByGroupID(group.getGroupID());
        int counter = 0;
        for(Message message : messages){
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

        Message message = messages.get(choice);
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
                case "1", "forward message" -> forwardMessage(message);
                case "2", "reply to message" -> sendMessage(message.messageID);
                case "3", "discard" -> bool = false;
                default -> System.out.println("invalid command");
            }
        }
    }

    private static void forwardMessage(Message message) {
        ArrayList<Group> groupNames = ConnectionLink.findGroupsWithMemberID(loggedInUser.getId());

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
        ConnectionLink.messageCreationInGroup(message, loggedInUser.getId(), group.getGroupID(), dateOfNow, -1, inReplyTo );
        ArrayList<Message> messages = ConnectionLink.findGroupMessagesByGroupID(group.getGroupID());
        for(Message message1 : messages){
            message1.show();
        }

    }

    private static void sendForwardedMessage(Group group, Message  message){
        String message2 = message.getMessageText();
        Date dateOfNow = new Date();
        ConnectionLink.messageCreationInGroup(message2, loggedInUser.getId(), group.getGroupID(), dateOfNow,  message.messageID, -1 );
        System.out.println("forwarded to group " + group.getGroupName() + " successfully " );


    }


}
