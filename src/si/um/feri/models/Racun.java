package si.um.feri.models;
import si.um.feri.models.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Racun extends Artikel implements Searchable {
    protected static int _ID = 0;
    private int _RacunID = 0;
    private String _izdajatelj;
    private long _UnikatnaStevilkaRacuna;
    private long _DavcnaStevilkaPodjetja;
    private LocalDate _datum;
    ArrayList<Artikel> Artikli = new ArrayList<Artikel>();
    private String _kupon;

    @Override
    public Boolean search(String s) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(_datum);
        if(_izdajatelj == s || Integer.toString(_RacunID)==s || Long.toString(_UnikatnaStevilkaRacuna)==s
       || Long.toString(_DavcnaStevilkaPodjetja)==s /*|| strDate ==s*/)
            return true;
        else return false;
    }

    public Racun() {
        _ID++;
        this._RacunID = _ID;
        _datum = LocalDate.now();
        setUSR();
    }

    public void setKupon(String a){
        this._kupon = a;
    }
    public String getKupon(){
        return this._kupon;
    }

    public int getKolicinaPosamezniArtiklov(){
        return Artikli.size();
    }
    public BigDecimal getSkupnaCena() {
        BigDecimal _suma = new BigDecimal("0");
        for (int i = 0; i < getKolicinaPosamezniArtiklov(); i++) {
            _suma = _suma.add(Artikli.get(i).getPrice().multiply(Artikli.get(i).getKolicina()));
        }
        String str = new String();
        str = _kupon.substring(0,2);
        BigDecimal suma1 = new BigDecimal("0");
        if(str == "10" || str =="20") {
            String datum = new String();
            datum = _kupon.substring(2, 10);
            String godina = new String();
            String mesec = new String();
            String den = new String();
            godina = datum.substring(4, 8);
            mesec = datum.substring(2, 4);
            den = datum.substring(0, 2);

            String datum1 = new String();
            datum1 = _kupon.substring(10, 18);
            //System.out.println(datum1);
            String godina1 = new String();
            String mesec1 = new String();
            String den1 = new String();
            godina1 = datum1.substring(4, 8);
            mesec1 = datum1.substring(2, 4);
            den1 = datum1.substring(0, 2);
            //SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy",Locale.ENGLISH);

            LocalDate date = LocalDate.of(Integer.parseInt(godina), Integer.parseInt(mesec), Integer.parseInt(den));
            LocalDate date1 = LocalDate.of(Integer.parseInt(godina1), Integer.parseInt(mesec1), Integer.parseInt(den1));

            //Calendar c = Calendar.getInstance();
            //c.set(Integer.parseInt(godina), Integer.parseInt(mesec), Integer.parseInt(den));
            //Date date = c.getTime();
            //Calendar c1 = Calendar.getInstance();
            //c1.set(Integer.parseInt(godina1), Integer.parseInt(mesec1), Integer.parseInt(den1));
            //Date date1 = c1.getTime();
            //Date date = new GregorianCalendar(Integer.parseInt(godina), Integer.parseInt(mesec), Integer.parseInt(den)).getTime();
            //Date date1 = new GregorianCalendar(Integer.parseInt(godina1), Integer.parseInt(mesec1), Integer.parseInt(den1)).getTime();
            BigDecimal kupon1 = new BigDecimal("0");

            if (this._datum.isAfter(date1) || this._datum.isBefore(date)) {
                suma1 = new BigDecimal("0");
            } else {
                String sh = new String();
                sh = _kupon.substring(0, 2);
                kupon1 = new BigDecimal(sh);
                suma1 = _suma.divide(kupon1);
            }
        }
        return _suma.subtract(suma1);
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
                     "Je EAN veljaven: \t" + Artikli.get(i).checkDigit(Artikli.get(i).getEAN()) + "\nDrzava: "+ Artikli.get(i).getDrzava()+
                    "\n" ;
        }
       return "Racun id: " + _RacunID + "\nIzdajatelj:" + getIzdajatelj() + "\nJe Zavezanec: " + ss1 +
               "\nDavcna stevilka podjetja:" +  getDSP() +
               "\nDatum: " + _datum.toString() +  "\nPosamezni artikli: " + getKolicinaPosamezniArtiklov() +
               "\nArtikli:\n" + ss + "\nSkupna Cena: " + getSkupnaCena() + "\nUnikatna stevilka racuna: " + getUSR()+
                "\nKupon: " + getKupon() + "\n\n\n";
    }

}
