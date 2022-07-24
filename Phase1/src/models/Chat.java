package models;

public abstract class Chat {
    int chatID;
    int notificationType;//mute,and frequency of being mute
    int [] pinned;

    public void search(){}

    public void pin(){}

    public void message(){}

    public void deleteChat(){}

    public void mute(){}

    public void clearHistory(){}

    public void view(){}

}

class Group extends Chat {

    int [] membersID ;
    int [] messagesID ;

    public void addMember(int userID){
        // add to data base
    }
  public void banning(int userID){

  }
}

class Personal extends Chat {
    int user1ID;
    int user2ID;
    boolean secretChat;
    boolean blockStatus;

    public void block(int userID){

    }
}