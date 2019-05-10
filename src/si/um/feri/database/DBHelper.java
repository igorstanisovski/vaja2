package si.um.feri.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
public class DBHelper {
    private static BasicDataSource dataSource;
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
}



