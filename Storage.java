import java.util.List;

public class Storage {
    private Dataset dataset;
    private List<Assigment> assigments;
    private List<User> users;


    public Storage(){

    }

    public void setAssigments(List<Assigment> assigments) {
        this.assigments = assigments;
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

    public List<Assigment> getAssigments() {
        return assigments;
    }

    public List<User> getUsers() {
        return users;
    }


}
