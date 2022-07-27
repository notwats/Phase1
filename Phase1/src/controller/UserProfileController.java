package controller;

import models.User;

public class UserProfileController extends Controller {
 private User currentUser ;

    public UserProfileController(User user) {
        this.currentUser = user;
    }
}
