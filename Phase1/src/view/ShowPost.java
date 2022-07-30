package view;

import enums.Message;
import models.Post;

import java.awt.*;

public class ShowPost extends Menu {

    private Post currentPost;
   public ShowPost(Post post){
       this.currentPost=post;
       if (post.getAdPost()){
           System.out.println("!!!Ad!!!");
       }
   }

    @Override
    public void run() {

        boolean bool = true;
        while (bool) {
            System.out.println("-----"+currentPost.getContext()+"-----");
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

    }

    private void showComments() {
        boolean bool = true;
        while (bool) {
            System.out.println("Add comment");
            System.out.println("show comments vaa reply");
            String choice = getChoice();

        }
    }

    @Override
    protected void showOptions() {
        //  if post khodeshse edit post
        System.out.println("1. comment");
        System.out.println("2. like");
       // System.out.println("3. ");
        System.out.println("0. back");
    }
}
