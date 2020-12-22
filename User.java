//#lines 16

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user id")
    private int id;
    @SerializedName("user name")
    private String userName;
    @SerializedName("user type")
    private String userType;

    public User(int id, String userName, String userType){
        setId(id);
        setUserName(userName);
        setUserType(userType);
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
