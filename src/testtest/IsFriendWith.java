package testtest;

public class IsFriendWith {
    private String user1;
    private String user2;
    private String status;

    public IsFriendWith(String user1, String user2, String status) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
    }

    public String getValue(String propertyName) {
        String str;
        if (propertyName.equals("User1"))
            return this.user1;
        else if (propertyName.equals("User2"))
            return this.user2;
        else if (propertyName.equals("Status"))
            return this.status;
        return null;
    }

    public String getID() {
        return this.user1;
    }

}
