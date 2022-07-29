package models;

import database.DBGetter;

import java.util.Date;

public class Message {
    int chatID;
    int senderID;
    public final int messageID;
    String messageText;
    int inReplyTo;
    Date date;
    int forwardedFrom;

    public Message(int chatID, int senderID, int messageID, String messageText, int inReplyTo, Date date, int forwardedFrom) {
        this.chatID = chatID;
        this.messageID = messageID;
        this.messageText = messageText;
        this.inReplyTo = inReplyTo;
        this.date = date;
        this.forwardedFrom = forwardedFrom;
    }

    public String getMessageText() {
        return messageText;
    }

    //-----
    public String copy(){return"";}

    public void edit(){}

    public void show(){
        User user = DBGetter.findUserByUserNumberID(senderID);
        System.out.println(user.username+" : " +messageText);
    } //graphic function

    public void forward(){}

    public void report(){}

    public void reply(){}

    public void copyLink(){}

    public void delete(){}

    public void reaction(){}

    //public void pin(){}

}
