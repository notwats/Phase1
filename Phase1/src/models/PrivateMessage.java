package models;

import database.DBGetter;
import database.UpdateDB;

import java.util.Date;

public class PrivateMessage {
    public final int messageID;
    int firstUserID;
    int secondUserID;
    String messageText;
    int inReplyTo;
    Date date;
    int forwardedFrom;

    public PrivateMessage(int firstUserID, int secondUserID, int messageID, String messageText, int inReplyTo, Date date, int forwardedFrom) {
        this.firstUserID = firstUserID;
        this.messageID = messageID;
        this.secondUserID = secondUserID;
        this.messageText = messageText;
        this.inReplyTo = inReplyTo;
        this.date = date;
        this.forwardedFrom = forwardedFrom;
    }


    public String getMessageText() {
        return messageText;
    }

    public void editGroupMessage(String editedText ){
        messageText = editedText;
        UpdateDB.editGroupMessageTextInDatabase(editedText, messageID);
    }
    //-----
    public String copy(){return"";}

    public void edit(){}

    public void show(int senderID) {
        User user = DBGetter.findUserByUserNumberID(senderID);
        if (inReplyTo == -1) {
            System.out.println(user.username + " : " + messageText);
        } else{
            GroupMessage repMessage = DBGetter.findMessageByMessageID(inReplyTo);
            if(repMessage != null)
                System.out.println(user.username + " : " + messageText + " in rep to " + repMessage.getMessageText().substring(0, 10));
            else
                System.out.println(user.username + " : " + messageText + " in rep to deleted message ");
        }
    }
}
