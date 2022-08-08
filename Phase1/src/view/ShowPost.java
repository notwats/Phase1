package view;

import controller.MainScrolingController;
import database.DBGetter;
import database.PostDB;
import enums.Message;
import models.Comment;
import models.Post;
import models.User;

import java.util.ArrayList;

public class ShowPost extends Menu {

    private Post currentPost;

    public ShowPost(Post post) {
        this.currentPost = post;
        if (!post.getIsNormal()) {
            System.out.println("!!!Ad!!!");
        }
    }

    @Override
    public void run() {
        PostDB.newView(currentPost.getPostID());
        boolean bool = true;
        while (bool) {
            System.out.println("-----" + currentPost + "-----");
            this.showOptions();
            String choice = getChoice();
            switch (choice) {
                case "1", "comment" -> this.showComments();
                case "2", "like" -> this.likes();
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
    }

    private void likes() {
        ArrayList<User> likedUsers = new ArrayList<>();
        for (Integer id : currentPost.getLikedUsersid()) {
            likedUsers.add(DBGetter.findUserByUserNumberID(id));
        }
        showArray(likedUsers);
        System.out.println("---------");
        boolean bool = true;
        while (bool) {
            System.out.println("1. like");
            System.out.println("0. back");

            String choice = getChoice();
            switch (choice) {
                case "1", "like" -> {
                    if (currentPost.getLikedUsersid().contains(loggedInUser.getNumberID())) {
                        System.out.println("you have liked this post already");
                    } else {
                        currentPost.getLikedUsersid().add(loggedInUser.getNumberID());
                        currentPost.setLikeNumber(currentPost.getLikedUsersid().size());
                        PostDB.addLike(currentPost.getPostID(), loggedInUser.getNumberID());
                        System.out.println("liked");
                    }
                }
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
    }

    private void showComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Integer id : PostDB.getCommentsIDByPostID(currentPost.getPostID())) {
            comments.add(PostDB.getCommentByCommentID(id));
        }
        Menu.showArray(comments);
        boolean bool = true;
        while (bool) {
            System.out.println("1. add comment");
            System.out.println("2. select a comment to reply or edit or like");
            System.out.println("0. back");
            String choice = getChoice();
            switch (choice) {
                case "1", "add" -> this.addComment();
                case "2", "select" -> this.selection(comments);
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
    }

    private void selection(ArrayList<Comment> comments) {
        Integer selectedNum = Integer.valueOf(getInput("enter the number of comment"));
        Comment selected = comments.get(selectedNum);
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
        cc.setRepliedTo(null);
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
