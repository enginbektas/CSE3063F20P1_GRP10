import java.util.ArrayList;
import java.util.List;
//#methods =13
public class Config {
    private ArrayList<User> users;
    private ArrayList<DatasetPointer> datasetPointers;
    private int currentDatasetId;

    public Config(ArrayList<DatasetPointer> datasetPointers, ArrayList<User> users,int currentDatasetId){
        this.datasetPointers = datasetPointers;
        this.users = users;
        this.currentDatasetId = currentDatasetId;
    }

    public ArrayList<DatasetPointer> getDatasetPointers() {
        return datasetPointers;
    }

    public void setDatasets(ArrayList<DatasetPointer> datasetPointers) {
        this.datasetPointers = datasetPointers;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getCurrentDatasetId() {
        return currentDatasetId;
    }

    public void setCurrentDatasetId(int currentDatasetId) {
        this.currentDatasetId = currentDatasetId;
    }

}
