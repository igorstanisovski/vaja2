package si.um.feri.models;

import si.um.feri.Util.Util;

import java.util.Random;
import java.util.UUID;

public class Podjetje implements Searchable {
    private UUID uuid;
    private String _ime;
    private long _davcnaStevilka;
    private long _maticnaStevilka;
    private String phone;
    private String address;
    private boolean zavezanec;

    public Podjetje(UUID id, String name, String davcna,String maticna, String phone,boolean check,String address){
        this.uuid = id;
        this._ime = name;
        this._davcnaStevilka = Long.parseLong(davcna);
        this._maticnaStevilka = Long.parseLong(maticna);
        if(davcna.length() > 1){
            zavezanec = true;
        }
        else
            zavezanec = false;
        this.phone = phone;
        this.address = address;
    }

    public void setAddress(String a){
        this.address = a;
    }
    public String getAddress(){
        return this.address;
    }

    public void setPhone(String ph){
        this.phone=ph;
    }
    public String getPhone(){
        return this.phone;
    }

    public UUID getUuid(){
        if(this.uuid == null){
            this.uuid = Util.binaryToUuid(Util.generateBinrayUuid());
        }
        return this.uuid;
    }
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
