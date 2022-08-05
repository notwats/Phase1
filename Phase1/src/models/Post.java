package models;

import java.util.ArrayList;
import java.util.Date;

public class Post implements Comparable<Post> {

    int postID;
    Integer senderid;

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

    public Integer getSender() {
        return senderid;
    }

    public Boolean getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Boolean normal) {
        isNormal = normal;
    }

    public void setSender(Integer sender) {
        this.senderid = sender;
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


    @Override
    public int compareTo(Post post) {
        int compareId = post.getPostID();
        return this.postID - compareId;
    }
}

