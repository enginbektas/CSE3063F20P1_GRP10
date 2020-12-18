
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//#lines 28
public class UnitTest {
    public static void main(String[] args) {


        Storage storage = new Storage; //Creating storage
        Dataset dataset = new Dataset(); //Creating dataset
        Log newLog = new Log(); //Creating log
        newLog.editLog();



        // TODO read config (
        File file = new File("");
        DatasetController datasetController = new DatasetController();//Creating controller

        //TODO create and read config
        Config config = datasetController.configReader(configFile);

        //TODO create users
        List<User> users = config.getUsers();
        //TODO create storage list if at least one exists
        List<Storage> storages = config.getStorages();
        //TODO create a list of datasets
        List<Dataset> datasets = config.getDatasets();
        for (Storage storageIter : storages)
            if (storageIter.getDataset().getId() == config.getCurrentDatasetId())
                dataset = storageIter.getDataset();

        //TODO choose current dataset
        if (dataset == null)
        for (Dataset datasetIter : datasets)
            if (datasetIter.getId() == config.getCurrentDatasetId())
                dataset = datasetIter;




        //TODO if output exists create dataset from output and assignments
        ArrayList<Storage> storages = new ArrayList<>();



        ArrayList<Assignment> assignments
        //else from config


        //TODO assign labels to current  dataset
        for (User user: users) {
            for (int i = 0; i < user.getDatasetIds().size(); i++) {
                if (user.getDatasetIds().get(i) == currentDatasetId);
                    //TODO assign loop

                    //TODO after every assign update json files
            }
        }

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
        datasetController.writeDataset(storage);//Printing output files*/


    }
}