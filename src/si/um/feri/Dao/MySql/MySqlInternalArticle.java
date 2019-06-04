package si.um.feri.Dao.MySql;

import si.um.feri.Dao.InternalArticleDao;
import si.um.feri.database.DBHelper;
import si.um.feri.models.InternalArticle;
import si.um.feri.Util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlInternalArticle implements InternalArticleDao {
    final String TABLE_NAME = "InternalArticle";

    final String SQL_GET_BY_ID = "SELECT * FROM "+TABLE_NAME+" WHERE internal_article_id = ? LIMIT 1";
    final String SQL_GET_ALL = "SELECT * FROM "+TABLE_NAME;
    final String SQL_GET_BY_NAME = "SELECT * FROM "+TABLE_NAME+" WHERE name = ? LIMIT 1";
    final String SQL_INSERT = "INSERT INTO "+TABLE_NAME+"(internal_article_id,internal_id, name, price, vat , stock, created,modified)"+
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    final String SQL_UPDATE = "DELETE FROM"+TABLE_NAME+ " WHERE internal_article_id = ?";
    final String SQL_DELETE = "UPDATE"+TABLE_NAME+" SET internal_id = ?, name = ?, price = ?, vat = ?, stock = ?  WHERE internal_article_id = ?";





    @Override
    public InternalArticle getByName(String name) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if(rs.first())
                return extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InternalArticle getById(UUID id) {
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
    public List<InternalArticle> getAll() {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL)) {

            List<InternalArticle> articles = new ArrayList<>();
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
    public boolean insert(InternalArticle m) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setBytes(1, Util.uuidToBinary(m.getUuid())); // article_id BINARY(16) NOT NULL
            ps.setString(2, m.getID()); // barcode VARCHAR(14) NOT NULL
            ps.setString(3, m.getName()); // name VARCHAR(1000) NOT NULL
            ps.setBigDecimal(4, m.getPrice()); // price DECIMAL(10,2) NOT NULL
            ps.setBigDecimal(5, m.getVat()); // vat DECIMAL(5,2) NOT NULL
            ps.setInt(6, m.getStock()); // stock INT NOT NULL
            ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis())); // created TIMESTAMP NOT NULL
            ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(InternalArticle m) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {


            ps.setString(1, m.getID()); // barcode VARCHAR(14) NOT NULL
            ps.setString(2, m.getName()); // name VARCHAR(1000) NOT NULL
            ps.setBigDecimal(3, m.getPrice()); // price DECIMAL(10,2) NOT NULL
            ps.setBigDecimal(4, m.getVat()); // vat DECIMAL(5,2) NOT NULL
            ps.setInt(5, m.getStock()); // stock INT NOT NULL
            ps.setBytes(6, Util.uuidToBinary(m.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(InternalArticle m) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setBytes(1, Util.uuidToBinary(m.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public InternalArticle extractFromResultSet(ResultSet rs) throws SQLException {
        UUID uuid = Util.binaryToUuid(rs.getBytes("internal_article_id"));
        String id = rs.getString("article_id");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");
        BigDecimal vat = rs.getBigDecimal("vat");
        int stock = rs.getInt("stock");
        return new InternalArticle(uuid,id ,name, price, vat, stock);
    }
}
