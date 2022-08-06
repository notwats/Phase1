package controller;

import database.UserDB;
import models.User;
import view.Menu;

import static view.Menu.loggedInUser;

public class UserProfileController extends Controller {

    User currentProfile;

    public UserProfileController(User user) {
        this.currentProfile = user;
    }

    public static void block(User currentUser) {
        // logged in user do with current user
    }

    public static void mute(User currenUser) {

    }

    public void followHandle() {
        if (isFollower()) {
            UserDB.unFollow(loggedInUser, currentProfile);
            loggedInUser.getFollowingsID().remove(currentProfile.getNumberID());
            currentProfile.getFollowersID().remove(loggedInUser.getNumberID());
            System.out.println("unfollowed successfully");
        } else {
            UserDB.follow(loggedInUser, currentProfile);
            loggedInUser.getFollowingsID().add(currentProfile.getNumberID());
            currentProfile.getFollowersID().add(loggedInUser.getNumberID());
            System.out.println("followed successfully");

        }

    }

    // public static ArrayList<Post> showAllPost(User )
    private boolean isFollowing() {
        return currentProfile.getFollowingsID().contains(Menu.getLoggedInUser().getId());
    }

    private boolean isFollower() {
        return currentProfile.getFollowersID().contains(Menu.getLoggedInUser().getId());
    }


}
