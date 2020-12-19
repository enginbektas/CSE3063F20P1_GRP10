//#lines 16

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String userType;
    private ArrayList<Integer> datasetIds;

    public User(int id, String userName, String userType, ArrayList<Integer> datasetIds){
        setId(id);
        setUserName(userName);
        setUserType(userType);
        this.datasetIds = datasetIds;
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

    public ArrayList<Integer> getDatasetIds() {
        return datasetIds;
    }

    public void setDatasetIds(ArrayList<Integer> datasetIds) {
        this.datasetIds = datasetIds;
    }
}
