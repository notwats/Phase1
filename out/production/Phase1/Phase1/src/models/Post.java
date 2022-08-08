package models;

import database.DBGetter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Comparable<Post> {

    int postID;
    Integer senderid;

    Boolean isNormal = true;

    int likeNumber;

    String context; // main for phase 1

    LocalDate creationDate;
    ArrayList<Integer> commentsid = new ArrayList<>();
    ArrayList<Integer> likedUsersid = new ArrayList<>();


    public ArrayList<LocalDate> likesDate = new ArrayList<>();
    public ArrayList<LocalDate> viewsDate = new ArrayList<>();


    ArrayList<Tag> tags;


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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<LocalDate> getLikesDate() {
        return likesDate;
    }

    public void setLikesDate(ArrayList<LocalDate> likesDate) {
        this.likesDate = likesDate;
    }

    public ArrayList<LocalDate> getViewsDate() {
        return viewsDate;
    }

    public void setViewsDate(ArrayList<LocalDate> viewsDate) {
        this.viewsDate = viewsDate;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Integer getSenderid() {
        return senderid;
    }

    public void setSenderid(Integer senderid) {
        this.senderid = senderid;
    }

    public Boolean getNormal() {
        return isNormal;
    }

    public void setNormal(Boolean normal) {
        isNormal = normal;
    }

    public ArrayList<Integer> getCommentsid() {
        return commentsid;
    }

    public void setCommentsid(ArrayList<Integer> commentsid) {
        this.commentsid = commentsid;
    }

    public ArrayList<Integer> getLikedUsersid() {
        return likedUsersid;
    }

    public void setLikedUsersid(ArrayList<Integer> likedUsersid) {
        this.likedUsersid = likedUsersid;
    }


    @Override
    public int compareTo(Post post) {
        int compareId = post.getPostID();
        return -this.postID + compareId;
    }

    @Override
    public String toString() {

        StringBuilder ret = new StringBuilder();
        if (!isNormal){
            ret.append("---ad---");
        }

   ret.append(DBGetter.findUserByUserNumberID(senderid).getUserID()+": \n");
        ret.append(context);

   return String.valueOf(ret);
    }

}
