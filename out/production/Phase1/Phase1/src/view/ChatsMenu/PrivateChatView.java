package view.ChatsMenu;

import controller.PrivateChatController;
import database.DBGetter;
import database.UpdateDB;
import enums.Message;
import models.*;
import view.MainSearchView;
import view.Menu;

import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static view.ChatsMenu.GroupView.editMessage;
import static view.Menu.loggedInUser;

public class PrivateChatView {
    public static User user;
    public static User friend;
    public static PrivateChatController controller = new PrivateChatController();
    public PrivateChatView() {
    }

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
                case "3", "select a message" ->  selectPrivateMessage();
                case "4", "return to main chats view" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }

        }
    }

    private static void checkFriendProfile(){
        boolean bool = true;

        while(bool){
            System.out.println("""
                    what do you want to do?\s
                    1. block user\s
                    2. return to chat view
                    """);

            String choice = Menu.getChoice();

            switch(choice){

                case "1", "block user" -> controller.handleBlockUser(user.getId(), friend.getId());

                case "3", "return" -> bool=false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }

    }
    private static void selectPrivateMessage() {

        ArrayList<PrivateMessage> messages = DBGetter.findPrivateMessagesWithBothMembersID(friend.getId(), user.getId());
        int counter = 0;
        if (messages.size() == 0) {
            System.out.println("there isn't any message");
        }

        System.out.println("please enter the number of the selected message");
        Iterator var2 = messages.iterator();

        PrivateMessage message;
        while(var2.hasNext()) {
            message = (PrivateMessage)var2.next();
            ++counter;
            System.out.print(counter + " ");
            message.show(user.getId());
        }

        int choice;
        try {
            choice = Integer.parseInt(Menu.getChoice());
        } catch (Exception var9) {
            System.out.println("invalid input");
            return;
        }

        message = (PrivateMessage)messages.get(choice);
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
                    editMessage( editedText, message.messageID);
                    bool = false;
                    break;
                }
                case "4", "discard" ->{ bool = false; break;}
                default -> System.out.println("invalid command");
            }
        }
    }

    private static void forwardMessage(PrivateMessage message) {
        ArrayList<Personal> contactNames = DBGetter.findChatsWithMemberID(user.getId());
        //this has to change to chat
        for (int i = 0; i < contactNames.size(); i++) {
            if(contactNames.get(i).getUser1ID() == user.getId())
                System.out.println(i +". " +contactNames.get(i).getUser2ID());
            else if(contactNames.get(i).getUser2ID() == user.getId())
                System.out.println(i +". " +contactNames.get(i).getUser1ID());
        }

        String choice = Menu.getChoice();


        try {
            for(int i = 0; i < contactNames.size(); ++i) {
                User contact;
                if (i == Integer.parseInt(choice) && ((Personal)contactNames.get(i)).getUser1ID() == user.getId()) {
                    contact = DBGetter.findUserByUserNumberID(((Personal)contactNames.get(i)).getUser2ID());
                    sendForwardedMessage(contact, message);
                }

                if (i == Integer.parseInt(choice) || ((Personal)contactNames.get(i)).getUser2ID() == user.getId()) {
                    contact = DBGetter.findUserByUserNumberID(((Personal)contactNames.get(i)).getUser1ID());
                    sendForwardedMessage(contact, message);
                }
            }
        } catch (Exception var5) {
            System.out.println("not valid");
        }

    }

    private static void sendPrivateMessage(int inReplyTo) {
        String message = Menu.getInput("Message");
        Date dateOfNow = new Date();
        controller.handleSendMessage(message, user.getId(), friend.getId(), dateOfNow,-1, inReplyTo);
        System.out.println("your message is sent:" +
                message);
    }

    private static void sendForwardedMessage(User receiver, PrivateMessage  message){
        String message2 = message.getMessageText();
        Date dateOfNow = new Date();
        UpdateDB.messageCreationInPrivateChat(message2, loggedInUser.getId(), receiver.getId(), dateOfNow,  message.messageID, -1 );
        System.out.println("forwarded to username " + receiver.getUsername() + " successfully " );
    }

}
