
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UnitTest {
    public static void main(String[] args) throws InterruptedException {
        //TODO datasetController class needs to be updated.
        //TODO configReader, outputReader, datasetReader methods are required.

        File file = new File("Inputs\\config.json");
        Log newLog = new Log(); //Creating log
        newLog.editLog();

        Controller controller = new Controller();//Creating controller
        ArrayList<Storage> storageList = new ArrayList<>();
        ArrayList<User> userList = controller.userReader(file);

        int currentDatasetId = controller.getCurrentDatasetId(file);

        storageList = controller.configController(file, userList);
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
        HumanLabelingMechanism humanLabelingMechanism = new HumanLabelingMechanism("HumanMechanism");

        Scanner sc = new Scanner(System.in);
        while(true){
                System.out.println("Enter username:");
                String userName = sc.nextLine();
                boolean userNameFound = false;
                String foundPassword = "";
                User currentUser = new User();

                for(int i = 0; i < userList.size(); i++){
                    if(userList.get(i).getUserType().equalsIgnoreCase("Human")
                    && userName.equals((userList.get(i).getUserName())) && userList.get(i).getDatasetIds().contains(dataset.getId())){
                        userNameFound = true;
                        foundPassword = userList.get(i).getPassword();
                        currentUser = userList.get(i);
                    }
                }
                if(userNameFound){//If username is true
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    if(password.equals(foundPassword)){//If password is true
                        //TODO call label assignment for human here

                        for (Instance instance : dataset.getInstances()) {

                            float labelingTime = 0;

                            Assignment tempAssignment = humanLabelingMechanism.humanMechanism(userList, dataset, instance, currentUser);

                            labelingTime += tempAssignment.getTime();

                            
                            currentUser.getUserPerformanceMetrics().setTotalTimeSpentLabeling(currentUser.getUserPerformanceMetrics().getTotalTimeSpentLabeling() + labelingTime);
                            currentUser.getUserPerformanceMetrics().setAverageTimeSpentLabeling();

                            ArrayList<DatasetPerformanceMetric> datasetPerformanceMetricsList = new ArrayList<>();
                            ArrayList<UserPerformanceMetric> userPerformanceMetricsList = new ArrayList<>();
                            ArrayList<ArrayList<InstancePerformanceMetric>> instancePerformanceMetricList = new ArrayList<>();

                            ArrayList<InstancePerformanceMetric> instancePerformanceMetricListTemp = new ArrayList<>();

                            for (User user1 : userList) {
                                userPerformanceMetricsList.add(user1.getUserPerformanceMetrics());
                            }
                            for (Storage storage: storageList) {
                                writer.writeDataset(storage, "Outputs//Output" + storage.getDataset().getId() + ".json", false, false);
                                datasetPerformanceMetricsList.add(storage.getDataset().getDatasetPerformanceMetric());
                                for (Instance instance1 : storage.getDataset().getInstances()) {
                                    instancePerformanceMetricListTemp.add(instance1.getInstancePerformanceMetrics());
                                }
                                instancePerformanceMetricList.add(instancePerformanceMetricListTemp);
                            }

                            PerformanceMetrics performanceMetrics = new PerformanceMetrics(datasetPerformanceMetricsList, userPerformanceMetricsList, instancePerformanceMetricList);
                            writer.writeDataset(performanceMetrics, "Outputs//PerformanceMetrics" + ".json", false, false);
                        }

                    }
                    else{
                        System.out.println("Wrong username/password! or Unauthorized Dataset!");
                    }
                }
                else if(userName.equals("")){
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    if(password.equals("")){//If username and password is empty
                        for (User user : currentUserList) {//Loop for every bot user to label every instance

                            if(user.getUserType().equalsIgnoreCase("Human"))//skip human users
                                continue;

                            newLog.write("***User " + user.getId() + " has logged in.***");//Logging user logins

                            ArrayList<Instance> tempInstances = new ArrayList<>();
                            ArrayList<Instance> nonLabeledInstances = (ArrayList<Instance>) dataset.getInstances().clone();

                            if ( tempInstances.size() > 0)
                                tempInstances.get( (int) (Math.random() * tempInstances.size() ));

                            while (nonLabeledInstances.size() > 0) {//Loop for labeling every instance
                                double randomNumber = Math.random() * 100;
                                if ( randomNumber < user.getConsistencyCheckProbability() * 100 && user.getUserPerformanceMetrics().getUniqueInstancesLabeled().size() != 0) {
                                    tempInstances = (ArrayList<Instance>) user.getUserPerformanceMetrics().getUniqueInstancesLabeled().clone();
                                } else {
                                    tempInstances = nonLabeledInstances;
                                }
                                Instance tempInstance = null;
                                if(tempInstances.size() > 0)
                                    tempInstance = tempInstances.get((int) Math.random() * ( tempInstances.size() - 1));

                                if (nonLabeledInstances.contains(tempInstance)) {
                                    nonLabeledInstances.remove(tempInstance);
                                }
                                for (Instance instance1 : dataset.getInstances()) {
                                    if (instance1.getId() == tempInstance.getId()) {
                                        tempInstance = instance1;
                                    }
                                }

                                float labelingTime = 0;
                                Thread.sleep((long) (Math.random() * 250));
                                Assignment tempAssignment = randomLabelingMechanism.randomMechanism(userList, dataset, tempInstance, user);
                                if (tempAssignment != null){ //returns null if there is no space for any further label
                                    newLog.write("*User " + user.getId() + " has labeled instance " + tempInstance.getId() + ".*");//logging
                                    assignments.add(tempAssignment);
                                    labelingTime += tempAssignment.getTime();
                                }

                                user.getUserPerformanceMetrics().setTotalTimeSpentLabeling(user.getUserPerformanceMetrics().getTotalTimeSpentLabeling() + labelingTime);
                                user.getUserPerformanceMetrics().setAverageTimeSpentLabeling();

                                ArrayList<DatasetPerformanceMetric> datasetPerformanceMetricsList = new ArrayList<>();
                                ArrayList<UserPerformanceMetric> userPerformanceMetricsList = new ArrayList<>();
                                ArrayList<ArrayList<InstancePerformanceMetric>> instancePerformanceMetricList = new ArrayList<>();

                                ArrayList<InstancePerformanceMetric> instancePerformanceMetricListTemp = new ArrayList<>();

                                for (User user1 : userList) {
                                    userPerformanceMetricsList.add(user1.getUserPerformanceMetrics());
                                }
                                for (Storage storage: storageList) {
                                    writer.writeDataset(storage, "Outputs//Output" + storage.getDataset().getId() + ".json", false, false);
                                    datasetPerformanceMetricsList.add(storage.getDataset().getDatasetPerformanceMetric());
                                    for (Instance instance : storage.getDataset().getInstances()) {
                                        instancePerformanceMetricListTemp.add(instance.getInstancePerformanceMetrics());
                                    }
                                    instancePerformanceMetricList.add(instancePerformanceMetricListTemp);
                                }

                                PerformanceMetrics performanceMetrics = new PerformanceMetrics(datasetPerformanceMetricsList, userPerformanceMetricsList, instancePerformanceMetricList);
                                writer.writeDataset(performanceMetrics, "Outputs//PerformanceMetrics" + ".json", false, false);

                            } // labeling ends
                            newLog.write("***User " + user.getId() + " has logged out.***");
                        }
                    }
                }
                else{
                    System.out.println("Wrong username/password or Unauthorized Dataset!");
                }
        }
    }
}





