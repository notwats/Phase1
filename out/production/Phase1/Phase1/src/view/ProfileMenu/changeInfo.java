package view.ProfileMenu;

import controller.MainProfileController;
import database.DBGetter;
import database.UserDB;

import enums.Message;
import view.Menu;
import view.WelcomeMenu;

import static view.Menu.*;

public class changeInfo {


    public static void run() {
        boolean bool = true;
        while (bool) {
            showOptions();
            String choice = getChoice();

            switch (choice) {
                case "1" -> {
                    boolean flag= true;
                    while (flag){
                    String userID= getInput("enter userID: ");
                        if (DBGetter.findUserByUserID(userID)!= null){
                            System.out.println("this userID exist try something else pls");
                        }
                        else if(userID==null){
                            System.out.println("userID can't be null");
                        }
                        else {
                            loggedInUser.setUserID(userID);
                            bool=false; }
                    }
                }
                case "2", "username" -> {
                        String username= getInput("enter username: ");
                        loggedInUser.setUsername(username);
                         }

                case "3", "password" -> {
                    String password = getInput("enter new password");
                    String repeatedPassword = getInput("repeat password");
                    Message message;
                    while ((message = WelcomeMenu.getInstance().validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
                        System.out.println(message);
                        password = getInput("enter new password");
                        repeatedPassword = getInput("repeat password");
                    }
                }
               // case "4", "delete" -> MainProfileController.deleteAcc();
                case "0", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
        MainProfileController.changeInfo(Menu.loggedInUser);
    }

    static void showOptions(){
        System.out.println("1. userID");
        System.out.println("2. username");
        //System.out.println("bio");
        System.out.println("3. password");
        System.out.println("0. back (settings) ");

    }
}
