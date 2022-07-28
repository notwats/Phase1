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

    public UserProfile(String userID) {
        this.currentProfile = DBGetter.findUserByUserID(userID);
    }

    public UserProfile(User user) {
        currentProfile = user;
    }

    public UserProfile(int id) {
        this.currentProfile = DBGetter.findUserByUserNumberID(id);
    }

    @Override
    public void run() {

        boolean bool = true;
        while (bool) {
            this.showOptions();
            String choice = getChoice();
            if ("1".equals(choice) || "show user info".equals(choice)) {
                this.showUserInfo();
            } else if ("2".equals(choice) || "operations".equals(choice)) {
                this.operations();
            } else if ("3".equals(choice) || "posts".equals(choice)) {
                this.posts();
            } else if ("4".equals(choice) || "others".equals(choice)) {
                this.others();
            } else if ("5".equals(choice) || "back".equals(choice)) {
                bool = false;
            } else {
                System.out.println(Message.INVALID_CHOICE);
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
            System.out.println("I have no bio :_\" ");
        }
        System.out.println(currentProfile.getUsername());
        System.out.println("followings = " + currentProfile.getFollowingsID().size() + " followers = " + currentProfile.getFollowingsID().size());
        if (isFollowing()) {
            System.out.print("you are following this account");
        } else {
            System.out.print("you are not following this account");
        }
        if (isFollower()) {
            System.out.print("you are follower of this account");
        } else {
            System.out.print("you are not follower of this account");
        }
        System.out.println("posts = " + currentProfile.getPostsID().size());
    }

    private void operations() {
        System.out.println("enter one of the options");
        System.out.println("1. Follow");
        System.out.println("2. Message");
        System.out.println("3. Show suggestion");

        String choice = getChoice();

        if ("1".equals(choice) || "follow".equals(choice)) {
            this.follow();
        } else if ("2".equals(choice) || "message".equals(choice)) {
            this.messageUser();
        } else if ("3".equals(choice) || "show suggestion".equals(choice)) {
            this.showSuggestions();
        } else {
            System.out.println(Message.INVALID_CHOICE);
            this.operations();
        }
    }

    private void posts() {
        // show array   UserProfileController
        ArrayList<Post> posts = new ArrayList<>();
        posts = PostDB.getPostByUserID(currentProfile.getId());
        Menu.showArray(posts);
        System.out.println("1. select");
        System.out.println("2. back (user profile)");
        String cc = Menu.getChoice();
        switch (cc) {
            case "select": {
                Integer num = Integer.valueOf(getInput("enter the number of the post"));
                if (num > posts.size()) {
                    System.out.println(Message.INVALID_CHOICE);
                } else {
                    Post wanted = posts.get(num);
                    ShowPost showPost = new ShowPost(wanted);
                    showPost.run();

                }
            }
            case "back": {
                return;
            }
            default:
                System.out.println(Message.INVALID_CHOICE);
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
