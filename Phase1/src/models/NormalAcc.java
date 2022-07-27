package models;

import enums.Security;

import java.util.ArrayList;

public class NormalAcc extends User {

    public static ArrayList<NormalAcc> list;


    public NormalAcc(String userID ,  String username, String password, String securityAnswer, Integer securityQuestion) {
        super(userID , username, password, securityAnswer, securityQuestion);
        list.add(this);
    }
}