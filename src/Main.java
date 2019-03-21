import java.math.BigDecimal;
import java.util.*;
public class Main {

    public static void main(String[] args) {
       ArrayList<Racun> Seznam = new ArrayList<>();
       ArrayList<Podjetje> SeznamPodjetja = new ArrayList<>();

        Artikel a1 = new Artikel();
        Artikel a2 = new Artikel();
        Artikel a3 = new Artikel();
        Artikel a4 = new Artikel();
        Artikel a5 = new Artikel();
        Artikel a6 = new Artikel();
        Artikel a7 = new Artikel();
        Artikel a8 = new Artikel();
        Artikel a9 = new Artikel();

        Racun Racun1 = new Racun();
        Racun Racun2 = new Racun();
        Racun Racun3 = new Racun();

        Podjetje p1 = new Podjetje();
        Podjetje p2 = new Podjetje();
        Podjetje p3 = new Podjetje();

        p1.setIme("podjetje1");
        p1.setDavcnaStevilka(1234567);

        p2.setIme("podjetje2");

        p3.setIme("podjetje3");
        p3.setDavcnaStevilka(12321367);

        a1.setName("abc");
        a1.setPrice(new BigDecimal("10.00"));
        a1.setKolicina(new BigDecimal("2"));
        a1.setDrzava("Makedonija");

        a2.setName("ab");
        a2.setPrice(new BigDecimal("144.77"));
        a2.setKolicina(new BigDecimal("3.4"));
        a2.setDrzava("Slovenija");

        a3.setName("abcd");
        a3.setPrice(new BigDecimal("8.22"));
        a3.setKolicina(new BigDecimal("3"));
        a3.setDrzava("Spanija");


        a4.setName("abca");
        a4.setPrice(new BigDecimal("10.20"));
        a4.setKolicina(new BigDecimal("10"));
        a4.setDrzava("Makedonija");

        a5.setName("aaaa");
        a5.setPrice(new BigDecimal("2.8"));
        a5.setKolicina(new BigDecimal("10"));
        a5.setDrzava("Francija");

        a6.setName("abdsaca");
        a6.setPrice(new BigDecimal("10"));
        a6.setKolicina(new BigDecimal("5.1"));
        a6.setDrzava("Makedonija");

        a7.setName("cccc");
        a7.setPrice(new BigDecimal("3"));
        a7.setKolicina(new BigDecimal("3"));
        a7.setDrzava("Nemčija");

        a8.setName("adsadbca");
        a8.setPrice(new BigDecimal("3.8"));
        a8.setKolicina(new BigDecimal("3.3"));
        a8.setDrzava("Hrvaska");

        a9.setName("abca");
        a9.setPrice(new BigDecimal("1"));
        a9.setKolicina(new BigDecimal("20"));
        a9.setDrzava("ZDA");

        Racun1.Artikli.add(a1);
        Racun1.Artikli.add(a2);
        Racun1.Artikli.add(a3);
        Racun1.setIzdajatelj(p1.getIme());
        Racun1.setDSP(p1.getDavcnaStevilka());

        Racun2.Artikli.add(a4);
        Racun2.Artikli.add(a5);
        Racun2.Artikli.add(a6);
        Racun2.setIzdajatelj(p2.getIme());
        Racun2.setDSP(p2.getDavcnaStevilka());

        Racun3.Artikli.add(a7);
        Racun3.Artikli.add(a8);
        Racun3.Artikli.add(a9);
        Racun3.setIzdajatelj(p3.getIme());
        Racun3.setDSP(p3.getDavcnaStevilka());

        Seznam.add(Racun1);
        Seznam.add(Racun2);
        Seznam.add(Racun3);

        SeznamPodjetja.add(p1);
        SeznamPodjetja.add(p2);
        SeznamPodjetja.add(p3);

        for(int i=0;i<Seznam.size();i++) {
            System.out.println(Seznam.get(i).toString());
        }

        for(int i =0;i<SeznamPodjetja.size();i++){
         System.out.println(SeznamPodjetja.get(i).toString());
        }



    }
}
