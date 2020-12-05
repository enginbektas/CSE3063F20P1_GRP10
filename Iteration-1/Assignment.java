import java.util.ArrayList;
import java.util.List;


public class Assignment {
    private int instanceId;
    private List<Integer> classLabelIds;
    private int userId;
    private String date;
    private transient Mechanism mechanism;//Which mechanism made this assigment



    public Assignment(Instance instance, int userId, String date, List<Label> labels, Mechanism mechanism){
        this.instanceId = (int)instance.getId();
        this.classLabelIds = new ArrayList<Integer>();
        setLabels(labels);
        this.userId = userId;
        this.date = date;
        this.mechanism = mechanism;
    }

    public int getUserId() {
        return userId;
    }
    public int getInstance() {
        return instanceId;
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getLabels() {
        return classLabelIds;
    }

    public void setInstance(Instance instance) {
        this.instanceId = (int)instance.getId();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLabels(List<Label> labels) {//Only gets labels id's
        int i = 0;
        for (Label label : labels) {
            classLabelIds.add(labels.get(i).getId());
            i++;
        }
    }
}