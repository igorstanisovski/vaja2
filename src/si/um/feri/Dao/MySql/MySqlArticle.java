package si.um.feri.Dao.MySql;

import si.um.feri.Dao.ArticleDao;
import si.um.feri.database.DBHelper;
import si.um.feri.models.Artikel;
import si.um.feri.Util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class MySqlArticle implements ArticleDao {
    final String TABLE_NAME = "article";

    final String SQL_GET_BY_ID = "SELECT * FROM "+TABLE_NAME+" WHERE article_id = ? LIMIT 1";
    final String SQL_GET_ALL = "TODO";
    final String SQL_GET_BY_CODE = "TODO";
    final String SQL_INSERT = "TODO";
    final String SQL_UPDATE = "TODO";
    final String SQL_DELETE = "TODO";

    @Override
    public Artikel getByBarcode(String code) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_BY_CODE)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if(rs.first())
                return extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artikel getById(UUID id) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_BY_ID)) {
            ps.setBytes(1, Util.uuidToBinary(id));
            ResultSet rs = ps.executeQuery();

            if(rs.first())
                return extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Artikel> getAll() {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL)) {

            List<Artikel> articles = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                articles.add(extractFromResultSet(rs));

            return articles;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Artikel ar) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setBytes(1, Util.uuidToBinary(ar.getUuid())); // article_id BINARY(16) NOT NULL
            ps.setString(2, ar.getEAN()); // barcode VARCHAR(14) NOT NULL
            ps.setString(3, ar.getName()); // name VARCHAR(1000) NOT NULL
            ps.setBigDecimal(4, ar.getPrice()); // price DECIMAL(10,2) NOT NULL
            ps.setBigDecimal(5, ar.getVat()); // vat DECIMAL(5,2) NOT NULL
            ps.setInt(6, ar.getKolicina().intValue()); // stock INT NOT NULL
            ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis())); // created TIMESTAMP NOT NULL
            ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Artikel ar) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, ar.getEAN()); // barcode VARCHAR(14) NOT NULL
            ps.setString(2, ar.getName()); // name VARCHAR(1000) NOT NULL
            ps.setBigDecimal(3, ar.getPrice()); // price DECIMAL(10,2) NOT NULL
            ps.setBigDecimal(4, ar.getVat()); // vat DECIMAL(5,2) NOT NULL
            ps.setInt(5, ar.getKolicina().intValue()); // stock INT NOT NULL
            ps.setBytes(6, Util.uuidToBinary(ar.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Artikel ar) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setBytes(1, Util.uuidToBinary(ar.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Artikel extractFromResultSet(ResultSet rs) throws SQLException {
        UUID id = Util.binaryToUuid(rs.getBytes("article_id"));
        String barcode = rs.getString("barcode");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");
        BigDecimal vat = rs.getBigDecimal("vat");
        int stock = rs.getInt("stock");
        return new Artikel(id, name, barcode, stock, price, vat);
    }
}
