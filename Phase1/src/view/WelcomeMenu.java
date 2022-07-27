package view;

import controller.Controller;
import controller.WelcomeController;
import database.DBGetter;
import enums.Message;
import enums.Security;
import models.*;

public class WelcomeMenu extends Menu {

    // Singleton Pattern
    private static WelcomeMenu instance = null;

    private WelcomeController controller;

    // Singleton Pattern
    private WelcomeMenu() {
        this.controller = WelcomeController.getInstance();
    }

    // Singleton Pattern
    private static void setInstance(WelcomeMenu instance) {
        WelcomeMenu.instance = instance;
    }

    // Singleton Pattern
    public static WelcomeMenu getInstance() {
        if (WelcomeMenu.instance == null) {
            WelcomeMenu.setInstance(new WelcomeMenu());
        }
        return WelcomeMenu.instance;
    }

    @Override
//    public void run() {
//        this.showOptions();
//
//        String choice = this.getChoice();
//
//        if ("1".equals(choice) || "register".equals(choice)) {
//            this.registerOptions();
//        } else if ("2".equals(choice) || "login".equals(choice)) {
//            this.login();
//        } else if ("3".equals(choice) || "exit".equals(choice)) {
//            this.exit();
//        } else {
//            System.out.println(Message.INVALID_CHOICE);
//            this.run();
//        }
//
//    }

    public void run() {
        boolean bool = true;
        while(bool) {
            this.showOptions();

            String choice = getChoice();
            switch (choice) {
                case "1", "register" -> this.registerOptions();
                case "2", "login" -> this.login();
                case "3", "exit" ->{
                    Menu.getScanner().close();
                    bool = false;
                }
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }
    }


    private void registerOptions() {
        boolean bool = true;
        while(bool) {
            System.out.println("enter register as 1. business account or 2. normal account ");
            String choice = getChoice();

            switch (choice) {
                case "1", "business account" -> {
                    this.register(false);
                    bool = false;
                }
                case "2", "normal account" -> {
                    this.register(true);
                    bool = false;
                }
                default -> System.out.println(Message.INVALID_CHOICE);

            }
        }
    }

    private void register(Boolean isNormal) {
        String userID = null;
        boolean bool= true;
        while (bool){
             userID= getInput("enter userID");
            if (DBGetter.findUserByUserID(userID)!= null){
                System.out.println("this userID exist try something else pls");
            }
            else if(userID==null){
                System.out.println("userID can't be null");
            }
            else bool=false;
        }
        String username = getInput("enter username");
        String password = getInput("enter password");
        String repeatedPassword = getInput("repeat password");
        Message message;
        if ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
            System.out.println(message);
            register(isNormal);
        } else {
            Integer questionNum = Security.randomQuestion();
            String answerS = getInput(Security.values()[questionNum].toString());
            message = this.controller.handleRegistration(userID , username, password, repeatedPassword, questionNum , answerS, isNormal);
            if (message != Message.SUCCESS) {
                System.out.println(message);
                register(isNormal);

            } else
                System.out.println("Registered successfully");
        }
    }

    private Message forgetPass(String username) {
        User user = DBGetter.findUserByUserID(username);
        if (user != null) {
            String answer = getInput(user.getSecurityQuestion().toString());
            if (answer.equalsIgnoreCase(user.getSecurityAnswer()))
                return Message.SUCCESS;
            else return Message.WRONG_CREDENTIALS;
        } else return Message.NO_EXIST;
    }

    private Boolean isAlphaNumeric(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private Message validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword))
            return Message.MISMATCH_PASSWORD;
        if (password.length() < 8)
            return Message.SHORT_PASSWORD;
        if (!this.isAlphaNumeric(password))
            return Message.NON_ALPHA_NUMERIC_PASSWORD;
        return Message.SUCCESS;
    }

    private void login() {
        System.out.println("Forget password?");
        String yesNo = getChoice();
        if (yesNo.equalsIgnoreCase("yes")) {
            String userID = getInput("enter userID");
            Message message = this.forgetPass(userID);
            if (message != Message.SUCCESS) {
                System.out.println(message);
                login();
            } else {
                String password = getInput("enter new password");
                String repeatedPassword = getInput("repeat password");
                while ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
                    System.out.println(message);
                    password = getInput("enter new password");
                    repeatedPassword = getInput("repeat password");
                }
                this.controller.handleChangingPass(userID, password);
                System.out.println("Password changed");
            }
        }
        String userID = getInput("enter userID");
        String password = getInput("enter password");
        Message message = this.controller.handleLogin(userID, password);
        //Don't have an account? Sign up
        if (message == Message.SUCCESS) {
            System.out.println("Logged in successfully");
            setLoggedInUser(DBGetter.findUserByUserID(userID));
            Controller.setLoggedInUser(DBGetter.findUserByUserID(userID));
            MainMenu.getInstance().run(); //aval mire to mainmenu safhe profile
        } else {
            System.out.println(message);
        }
    }

//    private void exit() {
//        Menu.getScanner().close();
//    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. register");
        System.out.println("2. login");
        System.out.println("3. exit");
    }
}
