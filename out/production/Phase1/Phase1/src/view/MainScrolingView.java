package view;

import controller.MainProfileController;
import database.PostDB;
import enums.Message;
import models.Post;
import view.ProfileMenu.changeInfo;

import java.util.ArrayList;

import java.util.Collections;

import static view.Menu.loggedInUser;

// adPost
public class MainScrolingView {

    static ArrayList<Post> allFollowingsPosts = PostDB.getFollowingsPost(loggedInUser.getNumberID());


    static Integer whichPost = 0;

    public static void run() {

        boolean bool = true;
        while (bool) {

            // show ad post ezzzzzz
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

    static void showOptions() {
        System.out.println("1. select");
        System.out.println("2. next");
        System.out.println("0. back");
    }
}