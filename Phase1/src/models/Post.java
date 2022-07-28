package models;

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

    String context; // main for phase 1

    LocalDateTime creationDate;
    ArrayList<Comment> comments= new ArrayList<>();
    ArrayList<User> likedUsers= new ArrayList<>();


    ArrayList<Tag> tags;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(ArrayList<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

