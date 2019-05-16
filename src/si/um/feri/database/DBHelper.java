package si.um.feri.database;
import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

public class DBHelper {
    private static BasicDataSource dataSource;
    private static int counter;
    public DBHelper(){
        counter = 0;
    }
    private static BasicDataSource source;
    private static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ds.setUsername("root");
            ds.setPassword("igor123igor");

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }
    public static void testConnection() throws SQLException{
        try (BasicDataSource dataSource = DBHelper.getDataSource();
             Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM sakila.actor");)
        {
            System.out.println("The Connection Object is of Class: "+connection.getClass());
            try (ResultSet resultSet = pstmt.executeQuery();)
            {
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            }
            catch (Exception e)
            {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }

    private static BasicDataSource Invoice() {
        source = null;
        if (source == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/invoicemanagment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ds.setUsername("root");
            ds.setPassword("igor123igor");

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            source = ds;
        }
        return source;

    }

    public static void insert() throws SQLException,IOException {
        try (BasicDataSource source = DBHelper.Invoice();
             Connection conn = source.getConnection();
        ) {
            conn.setAutoCommit(false);
            try {
                //JOptionPane.showMessageDialog(null,"tuka" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                FileInputStream file = new FileInputStream(new File("D:/Fakultet/IV semestar/PPJ/Grocery_UPC_Database.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                //JOptionPane.showMessageDialog(null,"tuka1!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Row row;
                //JOptionPane.showMessageDialog(null,"tuka1!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(null,sheet.getLastRowNum() , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 1; i < sheet.getLastRowNum(); i++) {

                    row = sheet.getRow(i);
                    //double id = row.getCell(0).getNumericCellValue();
                    String barcode = row.getCell(1).getStringCellValue();
                    String name = row.getCell(4).getStringCellValue();
                    if (!checkBarcode(barcode)) {
                        //JOptionPane.showMessageDialog(null,"Barcode ni veljaven!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    }
                    //JOptionPane.showMessageDialog(null,i , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);




                    //System.out.println("The Connection Object is of Class: " + conn.getClass());
                    String SQL = "INSERT INTO Article (article_id, barcode, name, price, vat , stock, deleted,created,modified) " +
                            "VALUES(UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(SQL);
                    if (barcode.length() > 14) {
                        continue;
                    }
                    while (barcode.length() != 14) {
                        barcode = "0" + barcode;
                    }
                    double price = 0.1 + new Random().nextDouble() * (1000.0 - 0.1);
                    double stock = 0.1 + new Random().nextDouble() * (1000.0 - 0.1);
                    try {
                        pstmt.setString(1, barcode);
                        pstmt.setString(2, name);
                        pstmt.setDouble(3, price);
                        pstmt.setDouble(4, 22.0);
                        pstmt.setDouble(5, stock);
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Timestamp time = new java.sql.Timestamp(calendar.getTime().getTime());
                        pstmt.setBoolean(6, false);
                        pstmt.setTimestamp(7, time);
                        pstmt.setTimestamp(8, time);
                        pstmt.addBatch();
                        pstmt.executeBatch();
                        counter++;
                        if(counter==1000 || i==sheet.getLastRowNum()-1){
                            conn.commit();
                            counter = 0;
                        }
                        //}
                    } catch (Exception e) {
                        //conn.rollback();
                        e.printStackTrace();
                    }
                }
                //JOptionPane.showMessageDialog(null,"vnatre!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean checkBarcode(String barcode){
        long bar = Long.parseLong(barcode);
        long last = bar % 10;
        bar = bar / 10;
        long sum = 0;
        int i = 0;
        while(bar > 0){
            i++;
            long temp = bar % 10;
            if(i%2==0){
                sum += temp;
            }
            else if(i%2!=0){
                sum+=(temp*3);
            }
            bar = bar /10;
        }
        boolean check = false;
        long temp = sum % 10;
        if(temp==0){
            if(temp == last)
                check = true;
        }
        else if(temp != 0){
            long a = 10 - temp;
            if(a==last)
                check = true;
        }
    return check;
    }
    public static void test() throws SQLException{
        try (BasicDataSource source = DBHelper.Invoice();
             Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM invoicemanagment.article");)
        {
            System.out.println("The Connection Object is of Class: "+connection.getClass());
                try  (ResultSet resultSet = pstmt.executeQuery();)
            {
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            }
            catch (Exception e)
            {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }
}




