package controller;

import database.DBGetter;
import database.UpdateDB;

import java.util.Date;

public class PrivateChatController {

    public void handleSendMessage(String message, int userID, int friendID, Date dateOfNow, int forwardedFrom, int inReplyTo){
        if(message.length() >= 50){
            System.out.println("your message is too long");
            return;
        }
        // check whether the user is blocked by the friend
        if(DBGetter.BlockedByBLocker(userID, friendID)){
            System.out.println("unfortunately you are blocked by the user so you can't send them messages");
        }

        UpdateDB.messageCreationInPrivateChat(message, userID, friendID, dateOfNow, forwardedFrom, inReplyTo);

    }
}
