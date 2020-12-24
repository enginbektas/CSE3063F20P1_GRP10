import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.List;
public class InstancePerformanceMetric {
    private Instance instance;
    private int TotalNumberOfLabel;
    private int numberOfUniqueLabelAssignment;
    private int numberOfUniqueUsers;
    private double entropy;
    private ArrayList<User> users;
    private ArrayList<PLabel> plabel;
    public InstancePerformanceMetric(Instance instance){
        this.instance=instance;
        this.TotalNumberOfLabel=0;
        this.numberOfUniqueLabelAssignment=0;
        this.numberOfUniqueUsers=0;
        this.entropy=0;
    }

    public void update(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
        setTotalNumberOfLabel();
        labelCalculator();
    }
    public void labelCalculator(){
        TotalNumberOfLabel=instance.getLabels().size();

        int i=0,j=0;
        for(i=0;i<instance.getLabels().size();i++){
            for(j=i+1;j<instance.getLabels().size();j++){
                if(instance.getLabels().get(i)==instance.getLabels().get(j))
                    break;
            }
            if(j==instance.getLabels().size())
                numberOfUniqueLabelAssignment++;
        }

        if(instance.getUser_instances().size()==1){
            numberOfUniqueUsers++;
        }
        else {
            for (i = 0; i < instance.getUser_instances().size(); i++) {

                for (j = i+1; j < instance.getUser_instances().size(); j++) {
                    if (instance.getUser().get(i) == instance.getUser().get(j))
                        break;
                }
                if (j == instance.getUser().size())
                    numberOfUniqueUsers++;
            }
        }
        plabel = new ArrayList<PLabel>();
        PLabel tplabel = new PLabel(instance.getLabels().get(0),1);
        tplabel.setPercentage(TotalNumberOfLabel);
        plabel.add(tplabel);
        for(i=1;i<instance.getLabels().size();i++){
            for (j=0;j<plabel.size();j++){
                if(plabel.get(j).getLabel()==instance.getLabels().get(i)) {
                    plabel.get(j).incNum();
                    break;
                }
            }
            if(j==plabel.size()){
                tplabel= new PLabel(instance.getLabels().get(i),1);
                plabel.add(tplabel);
                tplabel.setPercentage(TotalNumberOfLabel);
            }
        }
        ArrayList<PLabel> pLabel2 = new ArrayList<PLabel>();
        pLabel2.add(plabel.get(0));
        for (i=1,j=0;i<plabel.size();i++){
            if(plabel.get(i).getNum()>pLabel2.get(j).getNum()){
                pLabel2.remove(j);
                pLabel2.add(plabel.get(j));
            }
            else if (plabel.get(i).getNum()== pLabel2.get(j).getNum()){
                pLabel2.add(plabel.get(i));
                j++;
            }
        }
        for (i=0;i<pLabel2.size();i++){
            entropy=entropy+(0-pLabel2.get(i).getPercentage()/100.0*Math.log(pLabel2.get(i).getPercentage()/100.0)/Math.log(numberOfUniqueLabelAssignment));
        }

    }

    public int getTotalNumberOfLabel(){ return TotalNumberOfLabel; }

    public void setTotalNumberOfLabel() {
        TotalNumberOfLabel=instance.getLabels().size();
    }

    public int getNumberOfUniqueLabelAssignment(){ return numberOfUniqueLabelAssignment; }

    public int getNumberOfUniqueUsers(){ return numberOfUniqueUsers; }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
