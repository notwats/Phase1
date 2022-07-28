package models;

import database.*;

import java.util.Date;

public class GroupMessage {
    int chatID;
    int receiverID;
    int senderID;
     public final int messageID;
    String messageText;
    int inReplyTo;
    Date date;
    int forwardedFrom;

    public GroupMessage(int chatID, int senderID, int messageID, String messageText, int inReplyTo, Date date, int forwardedFrom) {
        this.chatID = chatID;
        this.messageID = messageID;
        this.senderID = senderID;
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

    public void show() {
        User user = DBGetter.findUserByUserNumberID(senderID);
        if (inReplyTo == -1) {
            System.out.println(user.username + " : " + messageText);
        } else{
            GroupMessage repMessage = DBGetter.findMessageByMessageID(inReplyTo);
            if(repMessage != null )
                System.out.println(user.username + " : " + messageText + " in rep to " + repMessage.getMessageText().substring(0, 10));
            else
                System.out.println(user.username + " : " + messageText + " in rep to deleted message ");
        }
    }
        //graphic function

    public void forward(){}

    public void report(){}

    public void reply(){}

    public void copyLink(){}

    public void delete(){}

    public void reaction(){}

    //public void pin(){}

}
