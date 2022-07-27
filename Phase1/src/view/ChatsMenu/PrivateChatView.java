package view.ChatsMenu;

import controller.PrivateChatController;
import database.DBGetter;
import enums.Message;
import models.PrivateMessage;
import models.User;
import view.Menu;

import java.util.ArrayList;
import java.util.Date;

public class PrivateChatView {
    public static User user;
    public static User friend;
    public static PrivateChatController controller = new PrivateChatController();
    public static void run() {
        boolean bool = true;
        while(bool){
            System.out.println("""
                what do you wish to do?\s
                1. send message\s
                2. check friend profile\s
                3. select a message\s
                4. return to main chats view\s
                """);

            String choice = Menu.getChoice();

            switch(choice){
                case "1", "send message" -> sendPrivateMessage(-1);
                case "2", "check friend profile" ->  checkFriendProfile();
                case "3", "select a message" -> {} //selectPrivateMessage();
                case "4", "return to main chats view" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }

        }
    }

    private static void checkFriendProfile() {
        System.out.println("please enter the number of the selected message");
        ArrayList<PrivateMessage> messages = DBGetter.findPrivateMessagesWithBothMembersID(friend.getId(), user.getId());
        int counter = 0;
        for(PrivateMessage message : messages){
            counter++;
            System.out.print(counter + " ");
            message.show(user.getId());
        }
        int choice;
        try {
            choice = Integer.parseInt(Menu.getChoice());
        } catch(Exception NumberFormatException){
            System.out.println("invalid input");
            return;
        }

        PrivateMessage message = messages.get(choice);
        boolean bool = true;
        while(bool) {
            System.out.println("""
                    what do you wish to do ?\s
                    1. forward message\s
                    2. reply to message\s
                    3. edit message\s
                    4. discard\s
                    """);

            String thisChoice = Menu.getChoice();

            switch (thisChoice) {
                case "1", "forward message" -> {
                    forwardMessage(message);
                    bool = false;
                }
                case "2", "reply to message" -> {
                    sendPrivateMessage(message.messageID);
                    bool = false;
                }
                case "3", "edit message" -> {
                    String editedText = Menu.getInput("Please Enter Edited Text");
                    //editMessage( editedText, message.messageID);
                    bool = false;}
                case "4", "discard" -> bool = false;
                default -> System.out.println("invalid command");
            }
        }
    }

    private static void forwardMessage(PrivateMessage message) {
    }

    private static void sendPrivateMessage(int inReplyTo) {
        String message = Menu.getInput("Message");
        Date dateOfNow = new Date();
        controller.handleSendMessage(message, user.getId(), friend.getId(), dateOfNow,-1, inReplyTo);
        System.out.println("your message is sent:" +
                message);
    }


}
