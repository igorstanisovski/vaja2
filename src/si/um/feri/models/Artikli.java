package si.um.feri.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Artikli extends Artikel implements JsonSupport {
    ArrayList<Artikel> Artikli = new ArrayList<>();

    public String toString(){
        String ss = new String();
        for(int i = 0;i<Artikli.size();i++){
            ss=ss + "Artikel " + i + ": " + Artikli.get(i).getName() + "\tKolicina: " + Artikli.get(i).getKolicina() +
                    "\tCena: " + Artikli.get(i).getPrice() + "\tEAN: " + Artikli.get(i).getEAN()+
                    "Je EAN veljaven: \t" + Artikli.get(i).checkDigit(Artikli.get(i).getEAN()) + "\nDrzava: "+ Artikli.get(i).getDrzava()+
                    "\n" ;
        }
        return ss;
    }
    @Override
    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
        //JsonElement el = gson.toJson(_name);
        //return gson.toJson(this._name + this._drzava + this._price + this._EAN + this._kolicina);
    }
    @Override
    public void fromJson(String st){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Artikli art = gson.fromJson(st, Artikli.class);
        Artikli = art.Artikli;
        //return art;
    }
}
