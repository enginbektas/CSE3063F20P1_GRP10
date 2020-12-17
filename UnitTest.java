
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//#lines 28
public class UnitTest {
    public static void main(String[] args) {
        Log newLog = new Log();//Creating log
        newLog.editLog();


        // TODO read config (
        File file = new File("");
        DatasetController datasetController = new DatasetController();//Creating controller
        //TODO choose current dataset
        Dataset currentDataset = new Dataset();

        //TODO create users
        ArrayList<User> users = new ArrayList<>();
        //TODO if output exists create dataset from output and assignments
        ArrayList<Storage> storages = new ArrayList<>();


        ArrayList<Assignment> assignments
        //else from config


        //TODO assign labels to current  dataset
        for (User user: users) {
            for (int i = 0; i < user.getDatasetIds().size(); i++) {
                if (user.getDatasetIds().get(i) == currentDatasetId)
                    //TODO assign loop

                    //TODO after every assign update json files
            }
        }






































        /*Storage storage = new Storage();//Creating Storage
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

        for(User user :userList){
            UserPerformanceMetrics a = new UserPerformanceMetrics(user);
            


        }



        RandomLabelingMechanism randomLabelingMechanism = new RandomLabelingMechanism("RandomMechanism");//Creating mechanism

        for (User user : userList) {//Loop for every user to label every instance
            newLog.write("***User id = " + user.getId() + " has logged in.***");//Logging user logins
            long loginDate = System.currentTimeMillis();
            for (int j = 0; j < dataset.getInstances().size(); j++) {//Loop for labeling every instance
                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(userList, dataset, dataset.getInstances().get(j), user);
                if (tempAssignment != null){//returns null if there is no space for any further label
                    newLog.write("*User " + user.getId() + " has labeled instance " + dataset.getInstances().get(j).getId() + ".*");//logging
                    assignments.add(tempAssignment);
                }
            }
            newLog.write("***User " + user.getId() + " has logged out.***");
            long logoutDate = System.currentTimeMillis();
            long onlineTime = logoutDate - loginDate;
            onlineTime = onlineTime / 60; // seconds
            user.getUserPerformanceMetrics().setAverageTimeSpentLabeling((float)onlineTime);

        }
        datasetController.writeDataset(storage);//Printing output files*/
    }
}