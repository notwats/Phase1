package view;

import controller.UserProfileController;
import database.DBGetter;
import database.PostDB;
import enums.Message;
import models.*;
import view.ChatsMenu.MainChatsView;
import view.ProfileMenu.MainProfileView;
import view.ProfileMenu.PostsEdit;

import java.sql.SQLException;
import java.util.ArrayList;


// link
public class UserProfile extends Menu {

    private User currentProfile;
    private UserProfileController controller;

    public UserProfile(String userID) {
        this.currentProfile = DBGetter.findUserByUserID(userID);
        controller= new UserProfileController(currentProfile);
    }

    public UserProfile(User user) {
        currentProfile = user;
        controller= new UserProfileController(currentProfile);
    }

    public UserProfile(int id) {
        this.currentProfile = DBGetter.findUserByUserNumberID(id);
        controller= new UserProfileController(currentProfile);
    }

    @Override
    public void run() {

        boolean bool = true;
        while (bool) {
            this.showOptions();
            String choice = getChoice();
            switch (choice) {
                case "1", "show user info" -> this.showUserInfo();
                case "2", "operations" -> this.operations();
                case "3", "posts" -> this.posts();
                case "4", "others" -> this.others();
                case "5", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
            // 1--> bio , username , followings , followers , posts' num
            // 2--> follow , message , suggestion
            // 3-->
        }
    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. Show user info"); // up
        System.out.println("2. Operations"); //middle
        System.out.println("3. Posts"); //down
        System.out.println("4. Others"); // 3 dots
        System.out.println("5. back"); // back button
    }

    private void showUserInfo() {
        if (currentProfile.getBio() != null) {
            System.out.println(currentProfile.getBio());
        } else {
            System.out.println("I have no bio :\" ");
        }
        System.out.println(currentProfile.getUsername());
        System.out.println("followings = " + currentProfile.getFollowingsID().size() + " followers = " + currentProfile.getFollowersID().size());
        if (isFollower()) {
            System.out.println("you are follower of this account");
        } else {
            System.out.println("you are not follower of this account");
        }
        System.out.println("posts = " + currentProfile.getPosts().size());
    }

    private void operations() {

        boolean bool = true;
        while (bool) {
            System.out.println("enter one of the options");
            if (!isFollower())
                System.out.println("1. Follow");
            else
                System.out.println("1. UnFollow");
            System.out.println("2. Message");
            System.out.println("3. Show suggestion");
            System.out.println("0. back");

            String choice = getChoice();

            switch (choice) {
                case "1", "follow" -> controller.followHandle() ;
                case "2", "message" -> this.messageUser();
                case "3", "show suggestion" -> this.showSuggestions();
                case "0", "back" -> bool = false;
                default -> {
                    System.out.println(Message.INVALID_CHOICE);
                }
            }
        }
    }

    private void posts() {
        // show array   UserProfileController
        ArrayList<Post> posts = new ArrayList<>();
        posts = PostDB.getPostByUserID(currentProfile.getId());

        boolean bool = true;
        while (bool) {
            Menu.showArray(posts);
            System.out.println("------------");
            System.out.println("1. select");
            System.out.println("0. back (user profile)");
            String cc = Menu.getChoice();
            switch (cc) {
                case "1","select"-> {
                    Integer num = Integer.valueOf(getInput("enter the number of the post"));
                    if (num > posts.size()) {
                        System.out.println(Message.INVALID_CHOICE);
                    } else {
                        Post wanted = posts.get(num);
                        ShowPost showPost = new ShowPost(wanted);
                        showPost.run();
                    }
                }
                case "0","back"-> {
                    bool = false;
                }
                default->
                    System.out.println(Message.INVALID_CHOICE);
            }
        }

    }

    private void others() {
        //block and mute and restrict and all
    }

    private boolean isFollowing() {
        return currentProfile.getFollowingsID().contains(Menu.getLoggedInUser().getId());
    }

    private boolean isFollower() {
        return currentProfile.getFollowersID().contains(Menu.getLoggedInUser().getId());
    }

    private void follow() {
        //followHandler

        this.run();
    }

    private void messageUser() {
        // this should check in the database and return the chat id between these users
        // or make it if it doesn't exist (should happen in handler
        // privateChatView.run();
        this.run();
    }

    private void showSuggestions() {
        int i = 1;
        for (int thisId :
                currentProfile.getSuggestionsID()) {
            assert DBGetter.findUserByUserNumberID(thisId) != null;
            System.out.println(i + DBGetter.findUserByUserNumberID(thisId).getUsername());
            i++;
        }
        System.out.println("Enter The Number of The Page You Want to Visit");
        System.out.println("If You Are Not Interested In Any Enter -1 ");
        String choice = getChoice();
        if (choice.equals("-1")) {
            this.run();
        } else {
            try {
                new UserProfile(currentProfile.getSuggestionsID().get(Integer.parseInt(choice))).run();
            } catch (Exception e) {
                System.out.println(Message.INVALID_CHOICE);
                this.operations();
            }
        }

    }

}
