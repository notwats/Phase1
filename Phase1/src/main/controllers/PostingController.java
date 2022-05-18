package main.controllers;

import main.enums.Message;

public class PostingController extends Controller{
    private static PostingController instance = null;

    private PostingController() {

    }

    private static void setInstance(PostingController instance) {
        PostingController.instance = instance;
    }

    public static PostingController getInstance() {
        if (PostingController.instance == null) {
            PostingController.setInstance(new PostingController());
        }

        return PostingController.instance;
    }

    public Message handlePost(){
//        if (this.doesUsernameExist(username)) {
//            return Message.USER_EXIST;
//        }
//        Message message;
//        if ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
//            return message;
//        }
//        new Customer(username, password);
        return Message.SUCCESS;
    }
}
