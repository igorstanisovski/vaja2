import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Random;



public class Artikel implements Searchable{
    private String _name;
    private String _drzava;
    private BigDecimal _price;
    private long _EAN;
    private BigDecimal _kolicina;

    public void setDrzava(String _drzava) {
        this._drzava = _drzava;
    }

    public String getDrzava() {
        return _drzava;
    }



    protected final BigDecimal _davcnaStopnja=new BigDecimal("0.22");

    @Override
    public Boolean search(String s) {
        if(_name == s || _drzava==s || _price.toString() == s || Long.toString(_EAN) == s || _kolicina.toString()==s
        || _davcnaStopnja.toString()==s)
            return true;
        else return false;

    }

    public Artikel(){
        Random rnd = new Random();
        this._EAN = 10000000 + rnd.nextInt(90000000);
    }


    public BigDecimal getKolicina(){
        return _kolicina;
    }

    public Boolean checkDigit(long a){
        long last = a%10;
        long suma = 0;
        a = a/10;
        int poz = 7;
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

    public void setKolicina(BigDecimal kol){
        this._kolicina = kol;
    }

    public long getEAN(){
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

}
