package controller;
import controller.Controller;

public class MainChatsController  extends Controller{

    //singleton
    private static MainChatsController instance = null;

    private MainChatsController() {

    }

    private static void setInstance(MainChatsController instance) {
        MainChatsController.instance = instance;
    }

    public static MainChatsController getInstance() {
        if (MainChatsController.instance == null) {
            MainChatsController.setInstance(new MainChatsController());
        }

        return MainChatsController.instance;
    }



}
