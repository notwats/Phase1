package main.enums;

public enum Message {
    SUCCESS("ok"),
    NOT_CHANGED("not changed"),
    INVALID_CHOICE("invalid choice");



    private String message;
    Message(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
