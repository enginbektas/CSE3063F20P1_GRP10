import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private File file;
    private FileWriter fileWriter;

    public Log(){

    }

    public boolean write(String message) {
        File f = new File("log.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(message);
            System.out.println(message);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}