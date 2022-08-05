package view;

import database.DBGetter;
import view.ChatsMenu.MainChatsView;

import static view.Menu.setLoggedInUser;

public class Main {

    public static void main(String[] args) {
      /*  WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();*/
        setLoggedInUser(DBGetter.findUserByUserID("mohsenam1"));
        MainChatsView mainChatsView = new MainChatsView();
        mainChatsView.run();

        WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();
    }

}

