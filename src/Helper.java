import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Helper{
    public static void writer(Artikel a){
        Gson gson = new Gson();
        //String json = gson.toJson(a);
        //System.out.println(json);
        try (FileWriter writer = new FileWriter("D:\\Artikel.json")) {
            gson.toJson(a, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reader(){
        Gson gson = new Gson();
        try (Reader reader = new FileReader("D:\\Artikel.json")) {
            Artikel art = gson.fromJson(reader, Artikel.class);
            System.out.println(art);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
