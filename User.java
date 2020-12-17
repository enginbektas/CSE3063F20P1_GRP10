//#lines 16

import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String userType;
    private transient UserPerformanceMetrics userPerformanceMetrics;
    private transient ArrayList<Integer> datasetIds;
    public User(int id, String userName, String userType){
        setId(id);
        setUserName(userName);
        setUserType(userType);
        userPerformanceMetrics = new UserPerformanceMetrics(this);
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

    public UserPerformanceMetrics getUserPerformanceMetrics() {
        return userPerformanceMetrics;
    }

    public void setUserPerformanceMetrics(UserPerformanceMetrics userPerformanceMetrics) {
        this.userPerformanceMetrics = userPerformanceMetrics;
    }

    public ArrayList<Integer> getDatasetIds() {
        return datasetIds;
    }
}
