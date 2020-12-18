//#lines 16

import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String userType;
    private UserPerformanceMetrics userPerformanceMetrics;
    private ArrayList<Object> datasetIds = new ArrayList<Object>();
    public User(int id, String userName, String userType,ArrayList<Object> datasetIds){
        // setId(id);
        // setUserName(userName);
        // setUserType(userType);
        this.id=id;
        this.userName = userName;
        this.userType = userType;
        this.datasetIds=datasetIds;
        this.userPerformanceMetrics = new UserPerformanceMetrics(this);

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

    public ArrayList<Object> getDatasetIds() {
        return datasetIds;
    }
}
