import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Invoices extends Racun implements JsonSupport {
    ArrayList<Racun> Racuni = new ArrayList<Racun>();
    @Override
    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //JsonElement el = gson.toJson(_name);
        return gson.toJson(this.Racuni.toString());
    }
    @Override
    public Invoices fromJson(String st){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Invoices inv = gson.fromJson(st,Invoices.class);
        return inv;
    }

}
