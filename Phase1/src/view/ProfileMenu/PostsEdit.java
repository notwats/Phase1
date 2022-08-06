package view.ProfileMenu;

import controller.MainProfileController;
import database.PostDB;
import enums.Message;
import models.Post;
import view.MainMenu;
import view.Menu;
import view.ShowPost;

import java.util.ArrayList;
import java.util.Collections;

import static view.Menu.*;

public class PostsEdit {


    public static void run() {

        System.out.println("id" + loggedInUser.getNumberID());
        System.out.println(loggedInUser.getPosts().size());
        ArrayList<Post> posts = PostDB.getPostByUserID(loggedInUser.getNumberID());
        Collections.sort(posts);
        showArray(posts);
        if (posts.size()!=0){
        boolean bool = true;
        while (bool) {

            System.out.println("enter the number of post to select it or type back");
            String postNum = Menu.getChoice();
            if (postNum.equalsIgnoreCase("back")) {
                bool = false;
            } else {
                showOptions();
                String choice = Menu.getChoice();

                switch (choice) {
                    case "1", "delete" -> MainProfileController.deletePost(posts.get(Integer.parseInt(postNum)).getPostID());
                    case "2", "edit" -> {
                        String context = getInput("enter new post context");
                        MainProfileController.changePost(posts.get(Integer.parseInt(postNum)).getPostID(), context);
                        break;
                    }
                    case "3", "stats" -> {


                    }
                    default -> {
                        System.out.println(Message.INVALID_CHOICE);
                        PostsEdit.run();
                    }
                }
            }
        }
        }
        else System.out.println("you don't have any post");
    }

    private static void showOptions() {
        System.out.println("1. delete");
        System.out.println("2. edit the context");
        if (!loggedInUser.getIsNormal()) {
            System.out.println("3. show stats");
        }
    }
}
