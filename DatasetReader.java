import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//C:\Users\engin\Desktop\CES3063F20_LabelingProject_Input-1
public class DatasetReader {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader("C:\\Users\\Hasan\\IdeaProjects\\CSE3063F20P1_GRP10\\Inputs\\CES3063F20_LabelingProject_Input-1.json"));
            JSONObject jsonObject = (JSONObject) obj; //assign the parsed version of our file to a JSONObject

            long id = (long) jsonObject.get("dataset id");
            String datasetName = (String) jsonObject.get("dataset name");
            long maxNumOfLabelsPerInstance = (long) jsonObject.get("maximum number of labels per instance");

            //this block gets the info of labels from the input and assigns it to an array of labels
            JSONArray jsonArrayForClassLabels = (JSONArray) jsonObject.get("class labels");
            Label[] labels = new Label[jsonArrayForClassLabels.size()]; // create labels array
            for (int i=0; i<jsonArrayForClassLabels.size(); i++) { //assigns the given labels in the input to the labels array
                JSONObject obj2 = (JSONObject) jsonArrayForClassLabels.get(i); //declare obj2 to i'th element of JSON classlabelsarray
                long labelId = (long) obj2.get("label id"); //obj2 is now the element of the array
                String labelText = (String) obj2.get("label text");
                labels[i] = new Label(labelId, labelText);
            }
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

            System.out.println(instances[1].getInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



class Instance {
    private long id;
    private String instance;
    private Label[] labels;

    public Instance(long id, String instance) {
        this.id = id;
        this.instance = instance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public void setLabels(Label[] labels) {
        this.labels = labels;
    }

    public long getId() {
        return id;
    }

    public Label[] getLabels() {
        return labels;
    }

    public String getInstance() {
        return instance;
    }
}



