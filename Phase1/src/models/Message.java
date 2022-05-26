package models;

public class Message {
    int chatID;
    int messageID;
    int reactions;
    String messageText;
    int inReplyTo;
    //the message ID of the replied message
    //will be saved here if it's null then
    //the message isn't replying to anything
    boolean seen;
    boolean sent;
    boolean forwarded;
    String date;

    //-----
    public String copy(){return"";}

    public void edit(){}

    public void show(){} //graphic function

    public void forward(){}

    public void report(){}

    public void reply(){}

    public void copyLink(){}

    public void delete(){}

    public void reaction(){}

    //public void pin(){}

}
