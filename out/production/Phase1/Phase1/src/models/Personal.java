package models;

public class Personal extends Chat {
    int user1ID;
    int user2ID;
    boolean secretChat;

    public int getUser1ID() {
        return user1ID;
    }

    public int getUser2ID() {
        return user2ID;
    }

    boolean blockStatus;

    public Personal(int first_user_id, int second_user_id, String creation_date) {
        this.user1ID = first_user_id;
        this.user2ID = second_user_id;
    }

    public void block(int userID) {

    }
}
