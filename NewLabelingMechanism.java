public class NewLabelingMechanism extends Mechanism{
    private String mechanismName;
    private Log log;
    private Instance instance;
    public NewLabelingMechanism(Instance instance){
        this.instance = instance;
    }
    private int abc(){
        String positiveWords[]={"hızlı","iyi","düzgün","ilgili","memnun","başarılı","güzel","uygun"};
        String negativeWords[]={"hızlı","iyi","düzgün","ilgili","memnun","başarılı","güzel","uygun"};
        int ct=0;
        for(int i=0;i<positiveWords.length;i++){
            if(instance.getInstance().contains(positiveWords[i]))
                ct++;
        }
        for(int i=0;i<negativeWords.length;i++){
            if(instance.getInstance().contains(positiveWords[i])){
                ct--;
            }
        }
        return ct;


    }
}
