package controller;

import database.DBGetter;
import database.UpdateDB;
import models.User;

import java.util.Date;

public class PrivateChatController {

    public PrivateChatController() {
    }

    public void handleSendMessage(String message, int userID, int friendID, Date dateOfNow, int forwardedFrom, int inReplyTo) {
        if (message.length() >= 50) {
            System.out.println("your message is too long");
        } else {
            if (DBGetter.BlockedByBLocker(userID, friendID)) {
                System.out.println("unfortunately you are blocked by the user so you can't send them messages");
            }

            UpdateDB.messageCreationInPrivateChat(message, userID, friendID, dateOfNow, forwardedFrom, inReplyTo);
            System.out.println("your message is sent : " + message);
        }
    }

    public void handleBlockUser(int ID, int friendId) {
        User newMember = DBGetter.findUserByUserNumberID(friendId);
        if (newMember == null) {
            System.out.println("the member id doesn't belong to any user");
        } else if (DBGetter.BlockedByBLocker(newMember.getId(), ID)) {
            System.out.println("user is already blocked");
        } else {
            UpdateDB.blockerBlocks(ID, newMember.getId());
            System.out.println("user blocked successfully");
        }
    }
}
