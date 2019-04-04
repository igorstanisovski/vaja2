import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import javax.swing.*;
import java.lang.reflect.AnnotatedType;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Random;



public class Artikel implements Searchable,JsonSupport{
    private String _name;
    private String _drzava;
    private BigDecimal _price;
    private String _EAN;
    private BigDecimal _kolicina;
    private int _ID;
    private int _oddelekS;

    public void setDrzava(String _drzava) {
        this._drzava = _drzava;
    }

    public String getDrzava() {
        return _drzava;
    }

    public void setOddelekS(int s) {
        this._oddelekS = s;
    }

    public int getOddelekS() {
        return this._oddelekS;
    }

    protected final BigDecimal _davcnaStopnja=new BigDecimal("0.22");

    @Override
    public Boolean search(String s) {
        if(_name == s || _drzava==s || _price.toString() == s || _EAN == s || _kolicina.toString()==s
        || _davcnaStopnja.toString()==s)
            return true;
        else return false;

    }

    @Override
    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //JsonElement el = gson.toJson(_name);
        return gson.toJson(this._name + this._drzava + this._price + this._EAN + this._kolicina);
    }
    @Override
    public Artikel fromJson(String st){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Artikel art = gson.fromJson(st,Artikel.class);
        return art;
    }

    public Artikel(){
        Random rnd = new Random();
        this._ID = 1000 + rnd.nextInt(9000);
//        Random rnd1 = new Random();
//        this._oddelekS = 100 + rnd1.nextInt(900);
    }
    public void setEAN(){
        this._EAN = generateEAN();
    }
    public String generateEAN(){
        String sss = new String();
        if(this._EAN == null){
        String o = String.valueOf(getOddelekS());
        String id = String.valueOf(this._ID);
        String teza = String.valueOf(getKolicina());
        String ss = o + id + teza;
        int a = makeCheckDigit(ss);
        String checkdigit = String.valueOf(a);
        sss = o + id + teza + checkdigit;
        }
        else{
            JOptionPane.showMessageDialog(null,"EAN že obstaja." , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
        }
        return sss;
    }


    public BigDecimal getKolicina(){
        return _kolicina;
    }

//    public Boolean checkDigit(String b){
//
//
//
//
//        long last = a%10;
//        long suma = 0;
//        a = a/10;
//        int poz = 7;
//        while(a > 0){
//            long number = a%10;
//            if(poz % 2 == 0){
//                long digit = number * 1;
//                suma = suma + digit;
//            }
//            else{
//                long digit = number * 3;
//                suma = suma + digit;
//            }
//            a = a/10;
//            poz--;
//        }
//        long b = suma%10;
//        long checkeddigit;
//        if(b>0){
//            checkeddigit = 10-b;
//        }
//        else
//            checkeddigit = 0;
//
//        if(checkeddigit == last){
//            return true;
//        }
//        else return false;
//    }

    public int makeCheckDigit(String e){
        int a = Integer.parseInt(e);
        //int i = 0;
        ArrayList<Integer> stevila = new ArrayList<>();
        while(a > 0){
            stevila.add(a%10);
            a = a/10;
        }
        int suma = 0;
        for(int i=0;i<stevila.size();i++){
            if(i%2==0){
                suma = suma + stevila.get(i)*3;
            }
            else{
                suma = suma + stevila.get(i)*1;
            }
        }
        int b = suma%10;
        int checkeddigit;
        if(b>0){
            checkeddigit = 10-b;
        }
        else
            checkeddigit = 0;
        return checkeddigit;
    }

    public void setKolicina(BigDecimal kol){
        this._kolicina = kol;
    }

    public String getEAN(){
        return this._EAN;
    }

    public String getName(){
        return _name;
    }
    public void setName(String _ime){
        this._name = _ime;
    }

    public BigDecimal getPrice(){
        BigDecimal cena = _price.multiply(_davcnaStopnja);
        return cena;
    }
    public void setPrice(BigDecimal _cena){
        this._price = _cena;
    }

    public String toString(){
        return (this._name + this._drzava + this._price + this._EAN + this._kolicina);
    }

}
