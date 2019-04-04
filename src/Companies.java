import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Companies extends Podjetje implements JsonSupport{
    ArrayList<Podjetje> P = new ArrayList<Podjetje>();
    @Override
    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //JsonElement el = gson.toJson(_name);
        return gson.toJson(this.P.toString());
    }
    @Override
    public Companies fromJson(String st){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Companies cp = gson.fromJson(st,Companies.class);
        return cp;
    }
}
