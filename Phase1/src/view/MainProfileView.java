package view;

import controller.MainProfileController;

public class MainProfileView extends MainMenu{
// singleton

    private static MainProfileView instance = null;

    private final MainProfileController controller;

    private MainProfileView() {
        this.controller = MainProfileController.getInstance();

    }

    private static void setInstance(MainProfileView instance) {
        MainProfileView.instance = instance;
    }

    public static MainProfileView getInstance() {
        if (MainProfileView.instance == null) {
            MainProfileView.setInstance(new MainProfileView());
        }

        return MainProfileView.instance;
    }




}
