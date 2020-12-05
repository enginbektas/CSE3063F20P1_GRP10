
class Label {
    private int id;
    private String text;

    public Label(long id, String text) {
        this.id = (int)id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}