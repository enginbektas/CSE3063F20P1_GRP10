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

    public List<User> userReader(File file) {
        List<User> users = new ArrayList<User>();
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
                users.add(new User((int)userId, userName, userType));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<Assignment> assignmentsReader(Dataset dataset, File file) {

        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject

            long id = (long) jsonObject.get("instance id"); // setting dataset id
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

    public Config configReader(File file) {
        Config config ;
        List<User> users = new ArrayList<User>();
        ArrayList<Dataset> datasets =  new ArrayList<Dataset>();
        ArrayList<Storage> storages = new ArrayList<Storage>(); // Where can I get this?
        ArrayList<Object> datasetIds = new ArrayList<Object>();
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject

            JSONArray jsonArrayForUsers = (JSONArray) jsonObject.get("users");
           // Instance[] instances = new Instance[jsonArrayForUsers.size()];
            for (int i=0; i<jsonArrayForUsers.size(); i++) { //assigns the given instances in the input to the instances array
                JSONObject obj2 = (JSONObject) jsonArrayForUsers.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long userId = (long) obj2.get("user id"); //obj2 is now the element of the array
                String userName = (String) obj2.get("user name");
                String userType = (String) obj2.get("user type");

                JSONArray  jsonDatasetIds = (JSONArray) obj2.get("dataset ids");
                for(int a = 0; a<jsonDatasetIds.size();a++){
                    datasetIds.add(jsonDatasetIds.get(a));
                }

                users.add(new User((int)userId, userName, userType,datasetIds));
            }
            JSONArray jsonArrayForDatasets = (JSONArray) jsonObject.get("datasets");
            for(int i = 0;i<jsonArrayForDatasets.size(); i++){
                JSONObject obj2 = (JSONObject) jsonArrayForDatasets.get(i);
                long datasetId = (long) obj2.get("dataset id");
                String datasetName = (String) obj2.get("dataset name");
                String datasetPath = (String) obj2.get("path");
                datasets.add(new Dataset())
                
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
    public 

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







