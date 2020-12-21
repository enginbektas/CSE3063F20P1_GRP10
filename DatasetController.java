import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatasetController {

    public DatasetController() {

    }

    public ArrayList<User> userReader(File file)  {
        ArrayList<User> users = new ArrayList<User>();
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject

            JSONArray jsonArrayForUsers = (JSONArray) jsonObject.get("users");
            Instance[] instances = new Instance[jsonArrayForUsers.size()];

            for (int i=0; i<jsonArrayForUsers.size(); i++) { //assigns the given instances in the input to the instances array
                JSONObject obj2 = (JSONObject) jsonArrayForUsers.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long userId = (long) obj2.get("user id"); //obj2 is now the element of the array
                String userName = (String) obj2.get("user name");
                String userType = (String) obj2.get("user type");

                JSONArray jsonArrayForDatasetIds = (JSONArray) obj2.get("dataset ids");
                ArrayList<Integer> datasetIds = new ArrayList<>(); //set dataset ids to user
                for (int j=0; j<jsonArrayForDatasetIds.size(); j++) { // iterate size times
                    long datasetId = (long) jsonArrayForDatasetIds.get(j); //
                    datasetIds.add((int)datasetId); //
                }
                users.add(new User((int)userId, userName, userType, datasetIds));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<Storage> configController(File file) {
        //TODO iterate through configs datasets, check if they have output, if so read output with storageReader
        //  hold dataset and assignments and users, call assigner and set assignments to dataset,
        //  set dataset.getStorage.setDataset, dataset.getStorage.setAssignments, dataset.getStorage.setUsers
        // if not read dataset with datasetReader
        // add the dataset to the list, iterate for the next dataset
        // return the list of datasets
        ArrayList<Storage> storageList = new ArrayList<Storage>();
        List<User> userList = userReader(file);

        Dataset dataset = new Dataset();
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject
            JSONArray jsonArrayForDatasetPointers = (JSONArray) jsonObject.get("datasets");

            for (int i=0; i<jsonArrayForDatasetPointers.size(); i++) {
                Storage storage = new Storage();
                JSONObject obj2 = (JSONObject) jsonArrayForDatasetPointers.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long datasetId = (long) obj2.get("dataset id"); //obj2 is now the element of the array
                String path = (String) obj2.get("path");
                File outputFile = new File("Output"+datasetId);
                File inputFile = new File(path);
                if (outputFile.exists()) {
                    storage = storageReader(outputFile);
                    assigner(storage, userList);
                }
                else {//if no output, read inputstorage.setDataset(reader(new File(path)));
                    dataset = reader(inputFile);
                    storage.setDataset(dataset);
                }
                storage.getDataset().setStorage(storage);
                storageList.add(storage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageList;
    }

    public Dataset reader(File file) {
        Dataset dataset = new Dataset();
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject

            long id = (long) jsonObject.get("dataset id"); // setting dataset id
            dataset.setId((int) id);

            dataset.setName((String) jsonObject.get("dataset name"));
            dataset.setMaxNumOfLabelsPerInstance((int)
                    (long) jsonObject.get("maximum number of labels per instance"));

            //this block gets the info of labels from the input and assigns it to an array of labels
            JSONArray jsonArrayForClassLabels = (JSONArray) jsonObject.get("class labels");
            Label[] labels = new Label[jsonArrayForClassLabels.size()]; // create labels array

            for (int i=0; i<jsonArrayForClassLabels.size(); i++) { //assigns the given labels in the input to the labels array
                JSONObject obj2 = (JSONObject) jsonArrayForClassLabels.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long labelId = (long) obj2.get("label id"); //obj2 is now the element of the array
                String labelText = (String) obj2.get("label text");
                labels[i] = new Label(labelId, labelText);
            }
            dataset.setLabels(Arrays.asList(labels));

            //this block gets the info of instances from the input and assigns it to an array of instances
            JSONArray jsonArrayForInstances = (JSONArray) jsonObject.get("instances");
            Instance[] instances = new Instance[jsonArrayForInstances.size()];
            for (int i=0; i<jsonArrayForInstances.size(); i++) { //assigns the given instances in the input to the instances array
                JSONObject obj2 = (JSONObject) jsonArrayForInstances.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long instanceId = (long) obj2.get("id"); //obj2 is now the element of the array
                String instance = (String) obj2.get("instance");
                instances[i] = new Instance(instanceId, instance);
            }
            dataset.setInstances(Arrays.asList(instances));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public Storage storageReader(File file) {
        //TODO read storage, return storage
        Storage storage = new Storage();
        Dataset dataset = new Dataset();
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject
            //TODO DATASET
            dataset = reader(file);
            //TODO ASSIGNMENTS
            JSONArray jsonArrayForAssignments = (JSONArray) jsonObject.get("assignments");
            Assignment[] assignments = new Assignment[jsonArrayForAssignments.size()];
            for (int i=0; i<jsonArrayForAssignments.size(); i++) { //assigns the given instances in the input to the instances array
                JSONObject obj2 = (JSONObject) jsonArrayForAssignments.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long instanceId = (long) obj2.get("instanceId"); //obj2 is now the element of the array

                JSONArray jsonArrayForClassLabelIds = (JSONArray) obj2.get("ClassLabelIds");
                ArrayList<Integer> classLabelIds = new ArrayList<>();
                for (int j=0; j<jsonArrayForClassLabelIds.size(); j++) { // iterate size times
                    long classLabelId = (long) jsonArrayForClassLabelIds.get(j); //
                    classLabelIds.add((int)classLabelId); //
                }

                long userId = (long) obj2.get("userId");
                String date = (String) obj2.get("date");
                assignments[i] = new Assignment((int)instanceId, (int)userId, date, classLabelIds);
            }
            ArrayList<User> userList = new ArrayList<>();
            //TODO USERS
            userList = userReader(file);

            storage.setDataset(dataset);
            storage.setAssigments(Arrays.asList(assignments));
            storage.setUsers(userList);

            for (Assignment assignmentIter : storage.getAssigments()) {
                assignmentIter.setDataset(dataset); // Set dataset
                assignmentIter.setUserList(userList);  // Set userList
                assignmentIter.setUser(); // Set user
                assignmentIter.setInstance();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return storage;
    }

    public void assigner(Storage storage, List<User> userList) {
        for (Instance instance : storage.getDataset().getInstances()) {
            for (Assignment assignment : storage.getAssigments()) {
                if (instance.getId() == assignment.getInstanceId()) {
                    //TODO create a list of labels from assignments label ids, check them with datasets label ids and reach to instance itself
                    ArrayList<Label> labels = new ArrayList<>();
                    for (Integer labelId : assignment.getLabels())
                        for (Label label : storage.getDataset().getLabels())
                            if (labelId == label.getId())
                                labels.add(label);
                    Assignment newAssignment = new Assignment(storage.getDataset(), userList, instance, assignment.getUser(), assignment.getDate(), labels);
                    // TODO Assignment(Dataset dataset, List<User> userList,
                    //  Instance instance, User user, String date, List<Label> labels, Mechanism mechanism)
                }
            }
        }
    }

    public void writeDataset(Storage storage){
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(storage);
        StringBuilder sb = new StringBuilder(json);

        try (FileWriter file = new FileWriter("testOutput.json")) {
            file.write(sb.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







