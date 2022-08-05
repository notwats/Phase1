package view;

import database.PostDB;
import enums.Message;
import models.Post;

import java.util.ArrayList;

import static view.Menu.loggedInUser;

// adPost
public class MainScrollingView {

    static ArrayList<Post> allFollowingsPosts = PostDB.getFollowingsPost(loggedInUser.getNumberID());

    static Integer whichPost = 0;

    public static void run() {

        if (allFollowingsPosts.size()!=0){
        boolean bool = true;
        while (bool) {

            // show ad post ezzzzzz
            if (!allFollowingsPosts.get(whichPost).getIsNormal()){
                System.out.println("----AD----");
            }
            System.out.println(allFollowingsPosts.get(whichPost).getContext());
            showOptions();
            String choice = Menu.getChoice();

            switch (choice) {
                case "1", "select" -> {
                    ShowPost showPost = new ShowPost(allFollowingsPosts.get(whichPost));
                    showPost.run();
                }
                case "2", "next" -> whichPost += 1;

                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }

        }
        }
        else {
            System.out.println("No post to show");
        }
    }

    static void showOptions() {
        System.out.println("1. select");
        System.out.println("2. next");
        System.out.println("0. back (main menu)");
    }
}