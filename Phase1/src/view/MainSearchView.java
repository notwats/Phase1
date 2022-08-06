package view;
// ad post suggestion

import controller.MainSearchController;
import enums.Message;
import models.Group;
import models.User;
import view.ChatsMenu.MainChatsView;
import view.ProfileMenu.MainProfileView;

import java.util.ArrayList;

public class MainSearchView {


    public static void run() {
        boolean bool = true;
        while (bool) {
            // show ad post ezzzzzz
            // recomendatinsss

            showOptions();
            String choice = Menu.getChoice();

            switch (choice) {
                case "1", "accounts" -> {
                    String userid = Menu.getInput("write userID");
                    User wanted = MainSearchController.searchAccounts(userid);
                    if (wanted != null) {
                        System.out.println(wanted.getUsername());
                    }
                    else {
                        System.out.println("there's no user");
                    }
                    System.out.println("1. select");
                    System.out.println("0. back (search menu)");
                    String cc = Menu.getChoice();
                    switch (cc) {
                        case "1" , "select": {
                          UserProfile profile= new UserProfile(wanted);
                          profile.run();
                        }
                        case "0","back": {
                            bool = false;
                        }
                        default:
                            System.out.println(Message.INVALID_CHOICE);
                    }

                }
                case "2", "groups" -> {
                    String groupid = Menu.getInput("write groupId");
//                    ArrayList<Group> wantedGroups = MainSearchController.searchGroups(groupid);
//                    Menu.showArray(wantedGroups);
//                    if (wantedGroups != null) {
//                        System.out.println("if you want to select one type the number else type \"back\" (to search menu)");
//                    }
                    Group wanted = MainSearchController.searchGroups(groupid);
                    if (wanted != null) {
                        System.out.println(wanted);
                    }
                    System.out.println("1. select");
                    System.out.println("2. back (search menu)");
                    String cc = Menu.getChoice();
                    switch (cc) {
                        case "select": {

                        }
                        case "back": {
                            bool = false;
                        }
                        default:
                            System.out.println(Message.INVALID_CHOICE);
                    }
                }
                case "4", "tags" -> {
                    // controller tags
                }
                case "3", "back" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);

            }
        }
    } //search in whereee tags/ accounts / groups / whatelse??


    public static void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. search for accounts"); // up
 //       System.out.println("2. search for groups");
 //       System.out.println("3. search for tags");
        // System.out.println("2. s"); // recent search :)
        // System.out.println("3. a"); // adPosts / suggestions
        System.out.println("4. back  (Main Menu)"); // back button
    }
}
