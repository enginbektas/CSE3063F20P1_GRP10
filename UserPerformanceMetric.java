import java.util.ArrayList;

public class UserPerformanceMetric {
    private User user;
    private transient ArrayList<Assignment> assignments;
    private transient ArrayList<Dataset> datasetsAssigned;
    private transient ArrayList<Instance> instancesLabeled;
    private transient ArrayList<Instance> uniqueInstancesLabeled;
    private transient ArrayList<Instance> instancesLabeledMoreThanOnce;

    private int datasetAssigned;
    private ArrayList<Percentage> datasetsCompletenessPercentage;
    private int numberOfInstancesLabeled;
    private int numberOfUniqueInstancesLabeled;
    private Percentage consistencyPercentage = new Percentage("", 0);
    private transient ArrayList<Percentage> usersCompleteness;

    private transient double totalTimeSpentLabeling; //

    private double averageTimeSpentLabeling;
    private double stdDevOfTimeSpentLabelingInstances;

    public UserPerformanceMetric(User user) {
        
        this.user = user;
        this.assignments = new ArrayList<>();
        this.datasetsAssigned = new ArrayList<>();
        this.instancesLabeled = new ArrayList<>();
        this.uniqueInstancesLabeled = new ArrayList<>();
        this.datasetsCompletenessPercentage = new ArrayList<>();
        this.usersCompleteness = new ArrayList<>();
        this.instancesLabeledMoreThanOnce = new ArrayList<>();

    }

    public void update(Assignment assignment, Dataset dataset, Instance instance) {
        //update method is called in each assignments constructor
        this.assignments.add(assignment);

        if (!instancesLabeled.contains(instance)){
            this.uniqueInstancesLabeled.add(instance);
        }
        else {
            if (!this.instancesLabeledMoreThanOnce.contains(instance))
            this.instancesLabeledMoreThanOnce.add(instance);
        }


        if (!datasetsAssigned.contains(dataset))
            this.datasetsAssigned.add(dataset);

        this.datasetAssigned = datasetsAssigned.size();

        this.instancesLabeled.add(instance);

        this.numberOfInstancesLabeled = instancesLabeled.size();

        this.numberOfUniqueInstancesLabeled = uniqueInstancesLabeled.size();

        calculateDatasetCompletenessPercentage();
        setConsistencyPercentage();
        calculateStd();

    }

    private void calculateDatasetCompletenessPercentage(){

        for (int i = 0; i < usersCompleteness.size(); i++) {
            usersCompleteness.get(i).setPercentage(0);
        }
        for (Instance instance: uniqueInstancesLabeled) {
            for (Assignment assignment: assignments) {
                if (instance.equals(assignment.getInstance()) && assignment.getUserId() == user.getId()){
                    if ( usersCompleteness.size() == 0){
                        usersCompleteness.add(new Percentage(assignment.getDataset().getId() + "", (1.0 / assignment.getDataset().getInstances().size()) * 100.0));
                        break;
                    }
                    else{
                        for (int i = 0; i < usersCompleteness.size(); i++) {
                            if (usersCompleteness.get(i).getName().equals(assignment.getDataset().getId() + "")){
                                usersCompleteness.get(i).setPercentage(usersCompleteness.get(i).getPercentage() + (1.0 / assignment.getDataset().getInstances().size()) * 100.0);
                                break;
                            }
                            if(i == usersCompleteness.size() - 1){
                                usersCompleteness.add(new Percentage(assignment.getDataset().getId() + "", (1.0 / assignment.getDataset().getInstances().size()) * 100.0));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    public ArrayList<Percentage> getUsersCompleteness() {
        return usersCompleteness;
    }

    public Percentage getConsistencyPercentage() {
        return consistencyPercentage;
    }

    public void setConsistencyPercentage() {
        int consistentLabelings =0;
        for (Instance instance1 : instancesLabeledMoreThanOnce) {
            ArrayList<Label> reoccuringLabels = new ArrayList<>();
            ArrayList<Label> allLabels = new ArrayList<>();
            for (Assignment assignment : assignments) {
                allLabels.addAll(assignment.getLabelList());
                if (assignment.getInstance().equals(instance1)) {
                    for (Label label : assignment.getLabelList()) {
                        for ( Label label1 : instance1.getLabels()) {
                            if (label == label1) {
                                consistentLabelings++;
                            }

                        }
                    }
                }
            }
        }
    }

    private void calculateStd(){ //calculates the standard deviation

    double sum = 0.0;
    double standardDeviation = 0.0;
    double mean = 0.0;
    double res = 0.0;
    double sq = 0.0;
    double[] arr = new double[user.getUser_instances().size()];
    int n = arr.length;
    int j = 0;

        for (User_Instance userInstance: user.getUser_instances()) {
            arr[j] = userInstance.getTime();
            j++;
        }

        for (int i = 0; i < n; i++) {
            sum = sum + arr[i];
        }

        mean = sum / (n);

        for (int i = 0; i < n; i++) {
            standardDeviation
                = standardDeviation + Math.pow((arr[i] - mean), 2);
        }
        sq = standardDeviation / n;
        res = Math.sqrt(sq);
        this.stdDevOfTimeSpentLabelingInstances = res;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<Instance> getUniqueInstancesLabeled() {
        return uniqueInstancesLabeled;
    }

    public void setAverageTimeSpentLabeling() {
        this.averageTimeSpentLabeling = totalTimeSpentLabeling / assignments.size();
    }

    public double getTotalTimeSpentLabeling() {
        return totalTimeSpentLabeling;
    }

    public void setTotalTimeSpentLabeling(double timeSpent) {
            totalTimeSpentLabeling = timeSpent;
    }
}

