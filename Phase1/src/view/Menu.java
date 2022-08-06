package view;
// line 11 settttttttttttttt
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static User loggedInUser = null;
    public static Boolean isNormal = true;

    protected static Scanner getScanner() {
        return Menu.scanner;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static String getInput(String message) {
        System.out.println(message + ":");
        return Menu.getScanner().nextLine().trim();
    }

    public static String getChoice() {
        return Menu.getScanner().nextLine().trim().toLowerCase();
    }

    public abstract void run();

    protected abstract void showOptions();

    public static <E> void showArray(ArrayList<E> list){ // call in controller EZ
       if (list== null){
           System.out.println("there's nothing");
           return;
       }
        for (int i=0 ; i< list.size();i++){
            System.out.println(i+". " + list.get(i));
        }
    } // and select...


    // public static void start(){};
    //  public static void options(){};
}

