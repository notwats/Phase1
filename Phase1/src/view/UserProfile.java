package view;


import controller.UserProfileController;

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
        System.out.println("3. exit"); // back button
    }
}
