package models;

import java.time.LocalDateTime;
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

    LocalDateTime date;
    ArrayList<Tag> tags;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void forward(){}
    public void edit(){}
    public void reaction(){}
    public void view(){}
    public void delete(){}
    public void report(){}
    public void comment(){}
}

