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

