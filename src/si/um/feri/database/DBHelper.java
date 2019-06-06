package si.um.feri.database;
import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

public class DBHelper {
    private static BasicDataSource dataSource;
    private static int counter;

    public DBHelper() {
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

    public static Connection getConnection() throws SQLException{
        BasicDataSource a = Invoice();
        return a.getConnection();
    }

    public static void testConnection() throws SQLException {
        try (BasicDataSource dataSource = DBHelper.getDataSource();
             Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM sakila.actor");) {
            System.out.println("The Connection Object is of Class: " + connection.getClass());
            try (ResultSet resultSet = pstmt.executeQuery();) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            } catch (Exception e) {
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

    public static void insertFromExcel() throws SQLException, IOException {
        try (BasicDataSource source = DBHelper.Invoice();
             Connection conn = source.getConnection();
        ) {
            conn.setAutoCommit(false);
            try {
                FileInputStream file = new FileInputStream(new File("D:/Fakultet/IV semestar/PPJ/Grocery_UPC_Database.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Row row;
                for (int i = 1; i < sheet.getLastRowNum(); i++) {
                    row = sheet.getRow(i);
                    String barcode = row.getCell(1).getStringCellValue();
                    String name = row.getCell(4).getStringCellValue();
                    if (!checkBarcode(barcode)) {
                        //JOptionPane.showMessageDialog(null,"Barcode ni veljaven!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Barcode ni veljaven! i:" + i);
                        continue;
                    }

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
                        if (counter == 1000 || i == sheet.getLastRowNum() - 1) {
                            conn.commit();
                            counter = 0;
                        }
                        //}
                    } catch (Exception e) {
                        conn.rollback();
                        e.printStackTrace();
                    }
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkBarcode(String barcode) {
        long bar = Long.parseLong(barcode);
        long last = bar % 10;
        bar = bar / 10;
        long sum = 0;
        int i = 0;
        while (bar > 0) {
            i++;
            long temp = bar % 10;
            if (i % 2 == 0) {
                sum += temp;
            } else if (i % 2 != 0) {
                sum += (temp * 3);
            }
            bar = bar / 10;
        }
        boolean check = false;
        long temp = sum % 10;
        if (temp == 0) {
            if (temp == last)
                check = true;
        } else if (temp != 0) {
            long a = 10 - temp;
            if (a == last)
                check = true;
        }
        return check;
    }

    public static void test() throws SQLException {
        try (BasicDataSource source = DBHelper.Invoice();
             Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM invoicemanagment.article");) {
            System.out.println("The Connection Object is of Class: " + connection.getClass());
            try (ResultSet resultSet = pstmt.executeQuery();) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }

    public static void insertFromCsv() throws SQLException, IOException {
        try (BasicDataSource source = DBHelper.Invoice();
             Connection conn = source.getConnection();
        ) {
            conn.setAutoCommit(false);
            //Path pathToFile = Paths.get("D:/Fakultet/IV semestar/PPJ/en.openfoodfacts.org.products.csv");
            try {
                BufferedReader br = new BufferedReader(new FileReader("D:/Fakultet/IV semestar/PPJ/en.openfoodfacts.org.products.csv"));
                String line = "";
                br.readLine();
                int i = 0;
                while ((line = br.readLine()) != null) {
                    if (line != null) {
                        String[] array = line.split("\t");
                        //JOptionPane.showMessageDialog(null,array.length, "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                        String SQL = "INSERT INTO Article (article_id, barcode, name, price, vat , stock, deleted,created,modified) " +
                                "VALUES(UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(SQL);
                        String barcode = array[0];
                        //JOptionPane.showMessageDialog(null,barcode, "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                        String name = array[7];
                        Timestamp t = new Timestamp(System.currentTimeMillis());

                        //JOptionPane.showMessageDialog(null,d , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                        if (barcode.length() > 14) {
                            continue;
                        }
                        if (!checkBarcode(barcode)) {
                            //JOptionPane.showMessageDialog(null,"Barcode ni veljaven!" , "InfoBox: ALERT", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Barcode ni veljaven!");
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
                            pstmt.setBoolean(6, false);
                            pstmt.setTimestamp(7, t);
                            pstmt.setTimestamp(8, t);
                            pstmt.addBatch();
                            pstmt.executeBatch();
                            counter++;
                            if (counter == 1000) {
                                conn.commit();
                                counter = 0;
                            }
                            //}
                        } catch (Exception e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                    }
                }
                conn.commit();
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




