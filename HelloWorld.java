import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        Log newLog = new Log();
        DatasetController datasetController = new DatasetController();

        File inputFile = new File("inputs\\CES3063F20_LabelingProject_Input-1.json");
        File userFile = new File("inputs\\config.json");

        Dataset dataset = datasetController.reader(inputFile);
        List<User> userList = datasetController.userReader(userFile);
        List<LabelAssignment> labelAssignments = new ArrayList<LabelAssignment>();

        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");

        for(int i = 0; i < userList.size(); i++) {
            newLog.write("User id = " + userList.get(i).getId() + " has logged in.");
            for(int j = 0; j < dataset.getInstances().size(); j++) {
                randomLabelingMechanism.mechanism(dataset, dataset.getInstances().get(j), userList.get(i).getId());
                newLog.write(userList.get(i).getId() + " has labeled instance " + dataset.getInstances().get(j) );
            }
        }
        datasetController.writeDataset(dataset, labelAssignments, userList);
    }
}