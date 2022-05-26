package models;
import enums.Security;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class BusinessAcc extends User {
    public static ArrayList<BusinessAcc> list;


    public HashMap<LocalDate, Integer> profileViews = new HashMap<>();


    public BusinessAcc(String username, String password, String securityAnswer, Security securityQuestion) {
        super(username, password, securityAnswer, securityQuestion);
       // BusinessAcc.list.add(this);
    }
}
