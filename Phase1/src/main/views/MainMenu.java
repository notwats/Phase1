package main.views;

public class MainMenu extends Menu{
    private static MainMenu instance = null;

   /* private MainMenu() {
        this.controller = MainController.getInstance();

    }*/

    private static void setInstance(MainMenu instance) {
        MainMenu.instance = instance;
    }

    public static MainMenu getInstance() {
        if (MainMenu.instance == null) {
            MainMenu.setInstance(new MainMenu());
        }

        return MainMenu.instance;
    }
    @Override
    public void run() {

    }

    @Override
    protected void showOptions() {

    }
}
