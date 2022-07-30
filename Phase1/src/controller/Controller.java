package controller;

import models.User;

public abstract class Controller {
    public static User loggedInUser = null;

    public static void setLoggedInUser(User loggedInUser) {
        Controller.loggedInUser = loggedInUser;
    }
}
