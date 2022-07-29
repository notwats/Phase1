package enums;

import java.util.Random;

public enum Security {

    CITY("What city were you born in?"),
    EXAM("What was the first exam you failed?"),
    NICKNAME("What was your childhood nickname?"),
    FRIEND("What is the name of your favorite childhood friend?"),
    TEACHER("What was the last name of your third grade teacher?"),
    PET("What was the name of your first pet?"),
    SCHOOL("What school did you attend for sixth grade?"),
    BORN("What time of the day were you born?"),
    NAME("What is your grandmother's first name?");

    private String question;

    Security(String text) {
        this.question = text;
    }

    @Override
    public String toString() {
        return this.question;
    }

    public static Integer randomQuestion() {

        int pick = new Random().nextInt(Security.values().length);

        return pick;
       // return null;
       // Security.values()[pick]

    }
//  public static Security getSecurityNum (Integer securityNum){
//        switch (securityNum){
//            case 1 : return CITY;
//            case 2 : return EXAM;
//            case 3 : return NICKNAME;
//            case 4 : return FRIEND;
//            case 5 : return TEACHER;
//            case 6 : return PET;
//            case 7 : return SCHOOL;
//            case 8 : return BORN;
//            case 9 : return NAME;
//
//        }
//
//  }

}
