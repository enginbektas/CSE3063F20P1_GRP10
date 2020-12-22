//#lines 16

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {
    @SerializedName("user id")
    private int id;
    @SerializedName("user name")
    private String userName;
    @SerializedName("user type")
    private String userType;
    private transient UserPerformanceMetrics userPerformanceMetrics;
    private transient ArrayList<Integer> datasetIds;

    public User(){
    }

    public User(int id, String userName, String userType,ArrayList<Integer> datasetIds){
        this.id=id;
        this.userName=userName;
        this.userType=userType;
        this.datasetIds=datasetIds;
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
