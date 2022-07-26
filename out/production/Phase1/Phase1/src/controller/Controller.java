package controller;

import models.User;

public abstract class Controller {
    private static User loggedInUser = null;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Controller.loggedInUser = loggedInUser;
    }
}
