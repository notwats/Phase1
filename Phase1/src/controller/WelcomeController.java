package controller;

import enums.Message;
import enums.Security;
import models.BusinessAcc;
import models.NormalAcc;
import models.User;
import view.Menu;

import javax.jws.soap.SOAPBinding;

public class WelcomeController extends Controller {
    //singleton
    private static void setInstance(WelcomeController instance) {
        WelcomeController.instance = instance;
    }

    private static WelcomeController instance = null;

    private WelcomeController() {

    }

    public static WelcomeController getInstance() {
        if (WelcomeController.instance == null) {
            WelcomeController.setInstance(new WelcomeController());
        }

        return WelcomeController.instance;
    }


    public Message handleRegistration(String username, String password, String repeatedPassword, Security question, String answerS , Boolean isNormal) {
        if (this.doesUsernameExist(username)) {
            return Message.USER_EXIST;
        }

       if (isNormal){
           NormalAcc nwUser= new NormalAcc(username , password , answerS , question );
           //NormalAcc.list.add(nwUser);
       }
       else {
           BusinessAcc nwUser = new BusinessAcc(username, password, answerS, question);
           //BusinessAcc.list.add(nwUser);
       }
        return Message.SUCCESS;
    }


    private Boolean doesUsernameExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public Message handleLogin(String username, String password) {

        User user = User.getUserByUsername(username);
        if (user != null ) {
            if (user.getPassword().equals(password)){
                Menu.setLoggedInUser(user);
                return Message.SUCCESS;
            }
            else return Message.WRONG_PASSWORD;
        }
        else return Message.NO_EXIST;
    }


    public void handleChangingPass(String username, String password) {
        User user = User.getUserByUsername(username);
        user.setPassword(password);
    }
}