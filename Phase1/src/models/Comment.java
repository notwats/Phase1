package models;

public class Comment {
    int commentID;
    //int postID;
    Post post;
    User sender;

    int likeNumber;
    Comment repliedTo = null; // it can be null
    //------
    String commentText;

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
}
