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

    private Percentage consistencyPercentage;
    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;


    public UserPerformanceMetrics(User user) {
        this.user = user;

        setNumberOfInstancesLabeled();
        setNumberOfUniqueInstancesLabeled();
        setDatasetsCompletenessPercentage();
    }

    public Percentage getConsistencyPercentage() {
        return consistencyPercentage;
    }

    public void setConsistencyPercentage(Percentage consistencyPercentage) {
        consistencyPercentage.setName(user.getUserName());

        ArrayList<Integer> allLabelIds = null;
        ArrayList<Integer> duplicateLabelIds = null;
        int duplicateLabelNumber = 0;
        int allLabelNumber = 0;
        double consistencyP = 0;

        for (Assignment assignment : assignments) { // iterates and compares every element of assignment list
            for (Assignment assignment2 : assignments) {    // with each other
                if (assignment != assignment2) { // avoids comparing the same element
                    if (assignment.getInstance() == assignment2.getInstance()) { // if same instances detected

                        duplicateLabelIds.clear(); //clears lists
                        allLabelIds.clear(); //clears lists

                        for (Integer labelId : assignment.getLabels()) { // compare ins1's labels vs ins2's labels
                            for (Integer labelId2 : assignment2.getLabels()) {
                                if (labelId == labelId2) {               //holds only the duplicate labels
                                    duplicateLabelIds.add(labelId);
                                }
                                else {
                                    if (!allLabelIds.contains(labelId))  //holds all the labels
                                        allLabelIds.add(labelId);
                                    if (!allLabelIds.contains(labelId2))
                                        allLabelIds.add(labelId2);
                                }
                            }
                        }
                        duplicateLabelNumber += duplicateLabelIds.size(); // adds
                        allLabelNumber += allLabelIds.size();   // adds
                    }
                }
            }
        }
        consistencyP = duplicateLabelNumber / allLabelNumber;
        Percentage percentage = new Percentage(user.getUserName(), consistencyP);
        this.consistencyPercentage = percentage;
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
        return this.datasetsCompletenessPercentage;
    }

    public void setDatasetsCompletenessPercentage() {
        for (Dataset dataset : allDatasets) {
            this.datasetsCompletenessPercentage.add(new Percentage(dataset.getName(), dataset.getDatasetPerformanceMetric().getCompletenessPercentage()));
        }
    }

    public double getAverageTimeSpentLabeling() {
        return averageTimeSpentLabeling;
    }

    public void setAverageTimeSpentLabeling(double averageTimeSpentLabeling) {
        this.averageTimeSpentLabeling = averageTimeSpentLabeling;
    }
}

