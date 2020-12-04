import java.io.File;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
        Log newLog = new Log();
        DatasetController datasetController = new DatasetController();
        File file = new File("inputs\\CES3063F20_LabelingProject_Input-1.json");
        datasetController.reader(file);
    }
}
