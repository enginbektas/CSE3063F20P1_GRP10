import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//C:\Users\engin\Desktop\CES3063F20_LabelingProject_Input-1
public class DatasetReader {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser(); // create JSON parser
        try {

            Object obj = parser.parse(new FileReader("C:\\Users\\engin\\Desktop\\CES3063F20_LabelingProject_Input-1.json"));
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
                instances[i] = new Instance(instanceId, instance);
            }

            System.out.println(instances[1].instance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Label {
    public long id;
    public String text;
    public Label(long id, String text) {
        this.id = id;
        this.text = text;
    }

}
class Instance {
    public long id;
    public String instance;

    public Instance(long id, String instance) {
        this.id = id;
        this.instance = instance;
    }

}



