package view.ProfileMenu;

import controller.MainProfileController;
import enums.Message;
import view.Menu;

public class Settings extends Menu {

    private static Settings instance = null;

    private Settings() {

    }

    private static void setInstance(Settings instance){Settings.instance = instance;
    }

    public static Settings getInstance() {
        if (Settings.instance == null) {
            Settings.setInstance(new Settings());
        }

        return Settings.instance;
    }



    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();

        if ("1".equals(choice) || "info".equals(choice)) {
            MainProfileController.changeInfo();
        } else if ("2".equals(choice) || "notification".equals(choice)) {
            MainProfileController.notif();
        } else if ("3".equals(choice) || "private".equals(choice)) {
            MainProfileController.makePrivate();
        }else if ("4".equals(choice) || "delete".equals(choice)) {
            MainProfileController.deleteAcc();
        }else if ("5".equals(choice) || "back".equals(choice)) {
            return;
        }else {
            System.out.println(Message.INVALID_CHOICE);
            this.run();
        }
        // 1--> bio , username  , userID , pass , ...
        // 2--> log out , private account,
        // 3-->
    }


    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. change info"); //
        System.out.println("2. notifications"); // block mute/
        System.out.println("3. make your account private"); //
        System.out.println("4. delete account"); //
        System.out.println("5. back  (Profile)"); // back button
        // notifications

    }
}
