package models;

public class Group extends Chat {
    int groupNumberID;
    String groupName;
    String groupID;
    int groupAdminID;
    String description;

    public void setGroupNumberID(int groupNumberID) {
        this.groupNumberID = groupNumberID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupAdminID(int groupAdminID) {
        this.groupAdminID = groupAdminID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMember(int userID) {
        // add to data base
    }

    public void banning(int userID) {

    }

    public int getGroupID() {
        return groupNumberID;
    }
}
