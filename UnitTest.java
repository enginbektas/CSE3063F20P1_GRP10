
import java.util.ArrayList;

public class UnitTest {
    public static void main(String[] args) {
        //TODO datasetController class needs to be updated.
        //TODO configReader, outputReader, datasetReader methods are required.
        ArrayList<Storage> storageList = new ArrayList<>();
        ArrayList<Dataset> datasetList = new ArrayList<>();
        Dataset currentDataset;
        Log newLog = new Log(); //Creating log
        newLog.editLog();
        /*
        // read config (
        File file = new File("");
        DatasetController datasetController = new DatasetController();//Creating controller

        // create and read config
        File configFile = new File("");
        Config config = datasetController.configReader(configFile); //configReader function has to be implemented

        // create users
        List<User> users = config.getUsers();

        // create a list of dataset pointers
        List<DatasetPointer> datasetPointers = config.getDatasetPointers();

        for (DatasetPointer datasetPointer: datasetPointers) {
            datasetList.add(datasetController.reader(new File(datasetPointer.getPath())));
            if (datasetPointer.getId() == config.getCurrentDatasetId())
                currentDataset = datasetList.get(datasetList.size());
        }

        // assign dataset to its matching output, if exists
        File outputFile = new File("Output" + config.getCurrentDatasetId());
        if(outputFile.exists()) {
            storage = datasetController.outputReader(outputFile);
            dataset = storage.getDataset();
            // TODO assign previous labels from storage to current dataset. Some classes may need updates.
            for (Assignment assignment : storage.getAssigments())

        }


        //assign new dataset if there is no matching output
        if (dataset == null) {
            for (DatasetPointer datasetPointerIter : datasetPointers) {
                if (datasetPointerIter.getId() == config.getCurrentDatasetId()) {
                    File datasetFile = new File(datasetPointerIter.getPath());
                    dataset = datasetController.reader(datasetFile);
                    break;
                }
            }
        }

        // remove illegal users from the list
        for (User user : users)
            if (!user.getDatasetIds().contains(config.getCurrentDatasetId()))
                users.remove(user);

        List<Assignment> assignments = new ArrayList<>();
        storage.setDataset(dataset);
        storage.setUsers(users);
        storage.setAssigments(assignments);

        //TODO assign labels to current  dataset
        for (User user : users) {
            //TODO assign loop

            //TODO after every assign update json files
        }        */
    }
}
        /*
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
        // aynı listeyi userPerformanceList içineki assigment'a eşitle

        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");//Creating mechanism


        for (User user : userList) {//Loop for every user to label every instance
            newLog.write("***User id = " + user.getId() + " has logged in.***");//Logging user logins
            long loginDate = System.currentTimeMillis();
            for (int j = 0; j < dataset.getInstances().size(); j++) {//Loop for labeling every instance
                long labelingTimeStart = System.currentTimeMillis();
                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(userList, dataset, dataset.getInstances().get(j), user);
                if (tempAssignment != null){ //returns null if there is no space for any further label
                    newLog.write("*User " + user.getId() + " has labeled instance " + dataset.getInstances().get(j).getId() + ".*");//logging
                    assignments.add(tempAssignment);
                }
                long labelingTimeEnd = System.currentTimeMillis();
                long labelingTime = labelingTimeEnd - labelingTimeStart;
                assignments.get(assignments.size()-1).setTimeSpent(labelingTime);
            } // labeling ends

            newLog.write("***User " + user.getId() + " has logged out.***");
            long logoutDate = System.currentTimeMillis();
            long onlineTime = ( logoutDate - loginDate ) / 100;
            user.getUserPerformanceMetrics().setTotalTimeSpentLabeling((float)onlineTime);
            user.getUserPerformanceMetrics().setAverageTimeSpentLabeling();
        }
        datasetController.writeDataset(storage);//Printing output files

        */


