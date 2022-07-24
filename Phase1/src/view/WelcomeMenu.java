package view;

import controller.WelcomeController;
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
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        if ("1".equals(choice) || "register".equals(choice)) {
            this.registerOptions();
        } else if ("2".equals(choice) || "login".equals(choice)) {
            this.login();
        } else if ("3".equals(choice) || "exit".equals(choice)) {
            this.exit();
        } else {
            System.out.println(Message.INVALID_CHOICE);
            this.run();
        }

    }

    private void registerOptions() {

        System.out.println("enter register as 1. business account or 2. normal account ");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "business account":
                this.register(false);
                break;
            case "2":
            case "normal account":
                this.register(true);
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
                registerOptions();
                break;
        }
        this.run();
    }

    private void register(Boolean isNormal) {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");
        String repeatedPassword = this.getInput("repeat password");
        Message message;
        if ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
            System.out.println(message);
            register(isNormal);
        } else {
            Security question = Security.randomQuestion();
            String answerS = this.getInput(question.toString());
           message = this.controller.handleRegistration(username, password, repeatedPassword, question, answerS, isNormal);
            if (message != Message.SUCCESS) {
                System.out.println(message);
                register(isNormal);

            } else
                System.out.println("Registered successfully");
        }
    }

    private Message forgetPass(String username) {
        User user = User.getUserByUsername(username);
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
        String yesNo = this.getChoice();
        if (yesNo.equalsIgnoreCase("yes")) {
            String username = this.getInput("enter username");
            Message message = this.forgetPass(username);
            if (message!=Message.SUCCESS){
                System.out.println(message);
                login();
            }
            else {
                String password = this.getInput("enter new password");
                String repeatedPassword = this.getInput("repeat password");
                while ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
                    System.out.println(message);
                    password = this.getInput("enter new password");
                    repeatedPassword = this.getInput("repeat password");
                }
                this.controller.handleChangingPass(username, password);
                System.out.println("Password changed");
            }
        }
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");
        Message message = this.controller.handleLogin(username, password);
        //Don't have an account? Sign up
        if (message == Message.SUCCESS) {
            System.out.println("Logged in successfully");
            setLoggedInUser(User.getUserByUsername(username));
            MainMenu.getInstance().run(); //aval mire to mainmenu safhe profile
        } else {
            System.out.println(message);
        }
        this.run();
    }

    private void exit() {
        Menu.getScanner().close();
    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. register");
        System.out.println("2. login");
        System.out.println("3. exit");
    }
}
