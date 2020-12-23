
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnitTest {
    public static void main(String[] args) throws InterruptedException {
        //TODO datasetController class needs to be updated.
        //TODO configReader, outputReader, datasetReader methods are required.

        File file = new File("Inputs\\config.json");
        Log newLog = new Log(); //Creating log
        newLog.editLog();

        DatasetController datasetController = new DatasetController();//Creating controller
        ArrayList<Storage> storageList = new ArrayList<>();
        ArrayList<User> userList = datasetController.userReader(file);

        int currentDatasetId = datasetController.getCurrentDatasetId(file);

        storageList = datasetController.configController(file, userList);
        Storage currentStorage = null;

        Writer writer = new Writer();

        ArrayList<Assignment> assignments = new ArrayList<>();

        for (Storage storage: storageList) {
            if (storage.getDataset().getId() == currentDatasetId){
                currentStorage = storage;
            }
        }

        ArrayList<User> currentUserList = currentStorage.getUsers();

        Dataset dataset = currentStorage.getDataset();
        assignments = (ArrayList<Assignment>) currentStorage.getAssigments();


        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");//Creating mechanism


        for (User user : currentUserList) {//Loop for every user to label every instance
            newLog.write("***User id = " + user.getId() + " has logged in.***");//Logging user logins

            for (int j = 0; j < dataset.getInstances().size(); j++) {//Loop for labeling every instance
                float labelingTime = 0;
                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(userList, dataset, dataset.getInstances().get(j), (User) user);
                if (tempAssignment != null){ //returns null if there is no space for any further label
                    newLog.write("*User " + user.getId() + " has labeled instance " + dataset.getInstances().get(j).getId() + ".*");//logging
                    assignments.add(tempAssignment);
                    labelingTime += tempAssignment.getTime();
                }

                user.getUserPerformanceMetrics().setTotalTimeSpentLabeling(user.getUserPerformanceMetrics().getTotalTimeSpentLabeling() + labelingTime);
                user.getUserPerformanceMetrics().setAverageTimeSpentLabeling();
            } // labeling ends

            newLog.write("***User " + user.getId() + " has logged out.***");
        }

        for (Storage storage: storageList) {
            writer.writeDataset(storage, "Output" + storage.getDataset().getId() + ".json", false, false);
        }
    }
}





