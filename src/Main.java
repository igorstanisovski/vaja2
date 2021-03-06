import si.um.feri.Dao.MySql.MySqlCompany;
import si.um.feri.Dao.MySql.MySqlInternalArticle;
import si.um.feri.models.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)throws SQLException, IOException {
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
        Podjetje p4 = new Podjetje();
        Podjetje p5 = new Podjetje();

        p1.setIme("podjetje1");
        p1.setAddress("Kamniska ulica 2");
        p1.setMaticnaStevilka();
        p1.setDavcnaStevilka(1234567);
        p1.setPhone("070222222");
        p1.getUuid();

        p2.setIme("podjetje2");
        p2.setAddress("Kamniska ulica 4");
        p2.setMaticnaStevilka();
        p2.setPhone("070222333");
        p2.getUuid();

        p3.setIme("podjetje3");
        p3.setDavcnaStevilka(12321367);
        p3.setAddress("Kamniska ulica 10");
        p3.setMaticnaStevilka();
        p3.setPhone("070222322");
        p3.getUuid();

        p4.setIme("podjetje4");
        p1.setDavcnaStevilka(21233213);
        p4.setAddress("Trzaska 5");
        p4.setMaticnaStevilka();
        p4.setPhone("030234234");
        p4.getUuid();

        p5.setIme("podjetje5");
        p5.setDavcnaStevilka(132123367);
        p5.setAddress("Kamniska ulica 110");
        p5.setMaticnaStevilka();
        p5.setPhone("070222233");
        p5.getUuid();


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

//        Racun1.Artikli.add(a1);
//        Racun1.Artikli.add(a2);
//        Racun1.Artikli.add(a3);
        Racun1.setIzdajatelj(p1.getIme());
        Racun1.setDSP(p1.getDavcnaStevilka());
        Racun1.search("banana");
        Racun1.setKupon("100810199820101998");

//        Racun2.Artikli.add(a4);
//        Racun2.Artikli.add(a5);
//        Racun2.Artikli.add(a6);
        Racun2.setIzdajatelj(p2.getIme());
        Racun2.setDSP(p2.getDavcnaStevilka());
        Racun2.search("podjetje2");
        Racun2.setKupon("100401201905012019");
//        Racun3.Artikli.add(a7);
//        Racun3.Artikli.add(a8);
//        Racun3.Artikli.add(a9);
        Racun3.setIzdajatelj(p3.getIme());
        Racun3.setDSP(p3.getDavcnaStevilka());
        Racun3.setKupon("200401201905012019");

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

     String userJson = "{'_name':'aaa','_drzava': 'bbb','_price':123,'_EAN':231321, '_kol':111}";

     //String a = a1.toJson();

     //System.out.println(a+"\n");

     //System.out.println(a1.fromJson(userJson));
     //Helper.writer(a1);
     //Helper.reader();
     String fileA = new String("D:\\Artikel.json");
     Artikli A = new Artikli();
//     A.Artikli.add(a1);
//     A.Artikli.add(a2);
     String toSend =  A.toJson();

     Helper.writer(toSend,fileA);
     String from = Helper.reader(fileA);
     System.out.println(from);

     A.fromJson(from);


//     DBHelper.testConnection();
//     DBHelper.insertFromCsv();
//     DBHelper.test();
     MySqlCompany CompanyInput = new MySqlCompany();

     CompanyInput.insert(p1);
     CompanyInput.insert(p2);
     CompanyInput.insert(p3);
     CompanyInput.insert(p4);
     CompanyInput.insert(p5);

     InternalArticle i1 = new InternalArticle();
     InternalArticle i2 = new InternalArticle();
     InternalArticle i3 = new InternalArticle();
     InternalArticle i4 = new InternalArticle();
     InternalArticle i5 = new InternalArticle();
     i1.getUuid();
     i1.setID("2");
     i1.setName("interniartikel");
     i1.setPrice(new BigDecimal("123"));
     i1.setStock(100);
     i1.setVat(new BigDecimal("0.22"));

     i2.getUuid();
     i2.setID("2");
     i2.setName("interniartikel2");
     i2.setPrice(new BigDecimal("19"));
     i2.setStock(100);
     i2.setVat(new BigDecimal("0.22"));

     i3.getUuid();
     i3.setID("3");
     i3.setName("interniartikel3");
     i3.setPrice(new BigDecimal("10"));
     i3.setStock(10);
     i3.setVat(new BigDecimal("0.22"));

     i4.getUuid();
     i4.setID("4");
     i4.setName("interniartikel4");
     i4.setPrice(new BigDecimal("10"));
     i4.setStock(10);
     i4.setVat(new BigDecimal("0.22"));

     i5.getUuid();
     i5.setID("5");
     i5.setName("interniartikel5");
     i5.setPrice(new BigDecimal("10"));
     i5.setStock(10);
     i5.setVat(new BigDecimal("0.22"));
     MySqlInternalArticle InternalArticleInput = new MySqlInternalArticle();
     InternalArticleInput.insert(i1);
     InternalArticleInput.insert(i2);
     InternalArticleInput.insert(i3);
     InternalArticleInput.insert(i4);
     InternalArticleInput.insert(i5);


    }
}
