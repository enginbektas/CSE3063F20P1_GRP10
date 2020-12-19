
import java.io.File;
import java.util.ArrayList;
import java.util.List;
//#lines 28
public class UnitTest {
    public static void main(String[] args) {

        //TODO
        // all datasets in the config file should be created as a list of storages in the unitTest
        // if a dataset's output exists, dataset should be created from the output and neccessary assignments must be made.
        //     configReader is responsible for these operations
        //     configReader reads outputs via storageReader and assigns previous assignments via assigner method.
        //     if output does not exist, configReader reads dataset via datasetReader method.
        //     finally adds them all to a list and returns the storage list.
        // why storage list instead of dataset list? -> we should have the assignment information for each dataset, via storage

        Storage storage = new Storage();//Creating Storage
        Log newLog = new Log();//Creating log
        newLog.editLog();
        DatasetController datasetController = new DatasetController();//Creating controller

        File inputFile = new File("inputs\\CES3063F20_LabelingProject_Input-2.json");
        File userFile = new File("inputs\\config.json");

        Dataset dataset = datasetController.reader(inputFile);//Reading dataset from json file
        storage.setDataset(dataset);//Adding dataset to storage
        List<User> userList = datasetController.userReader(userFile);//Reading users from user file
        storage.setUsers(userList);//Adding  users to storage

        List<Assignment> assignments = new ArrayList<>();
        storage.setAssigments(assignments);

        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");//Creating mechanism

        for (User user : userList) {//Loop for every user to label every instance
            newLog.write("***User id = " + user.getId() + " has logged in.***");//Logging user logins
            for (int j = 0; j < dataset.getInstances().size(); j++) {//Loop for labeling every instance
                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(dataset, dataset.getInstances().get(j), user);
                if (tempAssignment != null){//returns null if there is no space for any further label
                    newLog.write("*User " + user.getId() + " has labeled instance " + dataset.getInstances().get(j).getId() + ".*");//logging
                    assignments.add(tempAssignment);
                }
            }
            newLog.write("***User " + user.getId() + " has logged out.***");
        }
        datasetController.writeDataset(storage);//Printing output files
    }
}