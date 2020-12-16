import java.util.ArrayList;

public class UserPerformanceMetrics {


    private User user;
    private int datasetAssigned;

    private ArrayList<Percentage> datasetCompleteness;
    private int numberOfInstancesLabeled;
    private int numberOfUniqueInstancesLabeled;
    private double consistencyPercentage;
    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;

    private ArrayList<Dataset> datasetsAssigned;
    private ArrayList<Instance> instancesLabeled;

    public UserPerformanceMetrics(User user) {
        this.user = user;
        setDatasetAssigned(user);
       // setDatasetCompleteness(dataset);
    }

    public ArrayList<Dataset> getDatasetsAssigned() {
        return datasetsAssigned;
    }

    public void setDatasetsAssigned(ArrayList<Dataset> datasetsAssigned) {
        this.datasetsAssigned = datasetsAssigned;
    }

    public int getDatasetAssigned() {
        return datasetAssigned;
    }

    public void setDatasetAssigned(User user) {
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
}

