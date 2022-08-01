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
        boolean bool = true;
        while(bool) {
            this.showOptions();
            String choice = getChoice();

            switch (choice) {
                case "1", "info" -> changeInfo.run();
                case "2", "notification" -> MainProfileController.notif();
                case "3", "private" -> MainProfileController.makePrivate();
                case "4", "delete" ->{
                        bool=false;
                        MainProfileController.deleteAcc(); }
                case "0", "back" -> bool=false;
                default -> {
                    System.out.println(Message.INVALID_CHOICE);
                }
            }
        }
    }


    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. change info"); //
     //   System.out.println("2. notifications"); // block mute/
    //    System.out.println("3. make your account private"); //
        System.out.println("4. delete account"); //
        System.out.println("0. back  (Profile)"); // back button
        // notifications

    }
}
