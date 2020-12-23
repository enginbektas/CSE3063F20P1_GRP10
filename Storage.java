import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
//#methods =13
public class Storage {

    private transient Dataset dataset = new Dataset();
    @SerializedName("dataset id")
    private int id;
    @SerializedName("dataset name")
    private String name;
    private String instanceType;
    @SerializedName("maximum number of labels per instance")
    private int maxNumOfLabelsPerInstance;
    @SerializedName("class labels")
    private ArrayList<Label> labels;
    private ArrayList<Instance> instances;
    @SerializedName("class label assignments")

    private ArrayList<Assignment> assignments;
    private ArrayList<User> users;

    public Storage(){


    }

    public void setAssigments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
        this.id = dataset.getId();
        this.name = dataset.getName();
        this.instanceType = dataset.getInstanceType();
        this.maxNumOfLabelsPerInstance = dataset.getMaxNumOfLabelsPerInstance();
        this.labels = dataset.getLabels();
        this.instances = dataset.getInstances();
        this.assignments = new ArrayList<>();
        this.users = new ArrayList<>();

    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public ArrayList<Assignment> getAssigments() {
        return assignments;
    }

    public ArrayList<User> getUsers() {
        return users;
    }


}
