
import java.util.ArrayList;
import java.util.List;

class Instance {
    private int id;
    private String instance;
    private transient List<Label> labels;

    public Instance(long id, String instance) {
        this.id = (int)id;
        this.instance = instance;
        this.labels = new ArrayList<Label>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public void setLabels(List<Label>  labels) {
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String getInstance() {
        return instance;
    }
}