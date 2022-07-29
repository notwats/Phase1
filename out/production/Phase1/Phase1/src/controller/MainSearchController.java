package controller;

import database.DBGetter;
import models.*;

import java.util.ArrayList;

public class MainSearchController extends Controller {

    public static User searchAccounts(String userID) {
        return DBGetter.findUserByUserID(userID);
    }

    public static Group searchGroups(String groupID) {
        return DBGetter.findGroupByGroupID(groupID);
    }
//    public static ?? search(String tag){
//        //  ifexist
//        //           showallof them
//        //   ...
//
//    }

}
