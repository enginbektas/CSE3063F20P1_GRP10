import java.util.ArrayList;
import java.util.List;


public class Assignment {
    private transient Dataset dataset;
    private int instanceId;
    private List<Integer> classLabelIds;
    private transient User user;
    private String date;
    private transient Mechanism mechanism;//Which mechanism made this assigment
    private transient double timeSpent;

    public Assignment(List<User> userList, Dataset dataset, Instance instance, User user, String date, List<Label> labels, Mechanism mechanism){
        instance.getLabels().addAll(labels);
        this.dataset = dataset;
        this.instanceId = (int)instance.getId();
        this.classLabelIds = new ArrayList<Integer>();
        setLabels(labels);
        this.user = user;
        this.date = date;
        this.mechanism = mechanism;

        if (!user.getUserPerformanceMetrics().getDatasetsAssigned().contains(dataset)) //adds assignment's dataset to user's userperformancemetrics
            user.getUserPerformanceMetrics().getDatasetsAssigned().add(dataset);
        if (!user.getUserPerformanceMetrics().getUniqueInstancesLabeled().contains(dataset)) //adds assignment's unique instance to user's userperformancemetrics
            user.getUserPerformanceMetrics().getUniqueInstancesLabeled().add(instance);
        user.getUserPerformanceMetrics().getInstancesLabeled().add(instance); //adds assignment's instance to user's userperformancemetrics
        user.getUserPerformanceMetrics().getAssignments().add(this); //adds assignment to the assignments in users usermetric

        for (User userIter: userList) {
            if (!userIter.getUserPerformanceMetrics().getAllDatasets().contains(dataset))
                userIter.getUserPerformanceMetrics().getAllDatasets().add(dataset); //add every dataset to alldatasets in usermetrics
        }
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

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }
}