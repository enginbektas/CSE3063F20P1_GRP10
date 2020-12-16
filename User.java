//#lines 16

import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String userType;
    private ArrayList<Dataset> datasetsAssigned;

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

    public ArrayList<Dataset> getDatasetsAssigned() {
        return datasetsAssigned;
    }

    public void setDatasetsAssigned(ArrayList<Dataset> datasetsAssigned) {
        this.datasetsAssigned = datasetsAssigned;
    }
}
