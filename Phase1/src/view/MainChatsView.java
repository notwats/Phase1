package view;

import controller.MainChatsController;
import controller.MainProfileController;

public class MainChatsView extends MainMenu{

    private static MainChatsView instance = null;

    private final MainChatsController controller;

    private MainChatsView() {
        this.controller = MainChatsController.getInstance();
    }

    private static void setInstance(MainChatsView instance) {
        MainChatsView.instance = instance;
    }

    public static MainChatsView getInstance() {
        if (MainChatsView.instance == null) {
            MainChatsView.setInstance(new MainChatsView());
        }

        return MainChatsView.instance;
    }

}
