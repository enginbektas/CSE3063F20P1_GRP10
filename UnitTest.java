
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnitTest {
    public static void main(String[] args) {
        //TODO datasetController class needs to be updated.
        //TODO configReader, outputReader, datasetReader methods are required.
        File file = new File("Inputs\\config.json");
        DatasetController datasetController = new DatasetController();//Creating controller
        ArrayList<Storage> storageList = new ArrayList<>();

        int currentDatasetId = datasetController.getCurrentDatasetId(file);

        Log newLog = new Log(); //Creating log
        newLog.editLog();
        storageList = datasetController.configController(file);
        Storage currentStorage = null;


        Writer writer = new Writer();

        ArrayList<Assignment> assignments = null;

        for (Storage storage: storageList) {
            if (storage.getDataset().getId() == currentDatasetId){
                currentStorage = storage;
            }
        }

        List userList = currentStorage.getUsers();
        Dataset dataset = currentStorage.getDataset();
        assignments = (ArrayList<Assignment>) currentStorage.getAssigments();


        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");//Creating mechanism


        for (Object user : userList) {//Loop for every user to label every instance
            newLog.write("***User id = " + ((User) user).getId() + " has logged in.***");//Logging user logins
            long loginDate = System.currentTimeMillis();
            for (int j = 0; j < dataset.getInstances().size(); j++) {//Loop for labeling every instance
                long labelingTimeStart = System.currentTimeMillis();
                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(userList, dataset, dataset.getInstances().get(j), (User) user);
                if (tempAssignment != null){ //returns null if there is no space for any further label
                    newLog.write("*User " + ((User) user).getId() + " has labeled instance " + dataset.getInstances().get(j).getId() + ".*");//logging
                    assignments.add(tempAssignment);
                }
                long labelingTimeEnd = System.currentTimeMillis();
                long labelingTime = labelingTimeEnd - labelingTimeStart;
                assignments.get(assignments.size()-1).setTimeSpent(labelingTime);
            } // labeling ends

            newLog.write("***User " + ((User) user).getId() + " has logged out.***");
            long logoutDate = System.currentTimeMillis();
            long onlineTime = ( logoutDate - loginDate ) / 100;
            ((User) user).getUserPerformanceMetrics().setTotalTimeSpentLabeling((float)onlineTime);
            ((User) user).getUserPerformanceMetrics().setAverageTimeSpentLabeling();
        }

        for (Storage storage: storageList) {
            writer.writeDataset(storage, "Output" + storage.getDataset().getId() + ".json", false, false);
        }
    }
}



