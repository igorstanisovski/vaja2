package si.um.feri.models;

import java.util.Random;

public class Podjetje implements Searchable {
    private String _ime;
    private long _davcnaStevilka;
    private long _maticnaStevilka;

    @Override
    public Boolean search(String s) {
        if(_ime == s || Long.toString(_davcnaStevilka) == s || Long.toString(_maticnaStevilka)==s)
            return true;
            else return false;
    }

    public Podjetje(){
        setMaticnaStevilka();
    }

    public String getIme(){
        return _ime;
    }
    public void setIme(String ime){
        _ime = ime;
    }

    public long getDavcnaStevilka(){
        return _davcnaStevilka;
    }
    public void setDavcnaStevilka(long a){
        _davcnaStevilka = a;
    }

    public long getMaticnaStevilka(){
        return _maticnaStevilka;
    }
    public void setMaticnaStevilka() {
        Random rnd = new Random();
        this._maticnaStevilka = 10000000 + rnd.nextInt(90000000);
    }


    public String toString(){
        return "Ime podjetja: " + getIme() + "\nDavcna Stevilka: " + getDavcnaStevilka() + "\nMaticna Stevilka: " + getMaticnaStevilka() +
                "\n";
    }
}
