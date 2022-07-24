package view;


import controller.UserProfileController;
import enums.Message;
import models.User;


// link
public class UserProfile extends Menu {

    private int id ;
    private UserProfileController userProfileController = new UserProfileController(id);

    public UserProfile(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        if ("1".equals(choice) || "show user info".equals(choice)) {
            this.showUserInfo();
        } else if ("2".equals(choice) || "operations".equals(choice)) {
            this.operations();
        } else if ("3".equals(choice) || "posts".equals(choice)) {
            this.posts();
        }else if ("4".equals(choice) || "others".equals(choice)) {
            this.others();
        }else if ("5".equals(choice) || "back".equals(choice)) {
            return;
        }else {
            System.out.println(Message.INVALID_CHOICE);
            this.run();
        }
        // 1--> bio , username , followings , followers , posts' num
        // 2--> follow , message , suggestion
        // 3-->

    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. Show user info"); // up
        System.out.println("2. Operations"); //middle
        System.out.println("3. Posts"); //down
        System.out.println("4. Others"); // 3 dots
        System.out.println("5. back"); // back button
    }

    private void showUserInfo(){
        User thisUser = User.getUserByID(id);
        assert thisUser != null;
        if(thisUser.getBio() != null) {
            System.out.println(thisUser.getBio());
        } else{
            System.out.println("I have no bio :_\" ");
        }
        System.out.println(thisUser.getUsername());
        System.out.println("followings = " + thisUser.getFollowingsID().size() + " followers = " + thisUser.getFollowingsID().size());
        if(isFollowing()){
            System.out.print("you are following this account");
        } else {
            System.out.print("you are not following this account");
        }if(isFollower()){
            System.out.print("you are follower of this account");
        } else {
            System.out.print("you are not follower of this account");
        }
        System.out.println("posts = " + thisUser.getPostsID().size());
    }

    private void operations(){
        System.out.println("enter one of the options");
        System.out.println("1. Follow");
        System.out.println("2. Message");
        System.out.println("3. Show suggestion");

        String choice = this.getChoice();

        if ("1".equals(choice) || "follow".equals(choice)) {
            this.follow();
        } else if ("2".equals(choice) || "message".equals(choice)) {
            this.messageUser();
        } else if ("3".equals(choice) || "show suggestion".equals(choice)) {
            this.showSuggestions();
        }else {
            System.out.println(Message.INVALID_CHOICE);
            this.operations();
        }
    }

    private void posts(){

    }

    private void others(){
        //block and restrict and all
    }

    private boolean isFollowing(){
        assert User.getUserByID(id) != null;
        return User.getUserByID(id).getFollowingsID().contains(Menu.getLoggedInUser().getId());
    }

    private boolean isFollower(){
        assert User.getUserByID(id) != null;
        return User.getUserByID(id).getFollowersID().contains(Menu.getLoggedInUser().getId());

    }

    private void follow(){
        //followHandler

        this.run();
    }

    private void messageUser(){
        // this should check in the database and return the chat id between these users
        // or make it if it doesn't exist (should happen in handler
        // privateChatView.run();
        this.run();
    }

    private void showSuggestions(){
        int i = 1;
        for (int thisId :
            User.getUserByID(id).getSuggestionsID()) {
            assert User.getUserByID(thisId) != null;
            System.out.println(i + User.getUserByID(thisId).getUsername());
            i++;
        }
        System.out.println("Enter The Number of The Page You Want to Visit");
        System.out.println("If You Are Not Interested In Any Enter -1 ");
        String choice = getChoice();
        if(choice.equals("-1")){
            this.run();
        } else{
            try {
                new UserProfile(User.getUserByID(id).getSuggestionsID().get(Integer.parseInt(choice))).run();
            }catch(Exception e){
                System.out.println(Message.INVALID_CHOICE);
                this.operations();
            }
        }

    }

}
