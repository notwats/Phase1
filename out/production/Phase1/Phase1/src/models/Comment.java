package models;

import database.DBGetter;
import database.PostDB;

import java.util.StringTokenizer;

public class Comment {
    int commentID;
    //int postID;
    Integer postID;
    Integer senderID;
    int likeNumber;
    Integer repliedToID = null; // nothing default
    //------
    String commentText;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer post) {
        this.postID = post;
    }

    public Integer getSender() {
        return senderID;
    }

    public void setSender(Integer sender) {
        this.senderID = sender;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getRepliedTo() {
        return repliedToID;
    }

    public void setRepliedTo(Integer repliedTo) {
        this.repliedToID = repliedTo;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void edit() {
    }

    public void like() {
    }//or reaction

    public void reply() {
    }

    public void report() {
    }

    public void delete() {
    }


    @Override
    public String toString() {

        StringBuilder ret = new StringBuilder();
        if (repliedToID != null && repliedToID!=0) {
            ret.append("in replied to " + PostDB.getCommentByCommentID(repliedToID).getCommentText() + "\n");
        }
        ret.append(DBGetter.findUserByUserNumberID(this.senderID).getUsername() + " : \n");
        ret.append(commentText + "\n");

        ret.append(likeNumber + " user like this comment");

        return ret.toString();
    }
}
