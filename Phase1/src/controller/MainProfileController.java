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



}
