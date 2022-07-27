package models;

public class Group extends Chat {
    int groupNumberID;
    String groupName;
    String groupID;
    int groupAdminID;

    public int getGroupAdminNumberID() {
        return groupAdminID;
    }

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


    public void addMember(int userID) {
        // add to data base
    }

    public void banning(int userID) {

    }

    public int getGroupNumberID() {
        return groupNumberID;
    }

    public String getGroupID(){
        return groupID;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", groupID='" + groupID + '\'' +
                '}';
    }
}
