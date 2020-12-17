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
    private Percentage datasetCompletenessPercentage;
    private Percentage consistencyPercentage;
    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;


    public UserPerformanceMetrics(User user) {
        this.user = user;
        setDatasetAssigned();
        setNumberOfInstancesLabeled();
        setNumberOfUniqueInstancesLabeled();
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

    public void setDatasetCompleteness(ArrayList<Dataset> datasetArrayList) {
        for (Dataset dataset: datasetArrayList) {
            DatasetPerformanceMetric datasetPerformanceMetric = new DatasetPerformanceMetric(dataset);
            Percentage percentage = new Percentage(datasetPerformanceMetric.getDataset().getName(), datasetPerformanceMetric.getCompletenessPercentage());
            datasetCompleteness.add(percentage);
        }
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


}

