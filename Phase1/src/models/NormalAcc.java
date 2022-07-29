package models;

import enums.Security;

import java.util.ArrayList;

public class NormalAcc extends User {




    public NormalAcc(String userID ,  String username, String password, String securityAnswer, Integer securityQuestion) {

        super(userID , username, password, securityAnswer, securityQuestion);
        this.isNormal= true;
    }
}