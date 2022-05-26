package models;
import enums.Security;
import java.util.ArrayList;

public class NormalAcc extends User {

    public static ArrayList<NormalAcc> list;


    public NormalAcc(String username, String password, String securityAnswer, Security securityQuestion) {
        super(username, password, securityAnswer, securityQuestion);
       // list.add(this);
    }
}