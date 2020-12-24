import java.util.ArrayList;
import java.util.List;

public class DatasetPerformanceMetric {
    private transient Dataset dataset;
    private int datasetId;
    private ArrayList<Percentage> labelDistributionPercentage;
    private double completenessPercentage;
    private ArrayList<Percentage> numberUniqueInstancesForEachLabel;
    private int numberOfUsers;
    private ArrayList<Percentage> usersCompleteness;
    private ArrayList<Percentage> consistencyPercentageOfUsers;

    public DatasetPerformanceMetric(Dataset dataset) {
        this.dataset = dataset;
        this.datasetId = dataset.getId();
        this.usersCompleteness = new ArrayList<>();
        this.consistencyPercentageOfUsers = new ArrayList<>();

    }

    public void update(){
        this.datasetId = dataset.getId();
        this.calculateCompleteness();
        this.calculateConsistencyPercentageOfUsers();
        this.calculateLabelDistribution();
        this.calculateNumberOfUsers();
        this.calculateNumberUniqueInstancesForEachLabel();
        this.calculateUsersCompleteness();
        this.numberOfUsers = dataset.getUsers().size();
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
        this.usersCompleteness = new ArrayList<>();
        for (User user: dataset.getUsers()) {
            double percentage = 0;
            for (int i = 0; i < user.getUserPerformanceMetrics().getUsersCompleteness().size(); i++) {
                if (user.getUserPerformanceMetrics().getUsersCompleteness().get(i).getName().equals(dataset.getId() + "")){
                    this.usersCompleteness.add( new Percentage(user.getUserName(), user.getUserPerformanceMetrics().getUsersCompleteness().get(i).getPercentage()));
                }
            }

        }
    }
    //TODO User Metric Required
    private void calculateConsistencyPercentageOfUsers(){
        consistencyPercentageOfUsers = new ArrayList<>();
        for (User use: dataset.getUsers()) {
            consistencyPercentageOfUsers.add(new Percentage(use.getUserName(), use.getUserPerformanceMetrics().getConsistencyPercentage().getPercentage()));
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

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }
}


