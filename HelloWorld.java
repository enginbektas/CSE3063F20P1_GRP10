import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Log newLog = new Log();
        DatasetController datasetController = new DatasetController();

        File inputFile = new File("inputs\\CES3063F20_LabelingProject_Input-2.json");
        File userFile = new File("inputs\\config.json");

        Dataset dataset = datasetController.reader(inputFile);
        storage.setDataset(dataset);
        List<User> userList = datasetController.userReader(userFile);
        storage.setUsers(userList);
        List<Assigment> assignments = new ArrayList<>();
        storage.setAssigments(assignments);
        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");

        for (User user : userList) {
            newLog.write("User id = " + user.getId() + " has logged in.");
            for (int j = 0; j < dataset.getInstances().size(); j++) {
                Assigment tempAssigment = randomLabelingMechanism.mechanism(dataset, dataset.getInstances().get(j), user.getId());
                if (tempAssigment != null){
                    newLog.write("User id = " + user.getId() + " has labeled instance " + dataset.getInstances().get(j).getId());
                    assignments.add(tempAssigment);
                }
            }
        }
        datasetController.writeDataset(storage.getDataset(), storage.getAssigments(), storage.getUsers());
    }
}