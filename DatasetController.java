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
                //String instance = (String) obj2.escape((String) obj2.get("instance")); This line of code returns a string with the escape characters. But still it's not how it's supposed to be.
                users.add(new User((int)userId, userName, userType));
            }
            //this block gets the info of labels from the input and assigns it to an array of labels
            //System.out.println(labels[2].text);
            //this block gets the info of instances from the input and assigns it to an array of instances


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
            //System.out.println(labels[2].text);
            //this block gets the info of instances from the input and assigns it to an array of instances
            JSONArray jsonArrayForInstances = (JSONArray) jsonObject.get("instances");
            Instance[] instances = new Instance[jsonArrayForInstances.size()];
            for (int i=0; i<jsonArrayForInstances.size(); i++) { //assigns the given instances in the input to the instances array
                JSONObject obj2 = (JSONObject) jsonArrayForInstances.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long instanceId = (long) obj2.get("id"); //obj2 is now the element of the array
                String instance = (String) obj2.get("instance");
                //String instance = (String) obj2.escape((String) obj2.get("instance")); This line of code returns a string with the escape characters. But still it's not how it's supposed to be.
                instances[i] = new Instance(instanceId, instance);
            }
            dataset.setInstances(Arrays.asList(instances));
            System.out.println(instances[1].getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public void writeDataset(Dataset dataset, List<Assignment> assignments, List<User> users){
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(dataset);
        StringBuilder sb = new StringBuilder(json);
        sb.append("\n\"class label assignments\":" + gson.toJson(assignments));
        sb.append("\nusers:" + gson.toJson(users));

        //System.out.println(sb);

        try (FileWriter file = new FileWriter("testOutput.json")) {
            file.write(sb.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO
    /*public Dataset createDataset(){

        return ;
    }*/

    //TODO
    //public readLabel()



    //TODO
    //public readInstance()

}







