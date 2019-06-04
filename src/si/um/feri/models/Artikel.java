package si.um.feri.models;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import si.um.feri.Util.Util;
import java.util.UUID;

public class Artikel implements Searchable {
    private static UUID uuid;
    private String _name;
    private String _drzava;
    private BigDecimal _price;
    private String _EAN;
    private BigDecimal _kolicina;
    private int _ID;
    private int _oddelekS;


    public Artikel(UUID id,String name,String barcode,int stock,BigDecimal price,BigDecimal vat){
        this.uuid = id;
        this._name = name;
        this._EAN = barcode;
        this._kolicina = BigDecimal.valueOf(stock);
        this._davcnaStopnja = vat;
        this._price = price;
    }

    public static UUID getUuid(){
        if(uuid == null){
            uuid = Util.binaryToUuid(Util.generateBinrayUuid());
        }
        return uuid;
    }

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

    protected BigDecimal _davcnaStopnja=new BigDecimal("0.22");

    public BigDecimal getVat(){
        return  this._davcnaStopnja;
    }

    @Override
    public Boolean search(String s) {
        if(_name == s || _drzava==s || _price.toString() == s || _EAN == s || _kolicina.toString()==s
        || _davcnaStopnja.toString()==s)
            return true;
        else return false;

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
        if(teza.length() == 3){
            teza = "0" + teza;
        }
        else if(teza.length()==2){
            teza = "00" + teza;
        }
        else if (teza.length() == 1){
            teza = "000" + teza;
        }
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

    public Boolean checkDigit(String st){
        long a = Long.parseLong(st);
        long last = a%10;
        long suma = 0;
        a = a/10;
        int poz = st.length()-1;
        while(a > 0){
            long number = a%10;
            if(poz % 2 == 0){
                long digit = number * 1;
                suma = suma + digit;
            }
            else{
                long digit = number * 3;
                suma = suma + digit;
            }
            a = a/10;
            poz--;
        }
        long b = suma%10;
        long checkeddigit;
        if(b>0){
            checkeddigit = 10-b;
        }
        else
            checkeddigit = 0;

        if(checkeddigit == last){
            return true;
        }
        else return false;
    }

    public int makeCheckDigit(String e){
        long a = Long.parseLong(e);
        //int i = 0;
        ArrayList<Long> stevila = new ArrayList<>();
        while(a > 0){
            stevila.add(a%10);
            a = a/10;
        }
        long suma = 0;
        for(int i=0;i<stevila.size();i++){
            if(i%2==0){
                suma = suma + stevila.get(i)*3;
            }
            else{
                suma = suma + stevila.get(i)*1;
            }
        }
        long b = suma%10;
        long checkeddigit;
        if(b>0){
            checkeddigit = 10-b;
        }
        else
            checkeddigit = 0;

        int bb = (int)checkeddigit;
        return bb;
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
