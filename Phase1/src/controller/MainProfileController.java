package controller;

public class MainProfileController extends Controller {
    //singleton
    private static MainProfileController instance = null;

    private MainProfileController() {

    }

    private static void setInstance(MainProfileController instance) {
        MainProfileController.instance = instance;
    }

    public static MainProfileController getInstance() {
        if (MainProfileController.instance == null) {
            MainProfileController.setInstance(new MainProfileController());
        }

        return MainProfileController.instance;
    }


    public static String showInfo() {
        String info = "hi";
        return info;
    }

    public static void deleteAcc() {
    }

    public static void makePrivate() {
    }

    public static void notif() {
    }

    public static void changeInfo() {
    }

    public static void deletePost(String postNum) {
    }

    public static void changePost(String postNum) {
    }


    public void showAllPost() {
    }

    public static void handleNewPost(String context) {

    }

}
