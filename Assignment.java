import com.google.gson.annotations.SerializedName;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.List;


public class Assignment {
    @SerializedName("instance id")
    private int instanceId;
    @SerializedName("class label ids")
    private ArrayList<Integer> classLabelIds;
    @SerializedName("user id")
    private int userId;
    @SerializedName("datetime")
    private String date;
    //private transient Mechanism mechanism;//Which mechanism made this assigment
    private transient ArrayList<User> userList;
    private transient Dataset dataset;
    private transient User user;
    private transient Instance instance;




    public Assignment(Dataset dataset, ArrayList<User> userList, Instance instance, User user, String date, ArrayList<Label> labels){
        this.instanceId = instance.getId();
        this.classLabelIds = new ArrayList<>();
        setLabels(labels);
        this.date = date;
        //this.mechanism = mechanism;
        this.dataset = dataset;
        this.userList = userList;
        this.user = user;
        this.userId = user.getId();

        instance.getLabels().addAll(labels);

        //user.getUserPerformanceMetrics().update(this, dataset, instance);


    }


    public int getUserId() {
        return userId;
    }
    public int getInstanceId() {
        return instanceId;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Integer> getLabels() {
        return classLabelIds;
    }

    public void setInstance(Instance instance) {
        this.instanceId = (int)instance.getId();
    }
    public void setInstance() {
        for (Instance instance : dataset.getInstances())
            if (instance.getId() == instanceId)
                this.instance = instance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLabels(ArrayList<Label> labels) {//Only gets labels id's
        int i = 0;
        for (Label label : labels) {
            classLabelIds.add(labels.get(i).getId());
            i++;
            label.incrementNumberOfUses();
        }

    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public ArrayList<Integer> getClassLabelIds() {
        return classLabelIds;
    }

    public void setClassLabelIds(ArrayList<Integer> classLabelIds) {
        this.classLabelIds = classLabelIds;
    }
/*
    public Mechanism getMechanism() {
        return mechanism;
    }

    public void setMechanism(Mechanism mechanism) {
        this.mechanism = mechanism;
    }

 */

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public User getUser() {
        return user;
    }

    public void setUser() {
        for (User user : userList)
            if (user.getId() == userId)
                this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instance getInstance() {
        return instance;
    }


}