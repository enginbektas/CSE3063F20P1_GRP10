public class PLabel {
    private int num;
    private Label label;
    private double percentage;
    public PLabel(Label label,int num){
        this.label = label;
        this.num =  num;
    }
    public Label getLabel(){
        return label;
    }
    //increase number of this label by one
    public void incNum(){
        this.num=num+1;
    }
    public int getNum(){ return num; }
    //calculate percentage
    public void setPercentage(int total) {
        this.percentage = num*1.0/total*100.0;
    }
    public double getPercentage(){ return percentage; }
}
