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
    @SerializedName("dataset ids")
    private ArrayList<Integer> datasetIds;
    @SerializedName("consistency check probability")
    private double consistencyCheckProbability;

    private transient ArrayList<User_Instance> user_instances;

    public User(){
        userPerformanceMetrics = new UserPerformanceMetrics(this);
    }

    public User(int id, String userName, String userType,ArrayList<Integer> datasetIds, double consistencyCheckProbability){
        this.id=id;
        this.userName=userName;
        this.userType=userType;
        this.datasetIds=datasetIds;
        this.consistencyCheckProbability = consistencyCheckProbability;
        userPerformanceMetrics = new UserPerformanceMetrics(this);
        user_instances = new ArrayList<>();
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

    public ArrayList<User_Instance> getUser_instances() {
        return user_instances;
    }
}
