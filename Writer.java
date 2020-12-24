import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public Writer() {

    }
    public void writeDataset(Object obj, String outputName, boolean consolePrint, boolean append){

        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(obj);
        StringBuilder sb = new StringBuilder(json);

        if (consolePrint){
            System.out.println(sb);
        }

        try (FileWriter file = new FileWriter(outputName, append)) {
            file.write(sb.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







