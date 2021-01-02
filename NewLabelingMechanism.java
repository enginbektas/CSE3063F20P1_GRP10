import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class NewLabelingMechanism extends Mechanism{
    private String mechanismName;
    private Log log;
    private transient ArrayList<Assignment> assignments;
    public NewLabelingMechanism(String mechanismName){
        this.mechanismName = mechanismName;
        this.assignments = new ArrayList<>();
        this.log = new Log();
    }
    public Assignment newMechanismn(ArrayList<User> userList, Dataset dataset, Instance instance, User user){
        double labelingTimeStart = System.currentTimeMillis();
        ArrayList<Label> label = new ArrayList<>();
        for(int i=1;i<=dataset.getInstances().size();i++){
            if(dataset.getInstance(i).getLabels().size()<dataset.getMaxNumOfLabelsPerInstance()){
                int a = defineType(dataset.getInstance(i));
                int index=0;
                if(a<0){
                    index=2;
                }
                else if (a==0){
                    index=3;
                }
                else if(a>0){
                    index=1;
                }
                dataset.getInstance(i).getLabels().add(dataset.getLabel(index));
                label.add(dataset.getLabel(index));//Instance instance id is labeled by label
                log.write("Instance " + dataset.getInstance(i).getId() + " is labeled by label "  + dataset.getLabel(index).getText() + ".");
            }
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        String dateString =  formatter.format(date);
        double labelingTimeEnd = System.currentTimeMillis();
        double labelingTime = labelingTimeEnd - labelingTimeStart;
        Assignment assignment = new Assignment(dataset,userList,instance,user,dateString,label, labelingTime);
        assignments.add(assignment);
        return assignment;
    }
    private int defineType(Instance instance){
        String positiveWords[]={"hızlı","iyi","düzgün","ilgili","memnun","başarılı","güzel","uygun","ucuz","kaliteli","sıkıntısız","alakalı","problemsiz","hasarsız","verimli","yeterli","artı","basarili"};
        String negativeWords[]={"yavaş","kötü","bozuk","ilgisiz","hatalı","başarısız","kalitesiz","pahalı","dandik","sıkıntılı","alakasız","problemli","hasarlı","yok","verimsiz","yetersiz","çalışmıyor","bitiri"};
        int ct=0;
        for(int i=0;i<positiveWords.length;i++){
            if(instance.getInstance().toLowerCase().contains(positiveWords[i]))
                ct++;
        }
        for(int i=0;i<negativeWords.length;i++){
            if(instance.getInstance().toLowerCase().contains(negativeWords[i])){
                ct--;
            }
        }
        return ct;
    }
}
