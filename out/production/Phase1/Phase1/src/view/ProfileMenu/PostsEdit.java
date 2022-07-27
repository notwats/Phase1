package view.ProfileMenu;

import controller.MainProfileController;
import enums.Message;
import view.MainMenu;
import view.Menu;

public class PostsEdit {


    public static void run(){
        System.out.println("enter the number of post if you want edit it or type back");
      String postNum= Menu.getChoice() ;
      if (postNum.equalsIgnoreCase("back"))
      {
          MainProfileView.getInstance().run();
      }
      else {
          showOptions();
          String choice = Menu.getChoice();

          if ("1".equals(choice) || "delete".equals(choice)) {
             MainProfileController.deletePost(postNum);
          } else if ("2".equals(choice) || "edit".equals(choice)) {
              MainProfileController.changePost(postNum);
          } else {
              System.out.println(Message.INVALID_CHOICE);
              PostsEdit.run();
      }
    }
}

    private static void showOptions() {
        System.out.println("1- delete the post");
        System.out.println("2- change the context");
    }
    }
