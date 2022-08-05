package controller;
import database.DBGetter;
import database.UserDB;
import enums.Message;
import models.User;
import view.Menu;

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


    public Message handleRegistration(String userID ,String username , String password, String repeatedPassword, Integer questionNum, String answerS , Boolean isNormal) {
        if (this.doesUsernameExist(username)) {
            return Message.USER_EXIST;
        }

       if (isNormal){
           NormalAcc nwUser= new NormalAcc(userID ,username , password , answerS , questionNum );
           UserDB.addUser(nwUser);
       }
       else {
           BusinessAcc nwUser = new BusinessAcc( userID ,username, password, answerS, questionNum );
           UserDB.addUser(nwUser);
       }
        return Message.SUCCESS;
    }


    private Boolean doesUsernameExist(String userID) {
        return DBGetter.findUserByUserID(userID) != null;
    }

    public Message handleLogin(String userID, String password) {

        User user = DBGetter.findUserByUserID(userID);
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
        User user = DBGetter.findUserByUserID(username);
        user.setPassword(password);
        UserDB.updateUser(user);

    }
}