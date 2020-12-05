import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;



public class RandomLabelingMechanism extends Mechanism{
    private String mechanismName;
    private transient List<Assignment> assignments;
    public RandomLabelingMechanism(String mechanismName){
        this.mechanismName=mechanismName;
        this.assignments = new ArrayList<Assignment>();
    }

    public Assignment randomMechanism(Dataset dataset, Instance instance, int userId){
        //number of empty labels in the instance
        int labelNumberLeft = dataset.getMaxNumOfLabelsPerInstance() - instance.getLabels().size();
        if (labelNumberLeft == 0)
            return null;
        //how many labels to assign, randomly created
        int numberOfLabelsToAssign = (int) ((Math.random() * (labelNumberLeft - 1 )) + 1);
        //creates a list that holds the labels that are going to be assigned to the instance
        List<Label> labelsToUse = labelsToUse(dataset, instance, numberOfLabelsToAssign);
        //adds the new labels to the instances label list
        instance.getLabels().addAll(labelsToUse);
        //creating date formatter
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        //s holds the current time
        Date date = new Date(System.currentTimeMillis());
        String s =  formatter.format(date);
        //Labels are assigned. Now creating an assignment object
        Assignment assignment = new Assignment(instance, userId, s, labelsToUse, this);
        assignments.add(assignment);
        return assignment;
    }
    //returns a list of labels to assign to an instance
    private List<Label> labelsToUse(Dataset dataset, Instance instance, int numberOfLabelsToAssign){
        List<Label> labelsToUse = new ArrayList<Label>();
        for (int i = 0; i < numberOfLabelsToAssign; i++){//Loop for chosing labels to use
            while(true){
                int k = (int) (Math.random() * (dataset.getLabels().size() - 1));//Get a random int value for choosing label from label list
                if ( !instance.getLabels().contains(dataset.getLabels().get(k)) && !labelsToUse.contains(dataset.getLabels().get(k))){//Checks if instance has that label or labels to use has it
                    labelsToUse.add(dataset.getLabels().get(k));
                    break;
                }
            }
        }
        return labelsToUse;
    }


    //Geters Setters
    public String getMechanismName() {
        return mechanismName;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
