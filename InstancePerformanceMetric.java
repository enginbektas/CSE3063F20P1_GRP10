import java.util.ArrayList;

public class InstancePerformanceMetric {
    private transient Instance instance;
    private int instanceId;
    private int totalNumberOfLabel;
    private int numberOfUniqueLabelAssignment;
    private int numberOfUniqueUsers;
    private ArrayList<PLabel> mostFrequentLabel;
    private ArrayList<PLabel> labelsPercentages;
    private double entropy;
    private transient ArrayList<User> userList;
    private transient Label finalLabel;

    public InstancePerformanceMetric(Instance instance){
        this.instance=instance;
        this.totalNumberOfLabel =0;
        this.numberOfUniqueLabelAssignment=0;
        this.numberOfUniqueUsers=0;
        this.entropy=0;
        this.userList = new ArrayList<>();
        this.instanceId = instance.getId();
    }
    //update instance performance metric
    public void update(User user) {
        //add user into userList if it doesn't contain
        if(!userList.contains(user)) {
            userList.add(user);
        }
        setTotalNumberOfLabel();
        setNumberOfUniqueLabelAssignment();
        setNumberOfUniqueUsers();
        setLabelsPercentages();
        setMostFrequentLabel();
        setEntropy();
        updateFinalLabel();
    }
    //Update final label in Instance with most frequent label
    private void updateFinalLabel() {
        this.finalLabel = mostFrequentLabel.get(0).getLabel();
        this.instance.setFinalLabel(finalLabel);
    }
    //set total number of label with size of the labels list in instance
    public void setTotalNumberOfLabel() {
        totalNumberOfLabel =instance.getLabels().size();
    }
    //calculate number of unique label assignment
    public void setNumberOfUniqueLabelAssignment() {
        numberOfUniqueLabelAssignment = 0;
        int i=0,j=0;
        //search labels list and calculate unique label number
        for(i=0;i<instance.getLabels().size();i++){
            //try to find if there is another same label with this label
            for(j=i+1;j<instance.getLabels().size();j++){
                if(instance.getLabels().get(i)==instance.getLabels().get(j))
                    break;
            }
            //if there is no other label from this label, increase numberOfUniqueLabelAssignment
            if(j==instance.getLabels().size())
                numberOfUniqueLabelAssignment++;
        }
    }
    //set number of unique users with userList's size
    public void setNumberOfUniqueUsers() {
        numberOfUniqueUsers = userList.size();
    }
    //calculate all unique labels percentages
    public void setLabelsPercentages() {
        int i=0,j=0;

        labelsPercentages = new ArrayList<PLabel>();
        PLabel tplabel = new PLabel(instance.getLabels().get(0),1);
        tplabel.setPercentage(totalNumberOfLabel);
        labelsPercentages.add(tplabel);
        //try to find number of label in the labels list in the Instance and calculate their percentage according to total number of labels
        for(i=1;i<instance.getLabels().size();i++){
            for (j=0;j<labelsPercentages.size();j++){
                if(labelsPercentages.get(j).getLabel()==instance.getLabels().get(i)) {
                    labelsPercentages.get(j).incNum();
                    labelsPercentages.get(j).setPercentage(totalNumberOfLabel);
                    break;
                }
            }
            //if there is no same label in the labelsPercentages list, then add this plabel into labelsPercentages
            if(j==labelsPercentages.size()){
                tplabel= new PLabel(instance.getLabels().get(i),1);
                tplabel.setPercentage(totalNumberOfLabel);
                labelsPercentages.add(tplabel);
            }
        }
    }
    //Create mostFrequentLabel list
    public void setMostFrequentLabel() {
        int i=0,j=0;
        mostFrequentLabel = new ArrayList<PLabel>();
        mostFrequentLabel.add(labelsPercentages.get(0));
        //Search labelsPercentages list and try to find most frequent labels
        for (i=1,j=0;i<labelsPercentages.size();i++){
            if(labelsPercentages.get(i).getNum()>mostFrequentLabel.get(j).getNum()){
                mostFrequentLabel.clear();
                mostFrequentLabel.add(labelsPercentages.get(i));
                j=0;
            }
            else if (labelsPercentages.get(i).getNum()== mostFrequentLabel.get(j).getNum()){
                mostFrequentLabel.add(labelsPercentages.get(i));
                j++;
            }
        }
    }
    //Calculate entropy
    public void setEntropy(){
        entropy = 0;
        int i=0;
        for (i=0;i<labelsPercentages.size();i++){
            if (numberOfUniqueLabelAssignment != 1) {
                entropy = entropy + (0 - labelsPercentages.get(i).getPercentage() / 100.0 * Math.log(labelsPercentages.get(i).getPercentage() / 100.0) / Math.log(numberOfUniqueLabelAssignment));
            }
            else {//If numberOfUniqueLabelAssignment is equal to 1, then set entropy infinity
                entropy = Double.POSITIVE_INFINITY;
            }
        }
    }
}
