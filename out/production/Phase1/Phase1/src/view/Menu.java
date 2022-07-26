package view;
import models.*;
import java.util.Scanner;


    public abstract class Menu {
        private static final Scanner scanner = new Scanner(System.in);
        public static User loggedInUser = null;


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

       // public static void start(){};
      //  public static void options(){};
    }

