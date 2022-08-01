package models;

import java.util.ArrayList;
import java.util.Date;

public class Post {

    int postID;
    User sender;

    Boolean isNormal = true ;
    /*
    int imageID;
    int voiceID;

    int audioID;
     */
    int likeNumber;

    String context; // main for phase 1

    Date creationDate;
    ArrayList<Comment> comments= new ArrayList<>();
    ArrayList<User> likedUsers= new ArrayList<>();


    ArrayList<Tag> tags;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Boolean getAdPost() {

        return isNormal;
    }

    public void setAdPost(Boolean adPost) {
        isNormal = adPost;
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

    public Boolean getNormal() {
        return isNormal;
    }

    public void setNormal(Boolean normal) {
        isNormal = normal;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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

