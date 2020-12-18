import java.util.ArrayList;
import java.util.List;
//#methods =13
public class Config {
    private ArrayList<Dataset> datasets;
    private ArrayList<User> users;
    private ArrayList<Storage> storages;
    private int currentDatasetId;

    public Config(ArrayList<Dataset> datasets, ArrayList<User> users, ArrayList<Storage> storages,int currentDatasetId){
        this.datasets = datasets;
        this.users = users;
        this.currentDatasetId = currentDatasetId;
        this.storages = storages;
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

    public ArrayList<Storage> getStorages() {
        return storages;
    }

    public void setStorages(ArrayList<Storage> storages) {
        this.storages = storages;
    }
}
