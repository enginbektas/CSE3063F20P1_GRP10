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

    public UserPerformanceMetrics(User user) {
        setDatasetAssigned(user);
        setDatasetCompleteness(user.getDatasetsAssigned()); //sets for
    }

    public int getDatasetAssigned() {
        return datasetAssigned;
    }

    public void setDatasetAssigned(User user) {
        this.datasetAssigned = user.getDatasetsAssigned().size();
    }

    public void setDatasetCompleteness(ArrayList<Dataset> datasetArrayList) {
        for (Dataset dataset: datasetArrayList) {
            DatasetPerformanceMetric datasetPerformanceMetric = new DatasetPerformanceMetric(dataset);
            Percentage percentage = new Percentage(datasetPerformanceMetric.getDataset().getName(), datasetPerformanceMetric.getCompletenessPercentage());
            datasetCompleteness.add(percentage);
        }
    }
}

