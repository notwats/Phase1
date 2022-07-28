package view.ProfileMenu;

import controller.MainProfileController;
import enums.Message;
import view.MainMenu;
import view.Menu;

import java.util.Set;

public class MainProfileView extends MainMenu {
// singleton

    private static MainProfileView instance = null;

    private final MainProfileController controller;
    private final Settings settings;

    private MainProfileView() {
        this.settings = Settings.getInstance();
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

    @Override
    public void run() {
        this.showOptions();
        String choice = getChoice();

        if ("1".equals(choice) || "info".equals(choice)) {
            this.showInfo();
        } else if ("2".equals(choice) || "settings".equals(choice)) {
            settings.run();
        } else if ("3".equals(choice) || "all posts".equals(choice)) {
            this.allPosts();
        } else if ("4".equals(choice) || "new post".equals(choice)) {
            this.newPost();
        } else if ("5".equals(choice) || "back".equals(choice)) {
           MainMenu.getInstance().run();
        } else {
            System.out.println(Message.INVALID_CHOICE);
            this.run();
        }
        // 1--> bio , username , followings , followers , posts' num
        // 2--> log out , private account,
        // 3-->
    }

    private void newPost() {
        NewPost.run();
    }

    private void allPosts() {
       PostsEdit.run();
    }

    private void showInfo() {
        System.out.println( MainProfileController.showInfo());
    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. info"); // up
        System.out.println("2. settings"); //
        System.out.println("3. all posts"); //down
        System.out.println("4. new post"); // plus
        System.out.println("5. back  (Main Menu)"); // back button
    }


}