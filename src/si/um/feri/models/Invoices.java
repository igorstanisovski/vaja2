package si.um.feri.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Invoices extends Racun implements JsonSupport {
    ArrayList<Racun> racuni = new ArrayList<Racun>();
    @Override
    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
        //JsonElement el = gson.toJson(_name);
        //return gson.toJson(this.Racuni.toString());
    }
    @Override
    public void fromJson(String st){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Invoices inv = gson.fromJson(st, Invoices.class);
        racuni = inv.racuni;
        //return inv;
    }

}
