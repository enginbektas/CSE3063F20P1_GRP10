import java.util.ArrayList;

public class InstancePerformanceMetric {
    private Instance instance;
    private int TotalNumberOfLabel;
    private int numberOfUniqueLabelAssignment;
    private int numberOfUniqueUsers;
    private double entropy;
    private ArrayList<User> userList;
    private ArrayList<PLabel> labelsPercentages;
    private ArrayList<PLabel> mostFrequentLabel;

    public InstancePerformanceMetric(Instance instance){
        this.instance=instance;
        this.TotalNumberOfLabel=0;
        this.numberOfUniqueLabelAssignment=0;
        this.numberOfUniqueUsers=0;
        this.entropy=0;
        this.userList = new ArrayList<>();
    }

    public void update(User user) {
        if(!userList.contains(user)) {
            userList.add(user);
        }
        setTotalNumberOfLabel();
        setUniqueLabels();
        setNumberOfUniqueLabelAssignment();
        setNumberOfUniqueUsers();
        setLabelsPercentages();
        setMostFrequentLabel();
        setEntropy();

    }

    public void setTotalNumberOfLabel() {
        TotalNumberOfLabel=instance.getLabels().size();
    }

    public void setUniqueLabels() {
        ArrayList<Label> tempUniqueLabels = instance.getLabels();
    }

    public void setNumberOfUniqueLabelAssignment() {
        numberOfUniqueLabelAssignment = 0;
        int i=0,j=0;
        for(i=0;i<instance.getLabels().size();i++){
            for(j=i+1;j<instance.getLabels().size();j++){
                if(instance.getLabels().get(i)==instance.getLabels().get(j))
                    break;
            }
            if(j==instance.getLabels().size())
                numberOfUniqueLabelAssignment++;
        }
    }

    public void setNumberOfUniqueUsers() {
        numberOfUniqueUsers = userList.size();
    }

    public void setLabelsPercentages() {
        int i=0,j=0;

        labelsPercentages = new ArrayList<PLabel>();
        PLabel tplabel = new PLabel(instance.getLabels().get(0),1);
        tplabel.setPercentage(TotalNumberOfLabel);
        labelsPercentages.add(tplabel);
        for(i=1;i<instance.getLabels().size();i++){
            for (j=0;j<labelsPercentages.size();j++){
                if(labelsPercentages.get(j).getLabel()==instance.getLabels().get(i)) {
                    labelsPercentages.get(j).incNum();
                    break;
                }
            }
            if(j==labelsPercentages.size()){
                tplabel= new PLabel(instance.getLabels().get(i),1);
                labelsPercentages.add(tplabel);
                tplabel.setPercentage(TotalNumberOfLabel);
            }
        }
    }

    public void setMostFrequentLabel() {
        int i=0,j=0;

        mostFrequentLabel = new ArrayList<PLabel>();
        mostFrequentLabel.add(labelsPercentages.get(0));
        for (i=1,j=0;i<labelsPercentages.size();i++){
            if(labelsPercentages.get(i).getNum()>mostFrequentLabel.get(j).getNum()){
                mostFrequentLabel.remove(j);
                mostFrequentLabel.add(labelsPercentages.get(j));
            }
            else if (labelsPercentages.get(i).getNum()== mostFrequentLabel.get(j).getNum()){
                mostFrequentLabel.add(labelsPercentages.get(i));
                j++;
            }
        }
    }

    public void setEntropy(){
        entropy = 0;
        int i=0;
        for (i=0;i<labelsPercentages.size();i++){
            entropy=entropy+(0-labelsPercentages.get(i).getPercentage()/100.0*Math.log(labelsPercentages.get(i).getPercentage()/100.0)/Math.log(numberOfUniqueLabelAssignment));
        }

    }

    public int getTotalNumberOfLabel(){ return TotalNumberOfLabel; }

    public int getNumberOfUniqueLabelAssignment(){ return numberOfUniqueLabelAssignment; }

    public int getNumberOfUniqueUsers(){ return numberOfUniqueUsers; }

    public ArrayList<PLabel> getLabelsPercentages(){ return labelsPercentages; }

    public ArrayList<PLabel> getMostFrequentLabel(){ return mostFrequentLabel; }

    public double getEntropy(){ return entropy; }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
