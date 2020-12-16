import java.util.ArrayList;
import java.util.List;

public class DatasetPerformanceMetric {
    private Dataset dataset;
    private double completenessPercentage;
    private ArrayList<Percentage> labelDistributionPercentage;
    private int numberUniqueInstancesForEachLabel;
    private int numberOfUsers;
    private ArrayList<User> usersCompleteness;
    private ArrayList<User> consistencyPercentageOfUsers;

    public DatasetPerformanceMetric(Dataset dataset) {
        this.dataset = dataset;

        this.usersCompleteness = new ArrayList<>();
        this.consistencyPercentageOfUsers = new ArrayList<>();
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
            this.labelDistributionPercentage.add(new Percentage(label.getText(), label.getNumberOfUses()  / (totalUsage * 1.0));
        }
    }


}


