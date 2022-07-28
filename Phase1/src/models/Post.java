package models;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    int postID;
    User sender;

    /*
    int imageID;
    int voiceID;
    int audioID;
     */
    int likeNumber;

    String caption; // main for phase 1

    LocalDateTime creationDate;
    ArrayList<Comment> comments= new ArrayList<>();
    ArrayList<User> likedUsers= new ArrayList<>();



    ArrayList<Tag> tags;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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

