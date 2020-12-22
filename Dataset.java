import com.google.gson.annotations.SerializedName;

import java.util.List;
//#m//#lines 24
public class Dataset {
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

    public Dataset(){
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public int getMaxNumOfLabelsPerInstance() {
        return maxNumOfLabelsPerInstance;
    }

    public void setMaxNumOfLabelsPerInstance(int maxNumOfLabelsPerInstance) {
        this.maxNumOfLabelsPerInstance = maxNumOfLabelsPerInstance;
    }


}
