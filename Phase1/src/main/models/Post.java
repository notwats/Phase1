package models;

import java.util.ArrayList;

public class Post {

    int postID;
    int senderID;

    /*
    int imageID;
    int voiceID;
    int audioID;
     */
    int likeNumber;

    String caption; // main for phase 1

    String date;
    ArrayList<Tag> tags;

    public void forward(){}
    public void edit(){}
    public void reaction(){}
    public void view(){}
    public void delete(){}
    public void report(){}
    public void comment(){}
}

