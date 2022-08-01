package view.ProfileMenu;

import controller.MainProfileController;
import database.PostDB;
import enums.Message;
import models.Post;
import view.MainMenu;
import view.Menu;
import view.ShowPost;

import java.util.ArrayList;

import static view.Menu.*;

public class PostsEdit {


    public static void run() {

        ArrayList<Post> posts = PostDB.getPostByUserID(loggedInUser.getNumberID());
        showArray(posts);
        boolean bool = true;
        while (bool) {

            System.out.println("enter the number of post if you want edit it or type back");
            String postNum = Menu.getChoice();
            if (postNum.equalsIgnoreCase("back")) {
               bool=false;
            } else {
                showOptions();
                String choice = Menu.getChoice();

                if ("1".equals(choice) || "delete".equals(choice)) {
                    MainProfileController.deletePost(posts.get(Integer.parseInt(postNum)).getPostID());
                } else if ("2".equals(choice) || "edit".equals(choice)) {
                String context = getInput("enter new post context");
                       MainProfileController.changePost(posts.get(Integer.parseInt(postNum)).getPostID(),context );
                } else {
                    System.out.println(Message.INVALID_CHOICE);
                    PostsEdit.run();
                }
            }
        }
    }

    private static void showOptions() {
        System.out.println("1- delete the post");
        System.out.println("2- change the context");
    }
}
