import java.util.List;


public class Assigment {
    private Instance instance;
    private int userId;
    private String date;
    private List<Label> labels;

    public Assigment(Instance instance, int userId, String date, List<Label> labels){
        this.date = date;
        this.instance = instance;
        this.userId = userId;
        this.labels = labels;
    }
}
