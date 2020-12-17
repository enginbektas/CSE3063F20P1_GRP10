import java.util.ArrayList;

public class UserPerformanceMetrics {
    private User user;
    private ArrayList<Assignment> assignments;
    private ArrayList<Dataset> datasetsAssigned;
    private ArrayList<Instance> instancesLabeled;
    private ArrayList<Instance> uniqueInstancesLabeled;
    private ArrayList<Dataset> allDatasets;

    private int datasetAssigned;
    private int numberOfInstancesLabeled;
    private int numberOfUniqueInstancesLabeled;
    private ArrayList<Percentage> datasetsCompletenessPercentage;
    private ArrayList<Percentage> consistencyPercentage;
    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;


    public UserPerformanceMetrics(User user) {
        this.user = user;
        setDatasetAssigned();
        setNumberOfInstancesLabeled();
        setNumberOfUniqueInstancesLabeled();
        setDatasetsCompletenessPercentage();
    }

    public ArrayList<Dataset> getDatasetsAssigned() {
        return datasetsAssigned;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setDatasetsAssigned(ArrayList<Dataset> datasetsAssigned) {
        this.datasetsAssigned = datasetsAssigned;
    }

    public int getDatasetAssigned() {
        return datasetAssigned;
    }

    public void setDatasetAssigned() {
        this.datasetAssigned = getDatasetsAssigned().size();
    }

    public ArrayList<Instance> getInstancesLabeled() {
        return instancesLabeled;
    }

    public void setInstancesLabeled(ArrayList<Instance> instancesLabeled) {
        this.instancesLabeled = instancesLabeled;
    }

    public int getNumberOfInstancesLabeled() {
        return numberOfInstancesLabeled;
    }

    public void setNumberOfInstancesLabeled() {
        this.numberOfInstancesLabeled = instancesLabeled.size();
    }

    public ArrayList<Instance> getUniqueInstancesLabeled() {
        return uniqueInstancesLabeled;
    }

    public void setUniqueInstancesLabeled(ArrayList<Instance> uniqueInstancesLabeled) {
        this.uniqueInstancesLabeled = uniqueInstancesLabeled;
    }

    public int getNumberOfUniqueInstancesLabeled() {
        return numberOfUniqueInstancesLabeled;
    }

    public void setNumberOfUniqueInstancesLabeled() {
        this.numberOfUniqueInstancesLabeled = uniqueInstancesLabeled.size();
    }

    public ArrayList<Dataset> getAllDatasets() {
        return allDatasets;
    }

    public void setAllDatasets(ArrayList<Dataset> allDatasets) {
        this.allDatasets = allDatasets;
    }

    public ArrayList<Percentage> getDatasetsCompletenessPercentage() {
        return datasetsCompletenessPercentage;
    }

    public void setDatasetsCompletenessPercentage() {
        for (Dataset dataset : allDatasets) {
            datasetsCompletenessPercentage.add(new Percentage(dataset.getName(), dataset.getDatasetPerformanceMetric().getCompletenessPercentage()));
        }
    }
}

