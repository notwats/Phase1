package view;

import database.DBGetter;

public class Main {

    public static void main(String[] args) {
        WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();

    }

}

