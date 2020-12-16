import java.util.ArrayList;
import java.util.List;

public class DatasetPerformanceMetric {
    private Dataset dataset;
    private double completenessPercentage;
    private ArrayList<Percentage> labelDistributionPercentage;
    private ArrayList<Percentage> numberUniqueInstancesForEachLabel;
    private int numberOfUsers;
    private ArrayList<Percentage> usersCompleteness;
    private ArrayList<Percentage> consistencyPercentageOfUsers;

    public DatasetPerformanceMetric(Dataset dataset) {
        this.dataset = dataset;

        this.usersCompleteness = new ArrayList<>();
        this.consistencyPercentageOfUsers = new ArrayList<>();
        calculateCompleteness();
    }

    private void calculateCompleteness(){
        this.completenessPercentage = 0;
        for (Instance instance: dataset.getInstances() ) {
            if(instance.getLabels().size() != 0){
                this.completenessPercentage++;
            }
        }
        this.completenessPercentage /= dataset.getInstances().size();
        this.completenessPercentage *= 100;

    }

    private void calculateLabelDistribution(){
        this.labelDistributionPercentage = new ArrayList<>();
        int totalUsage = 0;
        for (Label label: dataset.getLabels()) {
            totalUsage += label.getNumberOfUses();
        }

        if (totalUsage == 0){
            for (Label label: dataset.getLabels()) {
                this.labelDistributionPercentage.add(new Percentage(label.getText(), 0));
            }
        }

        for (Label label: dataset.getLabels()) {
            this.labelDistributionPercentage.add(new Percentage(label.getText(), label.getNumberOfUses()  / (totalUsage * 1.0)));
        }
    }

    private void calculateNumberUniqueInstancesForEachLabel(){
        this.numberUniqueInstancesForEachLabel = new ArrayList<>();
        for (Label label: dataset.getLabels()) {
            int numberOfUniqueInstances = 0;
            for (Instance instance: dataset.getInstances()) {
                if(instance.getLabels().contains(label)){
                    numberOfUniqueInstances++;
                }
            }
            this.numberUniqueInstancesForEachLabel.add(new Percentage(label.getText(), numberOfUniqueInstances));
        }
    }

    private void calculateNumberOfUsers(){
        numberOfUsers = dataset.getUsers().size();
    }


    //TODO User Metric Required
    private void calculateUsersCompleteness(){
        for (User use: dataset.getUsers()) {

        }
    }
    //TODO User Metric Required
    private void calculateConsistencyPercentageOfUsers(){
        for (User use: dataset.getUsers()) {

        }
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public double getCompletenessPercentage() {
        return completenessPercentage;
    }
}


