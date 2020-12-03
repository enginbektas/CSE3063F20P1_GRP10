
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RandomLabelingMechanism extends Mechanism{
    private String mechanismName;
    public RandomLabelingMechanism(String mechanismName){
        this.mechanismName=mechanismName;
    }
    public Assigment mechanism(Dataset dataset, Instance instance, int userId){

        int labelNumberLeft = dataset.getMaxNumOfLabelsPerInstance() - instance.getLabels().size();

        int numberOfLabelsToAssign = (int) ((Math.random() * (labelNumberLeft - 1 )) + 1);
        List<Label> labelsToUse = labelsToUse(dataset, instance, numberOfLabelsToAssign);

        instance.getLabels().addAll(labelsToUse);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");

        Date date = new Date(System.currentTimeMillis());
        String s =  formatter.format(date);
        Assigment assigment = new Assigment(instance, userId, s, instance.getLabels());

        return assigment;
    }

    private List<Label> labelsToUse(Dataset dataset, Instance instance, int numberOfLabelsToAssign){
        boolean flag;
        List<Label> labelsToUse = new ArrayList<Label>();

        for (int i = 0; i < numberOfLabelsToAssign; i++){
            flag = true;
            while(flag){
                int k = (int) Math.random() * dataset.getLabels().size();

                if (!instance.getLabels().contains(dataset.getLabels().get(k))){
                    labelsToUse.add(dataset.getLabels().get(k));
                    flag = false;
                }
            }


        }

        return labelsToUse;
    }
}
