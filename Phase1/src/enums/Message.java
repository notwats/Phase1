package enums;

public enum Message {
    INVALID_CHOICE("invalid choice"),
    SUCCESS("OK"),
    WRONG_CREDENTIALS("Wrong credentials"),
    MISMATCH_PASSWORD("mismatch password"),
    EXIST("Username already exists"),
    SHORT_PASSWORD("Passwords must be at least 8 characters in length"),
    LONG_PASSWORD("long password"),
    NON_ALPHA_NUMERIC_PASSWORD("Password must contain at least one character and one number"),
    USER_EXIST("Username already exists"),
    PRODUCT_EXIST("product exists"),
    INVALID_ROLE("invalid role"),
    WRONG_PASSWORD("password doesn't match"),
   NO_EXIST("No user with this username exists");

    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
