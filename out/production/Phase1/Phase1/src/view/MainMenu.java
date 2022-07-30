package view;

import enums.Message;
import view.ChatsMenu.MainChatsView;
import view.ProfileMenu.MainProfileView;

// where user can choose 4 option!?
public class MainMenu extends Menu{


    private static MainMenu instance = null;

    // private final MainProfileController controller;

    private static void setInstance(MainMenu instance) {
        MainMenu.instance = instance;
    }

    public static MainMenu getInstance() {
        if (MainMenu.instance == null) {
            MainMenu.setInstance(new MainMenu());
        }

        return MainMenu.instance;
    }

    @Override
    public void run() {
        boolean bool = true;
        while(bool) {
            this.showOptions();
            String choice = getChoice();

            switch (choice) {
                case "1", "profile" -> MainProfileView.getInstance().run();
                case "2", "chats" -> MainChatsView.getInstance().run();
                case "3", "posts" -> {}//this.posts();
                case "4", "search" -> MainSearchView.run();
                case "5", "logout" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);

            }
        }
    }

    @Override
    protected void showOptions() {
        System.out.println("""
                enter one of the options\s
                1. profile\s
                2. chats\s
                3. posts\s
                4. search\s
                5. logout
                """);
    }
}
