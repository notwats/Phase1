package view.ProfileMenu;

import controller.MainProfileController;
import models.Post;
import view.Main;
import view.Menu;

public class NewPost {

    public static void run(){
        String context= Menu.getInput("enter your post context");
        MainProfileController.handleNewPost(context);
    }

}
