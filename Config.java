import java.util.ArrayList;
import java.util.List;
//#methods =13
public class Config {
    private ArrayList<Dataset> datasets;
    private ArrayList<User> users;
    private int currentDatasetId;

    public Config(ArrayList<Dataset> datasets, ArrayList<User> users,int currentDatasetId){
        this.datasets = datasets;
        this.users = users;
        this.currentDatasetId = currentDatasetId;
    }

    public ArrayList<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(ArrayList<Dataset> datasets) {
        this.datasets = datasets;
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
