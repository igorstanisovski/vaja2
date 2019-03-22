import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Racun extends Artikel implements Searchable{
    protected static int _ID = 0;
    private int _RacunID = 0;
    private String _izdajatelj;
    private long _UnikatnaStevilkaRacuna;
    private long _DavcnaStevilkaPodjetja;
    private Date _datum;
    ArrayList<Artikel> Artikli = new ArrayList<Artikel>();

    @Override
    public Boolean search(String s) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(_datum);
        if(_izdajatelj == s || Integer.toString(_RacunID)==s || Long.toString(_UnikatnaStevilkaRacuna)==s
        || Long.toString(_DavcnaStevilkaPodjetja)==s || strDate ==s)
            return true;
        else return false;
    }

    public Racun() {
        _ID++;
        this._RacunID = _ID;
        _datum = new Date();
        setUSR();
    }

    public int getKolicinaPosamezniArtiklov(){
        return Artikli.size();
    }
    public BigDecimal getSkupnaCena() {
        BigDecimal _suma = new BigDecimal("0");
        for (int i = 0; i < getKolicinaPosamezniArtiklov(); i++) {
            _suma = _suma.add(Artikli.get(i).getPrice().multiply(Artikli.get(i).getKolicina()));
        }
        return _suma;
    }
    public String getIzdajatelj(){

        return _izdajatelj;
    }

    public void  setIzdajatelj(String i){

        this._izdajatelj = i;
    }
    public long getUSR(){
        return _UnikatnaStevilkaRacuna;
    }

    public void  setUSR(){
        Random rnd = new Random();
        this._UnikatnaStevilkaRacuna = 10000000 + rnd.nextInt(90000000);
   }
    public long getDSP(){
        return _DavcnaStevilkaPodjetja;
    }

    public void setDSP(long d){
        this._DavcnaStevilkaPodjetja = d;
    }

    public Boolean JeZavezanec(){
        if(getDSP()==0){
            return false;
        }
        else return true;
    }


    public String toString(){
        String ss = new String();
        String ss1= new String();
        if(JeZavezanec()==true){
            ss1 = "Da";
        }
        else ss1 = "Ne";
        for(int i=0;i<getKolicinaPosamezniArtiklov();i++){
            ss=ss + "Artikel " + i + ": " + Artikli.get(i).getName() + "\tKolicina: " + Artikli.get(i).getKolicina() +
                    "\tCena: " + Artikli.get(i).getPrice() + "\tEAN: " + Artikli.get(i).getEAN()+
                     "Je EAN veljaven: \t" + Artikli.get(i).checkDigit(Artikli.get(i).getEAN()) + "\nDrzava: "+ Artikli.get(i).getDrzava()+"\n" ;
        }
       return "Racun id: " + _RacunID + "\nIzdajatelj:" + getIzdajatelj() + "\nJe Zavezanec: " + ss1 +
               "\nDavcna stevilka podjetja:" +  getDSP() +
               "\nDatum: " + _datum.toString() +  "\nPosamezni artikli: " + getKolicinaPosamezniArtiklov() +
               "\nArtikli:\n" + ss + "\nSkupna Cena: " + getSkupnaCena() + "\nUnikatna stevilka racuna: " + getUSR()+"\n\n\n";
    }

}
