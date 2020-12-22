import com.google.gson.annotations.SerializedName;

import java.util.List;
//#methods =13
public class Storage {

    private Dataset dataset = new Dataset();
    @SerializedName("dataset id")
    private int id;
    @SerializedName("dataset name")
    private String name;
    private String instanceType;
    @SerializedName("maximum number of labels per instance")
    private int maxNumOfLabelsPerInstance;
    @SerializedName("class labels")
    private List<Label> labels;
    private List<Instance> instances;
    @SerializedName("class label assignments")

    private List<Assignment> assignments;
    private List<User> users;

    public Storage(){


    }

    public void setAssigments(List<Assignment> assignments) {
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
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public List<Assignment> getAssigments() {
        return assignments;
    }

    public List<User> getUsers() {
        return users;
    }


}
