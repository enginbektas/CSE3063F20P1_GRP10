public class UserPerformanceMetrics {
    private User user;
    private int datasetAssigned;
    private double datasetCompleteness;
    private int numberOfInstancesLabeled;
    private int numberOfUniqueInstancesLabeled;
    private double consistencyPercentage;
    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;

    public UserPerformanceMetrics(User user) {
        setDatasetAssigned(user);
    }

    public int getDatasetAssigned() {
        return datasetAssigned;
    }

    public void setDatasetAssigned(User user) {
        this.datasetAssigned = user.getDatasetsAssigned().size();
    }
}

