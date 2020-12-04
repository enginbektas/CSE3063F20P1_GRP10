import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//C:\Users\engin\Desktop\CES3063F20_LabelingProject_Input-1
public class DatasetController {
    public static void main(String[] args) {


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

    public void writeDataset(Dataset dataset, List<LabelAssignment> assignments, List<User> users){
        JSONObject datasetObject = new JSONObject();

        datasetObject.put("dataset id", dataset.getId());
        datasetObject.put("dataset name", dataset.getName());
        datasetObject.put("maximum number of labels per instance", dataset.getMaxNumOfLabelsPerInstance());

        JSONArray labels = new JSONArray();
        for (int i = 0; i < dataset.getLabels().size(); i++){
            JSONObject labelObject = new JSONObject();
            labelObject.put("label id",dataset.getLabels().get(i).getId());
            labelObject.put("label text",dataset.getLabels().get(i).getText());
            labels.add(labelObject);
        }
        datasetObject.put("class labels", labels);

        JSONArray instances = new JSONArray();
        for (int i = 0; i < dataset.getInstances().size(); i++){
            JSONObject instanceObject = new JSONObject();
            instanceObject.put("label id",dataset.getInstances().get(i).getId());
            instanceObject.put("label text",dataset.getInstances().get(i).getInstance());
            instances.add(instanceObject);
        }
        datasetObject.put("instances", instances);

        JSONArray assignmentsArray = new JSONArray();
        for (int i = 0; i < assignments.size(); i++){
            JSONObject assignmentObject = new JSONObject();
            assignmentObject.put("user id", assignments.get(i).userID);
            assignmentObject.put("instance id", assignments.get(i).instanceToAssignLabel.getId());
            assignmentObject.put("datetime", assignments.get(i).dateTime);
            JSONArray classLabelIDs = new JSONArray();
            for (int j = 0; j < assignments.get(i).assignedLabels.size(); j++){
                classLabelIDs.add(assignments.get(i).assignedLabels.get(j).getId());
            }
            assignmentObject.put("class label ids", classLabelIDs);
            assignmentsArray.add(assignmentObject);
        }
        datasetObject.put("class label assignments", assignmentsArray);

        JSONArray usersArray = new JSONArray();
        for (int i = 0; i < users.size(); i++){
            JSONObject userObject = new JSONObject();
            userObject.put("user id", users.get(i).getId());
            userObject.put("user name", users.get(i).getUserName());
            userObject.put("user type", users.get(i).getUserType());
            usersArray.add(userObject);
        }
        datasetObject.put("users", usersArray);

        System.out.println(datasetObject);
        /*try (FileWriter file = new FileWriter("testOutput.json")) {
            file.write(datasetObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
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







