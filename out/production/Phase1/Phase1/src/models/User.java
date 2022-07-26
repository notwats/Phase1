package models;
import enums.Security;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {
    public static ArrayList<User> allUsers = new ArrayList<>();

    int id;
    String username;
    private String password;
    ArrayList<Integer> followersID;
    ArrayList<Integer> followingsID;
    ArrayList<Integer> postsID;
    ArrayList<Integer> chatsID;
    ArrayList<Integer> groupsID;
    ArrayList<Integer> suggestionsID;

    public ArrayList<Integer> getSuggestionsID() {
        return suggestionsID;
    }

    public void setSuggestionsID(ArrayList<Integer> suggestionsID) {
        this.suggestionsID = suggestionsID;
    }

    LocalDate createDate;

    String Bio;

    String securityAnswer;
    Security securityQuestion;


    private static int mainID = 0;

    public User(String username, String password, String securityAnswer, Security securityQuestion) {

        this.id = mainID++;

        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.securityQuestion = securityQuestion;

        this.createDate = LocalDate.now();
        allUsers.add(this);
    }
    public static User getUserByUsername(String username) {
        for (User user : User.allUsers) {
            if (user.username.equals(username)) {
                return user;
            }

        }
        return null;
    }

    public static User getUserByID(int id){
        for (User user:
             User.allUsers) {
            if(user.id == id){
                return user;
            }
        }
        return null;
    }

    public void follow(int followingID) {
        //add id to database;
    }

    public void liking(int postID) {
        //add id to database;
    }

    public void commenting(int postID, String comment) {
        // new object comment
        //add id to database;
    }

    public void posting() {
        // new post object
        // add to database
    }

    public void messaging(int groupID, String massage) {
        // new massage object
        // add to data base
    }

    public void chatting(int chatID, String massage) {
        // new massage object
        // add to data base
    }

    public void deletingac() {
        //delet databases related to userID
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getFollowersID() {
        return followersID;
    }

    public ArrayList<Integer> getFollowingsID() {
        return followingsID;
    }

    public ArrayList<Integer> getPostsID() {
        return postsID;
    }

    public ArrayList<Integer> getChatsID() {
        return chatsID;
    }

    public ArrayList<Integer> getGroupsID() {
        return groupsID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getBio() {
        return Bio;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public Security getSecurityQuestion() {
        return securityQuestion;
    }

    public static int getMainID() {
        return mainID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFollowersID(ArrayList<Integer> followersID) {
        this.followersID = followersID;
    }

    public void setFollowingsID(ArrayList<Integer> followingsID) {
        this.followingsID = followingsID;
    }

    public void setPostsID(ArrayList<Integer> postsID) {
        this.postsID = postsID;
    }

    public void setChatsID(ArrayList<Integer> chatsID) {
        this.chatsID = chatsID;
    }

    public void setGroupsID(ArrayList<Integer> groupsID) {
        this.groupsID = groupsID;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setSecurityQuestion(Security securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public static void setMainID(int mainID) {
        User.mainID = mainID;
    }
}