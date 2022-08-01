package view;

import database.DBGetter;

public class Main {

    public static void main(String[] args) {
        /*WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();*/

        int memberID = DBGetter.findUserByUserID("mohsenam1").getId();
        System.out.println(memberID);
        System.out.println(DBGetter.findGroupsWithMemberID(memberID));
    }

}

