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

        a1.setName("Banana");
        a1.setPrice(new BigDecimal("10.00"));
        a1.setKolicina(new BigDecimal("20"));
        a1.setDrzava("Makedonija");
        a1.setOddelekS(222);
        a1.setEAN();

        a2.setName("Krompir");
        a2.setPrice(new BigDecimal("144.77"));
        a2.setKolicina(new BigDecimal("30"));
        a2.setDrzava("Slovenija");
        a2.setOddelekS(100);
        a2.setEAN();

        a3.setName("Mleko");
        a3.setPrice(new BigDecimal("8.22"));
        a3.setKolicina(new BigDecimal("300"));
        a3.setDrzava("Spanija");
        a3.setOddelekS(953);
        a3.setEAN();


        a4.setName("Sir");
        a4.setPrice(new BigDecimal("10.20"));
        a4.setKolicina(new BigDecimal("1000"));
        a4.setDrzava("Makedonija");
        a4.setOddelekS(342);
        a4.setEAN();

        a5.setName("Sampon");
        a5.setPrice(new BigDecimal("2.8"));
        a5.setKolicina(new BigDecimal("245"));
        a5.setDrzava("Francija");
        a5.setOddelekS(336);
        a5.setEAN();

        a6.setName("Orbit");
        a6.setPrice(new BigDecimal("10"));
        a6.setKolicina(new BigDecimal("458"));
        a6.setDrzava("Makedonija");
        a6.setOddelekS(452);
        a6.setEAN();

        a7.setName("Sladkor");
        a7.setPrice(new BigDecimal("3"));
        a7.setKolicina(new BigDecimal("7000"));
        a7.setDrzava("Nemčija");
        a7.setOddelekS(222);
        a7.setEAN();

        a8.setName("Kava");
        a8.setPrice(new BigDecimal("3.8"));
        a8.setKolicina(new BigDecimal("458"));
        a8.setDrzava("Hrvaska");
        a8.setOddelekS(265);
        a8.setEAN();

        a9.setName("Zelje");
        a9.setPrice(new BigDecimal("1"));
        a9.setKolicina(new BigDecimal("151"));
        a9.setDrzava("ZDA");
        a9.setOddelekS(270);
        a9.setEAN();
        //a9.setEAN();

        Racun1.Artikli.add(a1);
        Racun1.Artikli.add(a2);
        Racun1.Artikli.add(a3);
        Racun1.setIzdajatelj(p1.getIme());
        Racun1.setDSP(p1.getDavcnaStevilka());
        Racun1.search("banana");


        Racun2.Artikli.add(a4);
        Racun2.Artikli.add(a5);
        Racun2.Artikli.add(a6);
        Racun2.setIzdajatelj(p2.getIme());
        Racun2.setDSP(p2.getDavcnaStevilka());
        Racun2.search("podjetje2");

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
        System.out.println(Racun1.search("banane"));
        System.out.println(Racun2.search("podjetje2"));
        System.out.println(a1.search("Mleko"));
        System.out.println(a8.search("Kava"));
        System.out.println(p1.search("podjetje1"));
        System.out.println(p2.search("podjetje3"));

//     String userJson = "{'_name':'aaa','_drzava': 'bbb','_price':123,'_EAN':231321, '_kol':111}";
//
//
//     String a = a1.toJson();
//
//     System.out.println(a+"\n");
//
//     System.out.println(a1.fromJson(userJson));
      Helper h1 = new Helper();
      h1.writer(a1);
      h1.reader();
    }
}
