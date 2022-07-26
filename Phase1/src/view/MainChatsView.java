package view;

import controller.MainChatsController;
import database.ConnectionLink;
import models.Group;
import java.util.ArrayList;

public class MainChatsView extends MainMenu{

    private static MainChatsView instance = null;

    private MainChatsView() {
        MainChatsController controller = MainChatsController.getInstance();
    }

    private static void setInstance(MainChatsView instance) {
        MainChatsView.instance = instance;
    }

    public static MainChatsView getInstance() {
        if (MainChatsView.instance == null) {
            MainChatsView.setInstance(new MainChatsView());
        }

        return MainChatsView.instance;
    }

    @Override
    public void run(){
        boolean bool = true;
        while(bool) {
            this.showOptions();

            String choice = getChoice();

            switch (choice) {
                case "1", "private chats" -> showPrivate();
                case "2", "groups" -> showGroups();
                case "3", "return to main menu" -> bool = false;
                default -> {
                }
            }
        }

    }

    private void showPrivate() {
        //ArrayList<String> chats = new ArrayList<>();

        // ConnectionLink.showPrivateChats(chats);
    }

    private void showGroups(){
        ArrayList<Group> groupNames = ConnectionLink.findGroupsWithMemberID(loggedInUser.getId());

        for (int i = 0; i < groupNames.size(); i++) {
            System.out.println(i +". " +groupNames.get(i).getGroupName());
        }

        String choice = getChoice();

        for (int i = 0; i < groupNames.size(); i++) {
            if(choice.equals(groupNames.get(i).getGroupName()) || i == Integer.parseInt(choice)){
                Group group = groupNames.get(i);
                GroupView.run(group);
            }
        }
    }

    @Override
    protected void showOptions(){
        System.out.println("which kind of chat do you want ?");
        System.out.println("1.private chats");
        System.out.println("2. groups");
        System.out.println("3. return to main menu");
    }

}
