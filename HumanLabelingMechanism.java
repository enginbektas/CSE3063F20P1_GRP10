import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HumanLabelingMechanism extends Mechanism{

    private String mechanismName;
    private transient ArrayList<Assignment> assignments;
    private Log log;

    public HumanLabelingMechanism(String mechanismName) {
        this.mechanismName=mechanismName;
        this.assignments = new ArrayList<Assignment>();
        this.log = new Log();
    }
    public Assignment humanMechanism(ArrayList<User> userList, Dataset dataset, Instance instance, User user) {
        double labelingTimeStart = System.currentTimeMillis();
        ArrayList<Label> labelList = dataset.getLabels();
        System.out.println("Enter how many labels");
        Scanner input = new Scanner(System.in);
        int labelAmount = input.nextInt();
        ArrayList<Label> labelsToUse = new ArrayList<>();
        for (int i = 0; i < labelAmount; i++) {
            System.out.println("Choose a label from id");
            int labelId = input.nextInt();
            for (Label label : dataset.getLabels()) {
                if (labelId == label.getId()) {
                    labelsToUse.add(label);
                }
            }
        }
        //creating date formatter
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        //s holds the current time
        Date date = new Date(System.currentTimeMillis());
        String dateString =  formatter.format(date);
        double labelingTimeEnd = System.currentTimeMillis();
        double labelingTime = labelingTimeEnd - labelingTimeStart;
        Assignment assignment = new Assignment(dataset, userList, instance, user, dateString, labelsToUse, labelingTime);
        assignments.add(assignment);
        return assignment;
    }
}


