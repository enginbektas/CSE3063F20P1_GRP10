import java.util.List;

class Instance {
    private long id;
    private String instance;
    private List<Label> labels;

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

    public void setLabels(List<Label>  labels) {
        this.labels = labels;
    }

    public long getId() {
        return id;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String getInstance() {
        return instance;
    }
}