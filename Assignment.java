import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.List;


public class Assignment {
    private int instanceId;
    private List<Integer> classLabelIds;
    private int userId;
    private String date;
    //private transient Mechanism mechanism;//Which mechanism made this assigment
    private transient List<User> userList;
    private transient Dataset dataset;
    private transient User user;
    private transient Instance instance;



    public Assignment(Dataset dataset, List<User> userList, Instance instance, User user, String date, List<Label> labels){
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

    public List<Integer> getLabels() {
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

    public void setLabels(List<Label> labels) {//Only gets labels id's
        int i = 0;
        for (Label label : labels) {
            classLabelIds.add(labels.get(i).getId());
            i++;

        }
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public List<Integer> getClassLabelIds() {
        return classLabelIds;
    }

    public void setClassLabelIds(List<Integer> classLabelIds) {
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
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