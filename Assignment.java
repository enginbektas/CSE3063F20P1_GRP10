import java.util.ArrayList;
import java.util.List;


public class Assignment {
    private Dataset dataset;
    private int instanceId;
    private List<Integer> classLabelIds;
    private User user;
    private String date;
    private transient Mechanism mechanism;//Which mechanism made this assigment

    public Assignment(Dataset dataset, Instance instance, User user, String date, List<Label> labels, Mechanism mechanism){
        this.dataset = dataset;
        this.instanceId = (int)instance.getId();
        this.classLabelIds = new ArrayList<Integer>();
        setLabels(labels);
        this.user = user;
        this.date = date;
        this.mechanism = mechanism;
        if (!user.getDatasetsAssigned().contains(dataset))
            user.getDatasetsAssigned().add(dataset);
    }

    public int getUserId() {
        return user.getId();
    }

    public int getInstance() {
        return instanceId;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getLabels() {
        return classLabelIds;
    }

    public void setInstance(Instance instance) {
        this.instanceId = (int)instance.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLabels(List<Label> labels) {//Only gets labels id's
        int i = 0;
        for (Label label : labels) {
            classLabelIds.add(labels.get(i).getId());
            i++;
        }
    }
}