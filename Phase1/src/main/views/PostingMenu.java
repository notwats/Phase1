package main.views;

import main.controllers.PostingController;
import main.enums.Message;

public class PostingMenu extends Menu{

    private PostingController controller;
    @Override
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        switch (choice) {
            case "1" -> this.createPost();
            case "2" -> MainMenu.getInstance().run();
            default -> System.out.println(Message.INVALID_CHOICE);
        }

    }

    private void createPost(){
        String postText = this.getInput("please enter the text you want to post : ");

        System.out.println("Post?\n1.post\n.discard");

        String choice = this.getChoice();

        Message message  = Message.NOT_CHANGED;
        switch (choice){
            case "1" ->  message = this.controller.handlePost();
            case "2" -> this.run();
        }

        if( message == Message.SUCCESS){
            System.out.println("posted Successfully");
        } else{
            System.out.println(message);
        }

        MainMenu.getInstance().run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Do you want to create a post?(enter the number)");
        System.out.println("1.YES");
        System.out.println("2.NO");
    }
}
