import java.util.List;

public class Storage {
    private Dataset dataset;
    private List<Assignment> assignments;
    private List<User> users;


    public Storage(){

    }

    public void setAssigments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public List<Assignment> getAssigments() {
        return assignments;
    }

    public List<User> getUsers() {
        return users;
    }


}
