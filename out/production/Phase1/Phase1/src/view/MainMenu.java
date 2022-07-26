package view;

import enums.Message;
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
        this.showOptions();
        String choice = this.getChoice();

        if ("1".equals(choice) || "profile".equals(choice)) {
            MainProfileView.getInstance().run();
        } else if ("2".equals(choice) || "chats".equals(choice)) {
         MainChatsView.getInstance().run();
        } else if ("3".equals(choice) || "posts".equals(choice)) {
          //  this.posts();
        } else if ("4".equals(choice) || "search".equals(choice)) {
           // this.others();
        } else {
            System.out.println(Message.INVALID_CHOICE);
            this.run();
        }
    }
    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. profile");
        System.out.println("2. chats"); //
        System.out.println("3. posts");
        System.out.println("4. search");
    }
}
