import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public Log(){

    }

    public boolean write(String message) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        String s =  formatter.format(date);

        File f = new File("log.txt");
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(message + " " + s + "\n");
            System.out.println(message + " " + s);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}