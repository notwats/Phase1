package models;

import enums.Security;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {
    private final static ArrayList<User> allUsers = new ArrayList<>();

    int id;
    String username;
    private String password;
    int[] followersID;
    int[] followingsID;
    int[] postsID;
    int[] chatsID;
    int[] groupsID;
    LocalDate creatDate;

    String Bio;

    String securityAnswer;
    Security securityQuestion;


    static private int mainID = 0;

    public User(String username, String password, String securityAnswer, Security securityQuestion) {

        this.id = mainID++;

        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.securityQuestion = securityQuestion;

        this.creatDate = LocalDate.now();
        User.allUsers.add(this);
    }
    public static User getUserByUsername(String username) {
        for (User user : User.allUsers) {
            if (user.username.equals(username)) {
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

    public int[] getFollowersID() {
        return followersID;
    }

    public int[] getFollowingsID() {
        return followingsID;
    }

    public int[] getPostsID() {
        return postsID;
    }

    public int[] getChatsID() {
        return chatsID;
    }

    public int[] getGroupsID() {
        return groupsID;
    }

    public LocalDate getCreatDate() {
        return creatDate;
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

    public void setFollowersID(int[] followersID) {
        this.followersID = followersID;
    }

    public void setFollowingsID(int[] followingsID) {
        this.followingsID = followingsID;
    }

    public void setPostsID(int[] postsID) {
        this.postsID = postsID;
    }

    public void setChatsID(int[] chatsID) {
        this.chatsID = chatsID;
    }

    public void setGroupsID(int[] groupsID) {
        this.groupsID = groupsID;
    }

    public void setCreatDate(LocalDate creatDate) {
        this.creatDate = creatDate;
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