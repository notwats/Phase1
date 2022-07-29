package models;
import enums.Security;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class BusinessAcc extends User {


    public HashMap<LocalDate, Integer> profileViews = new HashMap<>();


    public BusinessAcc(String userID,String username, String password, String securityAnswer, Integer securityQuestion) {
        super(userID ,username, password, securityAnswer, securityQuestion);
        isNormal=false;
    }
}
