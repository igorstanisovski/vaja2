import com.google.gson.Gson;

import java.io.*;

public class Helper{
    public static void writer(String a,String filename){
        try (FileWriter writer = new FileWriter(filename)) {
        writer.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String reader(String filename){
        String current;
        String returnString = new String();
        try (Reader reader = new FileReader(filename)) {
            BufferedReader bf = new BufferedReader(reader);
            while ((current = bf.readLine()) != null) {
                returnString = returnString + current;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }
}
