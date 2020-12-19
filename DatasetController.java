import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class DatasetController {

    public DatasetController() {

    }

    public ArrayList<User> userReader(File file) {
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

                JSONArray jsonArrayForDatasetIds = (JSONArray) obj2.get("datasetIds");
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
        ArrayList<DatasetPointer> datasetPointers = new ArrayList<DatasetPointer>();
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

                JSONArray jsonArrayForDatasetIds = (JSONArray) obj2.get("datasetIds");
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

            //TODO ASSIGNMENT
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
            userList = userReader(file);

            storage.setDataset(dataset);
            storage.setAssigments(Arrays.asList(assignments));
            storage.setUsers(userList);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return storage;
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







