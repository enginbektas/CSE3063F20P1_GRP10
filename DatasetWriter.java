import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DatasetWriter {
    public void writeDataset(Dataset dataset, List<labelAssignment> assignments, List<User> users){
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
}

class labelAssignment{
    public Instance instanceToAssignLabel;
    public List<Label> assignedLabels;
    public int userID;
    public Date dateTime;
}
