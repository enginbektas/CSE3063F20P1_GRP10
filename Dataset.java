import java.util.ArrayList;
import java.util.List;
//#m//#lines 24
public class Dataset {

    private int id;
    private String name;
    private String instanceType;
    private int maxNumOfLabelsPerInstance;
    private List<Label> labels;
    private List<Instance> instances;
    private transient List<User> users;
    private transient DatasetPerformanceMetric datasetPerformanceMetric;
    private transient Storage storage;

    public Dataset(){
        datasetPerformanceMetric = new DatasetPerformanceMetric(this);
    }

    public List<Label> getLabelListFromId(List<Integer> classLabelIds) {
        List<Label> labels = new ArrayList<>();
        for (int classLabelId : classLabelIds)
            for (Label labelj : labels)
                if (labelj.getId() == classLabelId)
                    labels.add(labelj);
                return labels;
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

    public Label getLabelWithId(int labelId){
        Label label2 = new Label(0, "s");
        for (Label label: labels) {
            if (labelId == label.getId())
                return label;
        }
        return label2;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public DatasetPerformanceMetric getDatasetPerformanceMetric() {
        return datasetPerformanceMetric;
    }

    public void setDatasetPerformanceMetric(DatasetPerformanceMetric datasetPerformanceMetric) {
        this.datasetPerformanceMetric = datasetPerformanceMetric;
    }

    public Label getLabel(int id){
        Label label = new Label(0,"");
        for (Label labelj : labels) {
            if (labelj.getId() == id)
                label = labelj;
        }
        return label;
    }

    public Instance getInstance(int id){
        Instance instance = new Instance(0, "");
        for (Instance instancej: instances) {
            if (instancej.getId() == id)
                instance = instancej;
        }
        return instance;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
