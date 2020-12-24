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
    }

    //update dataset performance metric
    public void update(){
        this.calculateCompleteness();
        this.calculateConsistencyPercentageOfUsers();
        this.calculateLabelDistribution();
        this.calculateNumberOfUsers();
        this.calculateNumberUniqueInstancesForEachLabel();
        this.calculateUsersCompleteness();

    }
    //calculate completeness percentage using instances
    private void calculateCompleteness(){
        this.completenessPercentage = 0;
        //for all instances of dataset
        for (Instance instance: dataset.getInstances() ) {
            //if instance has a label
            if(instance.getLabels().size() != 0){
                this.completenessPercentage++;
            }
        }
        this.completenessPercentage /= dataset.getInstances().size();
        this.completenessPercentage *= 100;

    }
    //calculate label distribution using labels
    private void calculateLabelDistribution(){
        //clear array
        this.labelDistributionPercentage = new ArrayList<>();
        int totalUsage = 0;
        //calculate total usage of labels
        for (Label label: dataset.getLabels()) {
            totalUsage += label.getNumberOfUses();
        }
        if (totalUsage == 0){
            for (Label label: dataset.getLabels()) {
                this.labelDistributionPercentage.add(new Percentage(label.getText(), 0));
            }
        }
        //for every label calcualte and add values
        for (Label label: dataset.getLabels()) {
            this.labelDistributionPercentage.add(new Percentage(label.getText(), label.getNumberOfUses()  / (totalUsage * 1.0)));
        }
    }
    //calculate number of unique instances for each label using labels and instances
    private void calculateNumberUniqueInstancesForEachLabel(){
        //clear array
        this.numberUniqueInstancesForEachLabel = new ArrayList<>();
        //for every label of dataset
        for (Label label: dataset.getLabels()) {
            int numberOfUniqueInstances = 0;
            //for every instance
            for (Instance instance: dataset.getInstances()) {
                //check unique instance
                if(instance.getLabels().contains(label)){
                    numberOfUniqueInstances++;
                }
            }
            this.numberUniqueInstancesForEachLabel.add(new Percentage(label.getText(), numberOfUniqueInstances));
        }
    }
    //calculate number of users
    private void calculateNumberOfUsers(){
        numberOfUsers = dataset.getUsers().size();
    }
    //calculate user completeness using users' performance metric
    private void calculateUsersCompleteness(){
        //clear array
        this.usersCompleteness = new ArrayList<>();
        //for every user of dataset
        for (User user: dataset.getUsers()) {
            double percentage = 0;
            //for every user completeness in user metric
            for (int i = 0; i < user.getUserPerformanceMetrics().getUsersCompleteness().size(); i++) {
                //find current dataset in user completeness
                if (user.getUserPerformanceMetrics().getUsersCompleteness().get(i).getName().equals(dataset.getId() + "")){
                    this.usersCompleteness.add( new Percentage(user.getUserName(), user.getUserPerformanceMetrics().getUsersCompleteness().get(i).getPercentage()));
                }
            }

        }
    }
    //calculate consistency percentage using users' performance metric
    private void calculateConsistencyPercentageOfUsers(){
        //clear array
        consistencyPercentageOfUsers = new ArrayList<>();
        //get consistency percentage from users
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
}


