package view;


import controller.MainScrolingController;
import database.PostDB;
import database.UserDB;
import enums.Message;
import models.Comment;
import models.Post;
import java.awt.*;
import java.util.ArrayList;

public class ShowPost extends Menu {

    private Post currentPost;

    public ShowPost(Post post) {
        this.currentPost = post;
        if (post.getAdPost()) {
            System.out.println("!!!Ad!!!");
        }
    }

    @Override
    public void run() {

        boolean bool = true;
        while (bool) {
            System.out.println("-----" + currentPost.getContext() + "-----");
            this.showOptions();
            String choice = getChoice();
            switch (choice) {
                case "1", "comment" -> this.showComments();
                case "2", "like" -> this.likes();
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
            // 1--> bio , username , followings , followers , posts' num
            // 2--> follow , message , suggestion
            // 3-->
        }
    }

    private void likes() {
        if (currentPost.getLikedUsers().contains(loggedInUser)) {
            System.out.println("you have liked this post already");
        } else {
            currentPost.getLikedUsers().add(loggedInUser);
            currentPost.setLikeNumber(currentPost.getLikedUsers().size());
        }
        PostDB.updatePost(currentPost);
    }

    private void showComments() {
        ArrayList<Comment> comments = PostDB.getCommentByPostID(currentPost.getPostID());
        Menu.showArray(comments);
        boolean bool = true;
        while (bool) {
            System.out.println("add comment");
            System.out.println("select a comment to reply or edit or like");
            System.out.println("0. back");
            String choice = getChoice();

            switch (choice) {
                case "1", "add" -> this.addComment();
                case "2", "select" -> this.selection();
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
    }

    private void selection() {
        Integer selectedNum = Integer.valueOf(getInput("enter the number of comment"));
        Comment selected = PostDB.getCommentByPostID(currentPost.getPostID()).get(selectedNum);
        boolean bool = true;
        while (bool) {
            System.out.println("1. reply");
            System.out.println("2. edit");
            System.out.println("3. like");
            System.out.println("0. back");
            String choice = getChoice();
            switch (choice) {
                case "1", "reply" -> this.addComment(selected);
                case "2", "edit" -> this.editComment(selected);
                case "3", "like" -> this.likeComment(selected);
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }

    }

    private void likeComment(Comment selected) {

        selected.setLikeNumber(selected.getLikeNumber() + 1);
        PostDB.updateComment(selected);
    }

    private void editComment(Comment comment) {
        if (comment.getSender() != loggedInUser.getId()) {
            System.out.println("you only can edit your comments");
        } else {
            String context = getInput("enter new context: ");
            comment.setCommentText(context);
            PostDB.updateComment(comment);
        }

    }

    private void addComment() {
        String commentText = getInput("comment text: \n");
        Comment cc = new Comment();
        cc.setPostID(currentPost.getPostID());
        cc.setCommentText(commentText);
        cc.setSender(loggedInUser.getId());
        MainScrolingController.addComment(cc);
        System.out.println("comment added successfully");
    }

    private void addComment(Comment inreplyTo) {
        String commentText = getInput("comment text: \n");
        Comment cc = new Comment();
        cc.setPostID(currentPost.getPostID());
        cc.setCommentText(commentText);
        cc.setSender(loggedInUser.getId());
        cc.setRepliedTo(inreplyTo.getCommentID()); // check it
        MainScrolingController.addComment(cc);
        System.out.println("comment added successfully");
    }

    @Override
    protected void showOptions() {
        //  if post khodeshse edit post
        System.out.println("1. comments");
        System.out.println("2. like");
        // System.out.println("3. ");
        System.out.println("0. back");
    }
}
