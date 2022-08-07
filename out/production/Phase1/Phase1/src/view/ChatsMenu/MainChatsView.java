package view.ChatsMenu;

import controller.MainChatsController;
import database.DBGetter;
import enums.Message;
import models.Group;
import database.UpdateDB;
import models.Personal;
import models.User;
import view.MainMenu;
import view.Menu;

import java.util.ArrayList;

public class MainChatsView extends MainMenu {
    private static MainChatsView instance = null;

    private final MainChatsController controller = new MainChatsController();

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
                case "3", "create or delete group" -> groupSettings();
                case "4", "create or delete private chat" -> privateChatSettings();
                case "5", "return to main menu" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }
        }

    }

    private void privateChatSettings() {
        boolean bool = true;
        while(bool){
            System.out.println("""
                    what do you wish to do?\s
                    1. delete a chat\s
                    2. create a chat\s
                    3. return to main chats view""");

            String choice = getChoice();

            switch (choice){
                case "1", "delete a chat" -> privateChatDelete();
                case "2", "create a chat" -> {
                    String receiverID = Menu.getInput("who do you want to message? (Enter ID)");
                    controller.handleCreatePrivateChat(receiverID, loggedInUser.getId());
                }
                case "3", " return to main chats view" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }

        }
    }

    private void privateChatDelete() {
        ArrayList<Personal> chats = this.controller.handleShowPrivateChats(loggedInUser.getId());

        int choice;
        for(choice = 0; choice < chats.size(); ++choice) {
            if (((Personal)chats.get(choice)).getUser1ID() == loggedInUser.getId()) {
                System.out.println(choice + 1 + ". " + ((Personal)chats.get(choice)).getUser2ID() + " " + DBGetter.findUserByUserNumberID(((Personal)chats.get(choice)).getUser2ID()).getUsername());
            } else if (((Personal)chats.get(choice)).getUser2ID() == loggedInUser.getId()) {
                System.out.println(choice + 1 + ". " + ((Personal)chats.get(choice)).getUser1ID() + " " + DBGetter.findUserByUserNumberID(((Personal)chats.get(choice)).getUser2ID()).getUsername());
            }
        }

        try {
            choice = Integer.parseInt(getChoice());
        } catch (Exception var5) {
            System.out.println("invalid choice");
            return;
        }

        try {
            for(int i = 0; i < chats.size(); ++i) {
                if (choice == ((Personal)chats.get(i)).getUser1ID() || choice == ((Personal)chats.get(i)).getUser2ID() || i + 1 == choice) {
                    if (choice == i + 1) {
                        if (((Personal)chats.get(i)).getUser2ID() == loggedInUser.getId()) {
                            choice = ((Personal)chats.get(i)).getUser1ID();
                        } else if (((Personal)chats.get(i)).getUser1ID() == loggedInUser.getId()) {
                            choice = ((Personal)chats.get(i)).getUser2ID();
                        }
                    }

                    User friend = DBGetter.findUserByUserNumberID(choice);
                    UpdateDB.deletePrivateChat(friend.getId(), loggedInUser.getId());
                    return;
                }
            }
        } catch (Exception var6) {
            System.out.println("your choice is invalid");
        }

    }

    private void groupSettings() {
        boolean bool = true;
        while(bool){
            System.out.println("""
                    what do you wish to do?\s
                    1. delete a group\s
                    2. create a group\s
                    3. return to main chats view""");

            String choice = getChoice();

            switch (choice){
                case "1", "delete a group" -> groupDelete();
                case "2", "create a group" -> {
                    String groupName = getInput("Please Enter Group's name:");
                    String groupID = getInput("Please Enter Group's ID:");
                    controller.handleCreateGroup(groupID, groupName, loggedInUser.getId());
                }
                case "3", " return to main chats view" -> bool = false;
                default -> System.out.println(Message.INVALID_CHOICE);
            }

        }
    }

    private void groupDelete() {
        ArrayList<Group> groupNames = controller.handleShowGroups(loggedInUser.getId());

        if(groupNames.size() == 0)
            return;
        for (int i = 0; i < groupNames.size(); i++) {
            System.out.println(i +". " +groupNames.get(i).getGroupName());
        }

        boolean bool = true;

        while(bool) {
            String choice = getChoice();

            for (int i = 0; i < groupNames.size(); i++) {
                try {

                    if (choice.equals(groupNames.get(i).getGroupName()) || i == Integer.parseInt(choice)) {
                        Group group = groupNames.get(i);
                        controller.handleDeleteGroup(group, loggedInUser.getId());
                        System.out.println("group " + groupNames.get(i).getGroupName() + " deleted successfully");
                        bool = false;
                        break;
                    }
                } catch (Exception NumberFormatException) {
                    System.out.println("Invalid Choice");
                }
            }
        }

    }

    private void showPrivate() {
        ArrayList<Personal> chats = this.controller.handleShowPrivateChats(loggedInUser.getId());
        if (chats != null && chats.size() != 0) {
            int choice;
            for(choice = 0; choice < chats.size(); ++choice) {
                if (((Personal)chats.get(choice)).getUser1ID() == loggedInUser.getId()) {
                    System.out.println(choice + ". " + ((Personal)chats.get(choice)).getUser2ID());
                } else if (((Personal)chats.get(choice)).getUser2ID() == loggedInUser.getId()) {
                    System.out.println(choice + ". " + ((Personal)chats.get(choice)).getUser1ID());
                }
            }

            try {
                choice = Integer.parseInt(getChoice());
            } catch (Exception var5) {
                System.out.println("invalid choice");
                return;
            }

            for(int i = 0; i < chats.size(); ++i) {
                if (choice == ((Personal)chats.get(i)).getUser1ID() || choice == ((Personal)chats.get(i)).getUser2ID() || i == choice) {
                    if (i == choice) {
                        if (((Personal)chats.get(i)).getUser2ID() == loggedInUser.getId()) {
                            choice = ((Personal)chats.get(i)).getUser1ID();
                        } else if (((Personal)chats.get(i)).getUser1ID() == loggedInUser.getId()) {
                            choice = ((Personal)chats.get(i)).getUser2ID();
                        }
                    }

                    User friend = DBGetter.findUserByUserNumberID(choice);
                    PrivateChatView.friend = friend;
                    PrivateChatView.user = loggedInUser;
                    PrivateChatView.run();

                    assert friend != null;

                    System.out.println("chat with " + friend.getUsername() + "was deleted successfully");
                }
            }

        } else {
            System.out.println("You Have No Chats. Please Make Some!");
        }
    }

    private void showGroups() {
        ArrayList<Group> groupNames = this.controller.handleShowGroups(loggedInUser.getId());
        boolean bool = true;

        while(bool) {
            for(int i = 0; i < groupNames.size(); ++i) {
                System.out.println(i + 1 + ". " + ((Group)groupNames.get(i)).getGroupName());
            }

            String choice = getChoice();

            try {
                for(int i = 0; i < groupNames.size(); ++i) {
                    if (choice.equals(((Group)groupNames.get(i)).getGroupName()) || i + 1 == Integer.parseInt(choice)) {
                        Group group = (Group)groupNames.get(i);
                        if (!this.controller.handleEnteringGroup(group, loggedInUser.getId())) {
                            System.out.println("you either left this group or have been removed from it");
                            bool = false;
                            break;
                        }

                        GroupView.run(group);
                        bool = false;
                    }
                }
            } catch (Exception var6) {
                System.out.println(Message.INVALID_CHOICE);
            }
        }

    }

    @Override
    protected void showOptions(){
        System.out.println("""
                which kind of chat do you want \s
                1.private chats\s
                2. groups\s
                3. add or remove group\s
                4. add or remove private chat\s
                5. return to main menu""");
    }
}
