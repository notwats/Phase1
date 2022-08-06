package controller;

import database.PostDB;
import database.UserDB;
import models.Post;
import models.User;
import view.WelcomeMenu;

import java.util.Date;

import static view.Menu.loggedInUser;

public class MainProfileController extends Controller {
    //singleton
    private static MainProfileController instance = null;

    private MainProfileController() {

    }

    private static void setInstance(MainProfileController instance) {
        MainProfileController.instance = instance;
    }

    public static MainProfileController getInstance() {
        if (MainProfileController.instance == null) {
            MainProfileController.setInstance(new MainProfileController());
        }

        return MainProfileController.instance;
    }


    public static String showInfo() {
        String info = "userID: " + loggedInUser.getUserID() +
                "\n" + "username: " + loggedInUser.getUsername() //+
                //          "\n" + "followers num : " + loggedInUser.getFollowersID().size() +
                //           "\n" + " followings num : " + loggedInUser.getFollowingsID().size()
                ;
        // bio

        return info;
    }

    public static void deleteAcc() {
        UserDB.deleteUser(loggedInUser);
        System.out.println("account deleted successfully");
        WelcomeMenu.getInstance().run();
    }

    public static void makePrivate() {
    }

    public static void notif() {
    }

    public static void changeInfo(User loggedInUser) {
        UserDB.updateUser(loggedInUser);
        System.out.println("changed successfully");
    }

    public static void deletePost(Integer postID) {
        PostDB.deletePost(postID);
        System.out.println("post deleted successfully");
    }

    public static void changePost(Integer postID, String context) {

        Post wanted = PostDB.getPostByPostID(postID);
        wanted.setContext(context);
        PostDB.updatePost(wanted);
        System.out.println("post edited successfully");
    }


    // public void showAllPost() {

    //  }

    public static void handleNewPost(String context) {
        Post post = new Post();
        post.setSender(loggedInUser.getNumberID());
        post.setContext(context);
        Date dateOfNow = new Date();
        post.setCreationDate(dateOfNow);
        post.setIsNormal(loggedInUser.getIsNormal());
        PostDB.addPost(post);
        System.out.println("Post created successfully");
    }
}
