package si.um.feri.Dao.MySql;

import si.um.feri.Dao.CompanyDao;
import si.um.feri.database.DBHelper;
import si.um.feri.models.Podjetje;
import si.um.feri.Util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlCompany implements CompanyDao {
    final String TABLE_NAME = "company";

    final String SQL_GET_BY_ID = "SELECT * FROM "+TABLE_NAME+" WHERE company_id = ? LIMIT 1";
    final String SQL_GET_ALL = "SELECT * FROM "+TABLE_NAME;
    final String SQL_GET_BY_NAME = "SELECT * FROM "+TABLE_NAME+" WHERE name = ? LIMIT 1";
    final String SQL_INSERT = "INSERT INTO "+TABLE_NAME+"(company_id, name,tax_number, registration_number,phone_number," +
            "taxpayer,address,created,modified)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String SQL_DELETE = "DELETE FROM"+TABLE_NAME+ " WHERE company_id = ?";
    final String SQL_UPDATE = "UPDATE"+TABLE_NAME+" SET name = ?, tax_number = ?, registration_number = ?, phone_number = ?,"+
            " taxpayer = ?, address = ? WHERE company_id = ?";

    @Override
    public Podjetje getByName(String name) {
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
    public Podjetje getById(UUID id) {
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
    public List<Podjetje> getAll() {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL)) {

            List<Podjetje> podjetja = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                podjetja.add(extractFromResultSet(rs));

            return podjetja;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Podjetje p) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setBytes(1, Util.uuidToBinary(p.getUuid()));
            ps.setString(2, p.getIme());
            ps.setString(3, Long.toString(p.getDavcnaStevilka()));
            ps.setString(4, Long.toString(p.getMaticnaStevilka()));
            ps.setString(5, p.getPhone());
            if(Long.toString(p.getDavcnaStevilka()).length() > 1){
                ps.setBoolean(6,true);
            }
            else{
                ps.setBoolean(6,false);
            }
            ps.setString(7, p.getAddress());
            ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Podjetje p) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, p.getIme()); // barcode VARCHAR(14) NOT NULL
            ps.setString(2, Long.toString(p.getDavcnaStevilka())); // name VARCHAR(1000) NOT NULL
            ps.setString(3, Long.toString(p.getMaticnaStevilka()));
            ps.setString(4, p.getPhone());
            if(Long.toString(p.getDavcnaStevilka()).length() > 1){
                ps.setBoolean(5,true);
            }
            else{
                ps.setBoolean(5,false);
            }
            ps.setString(6, p.getAddress());
            ps.setBytes(7, Util.uuidToBinary(p.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Podjetje p) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setBytes(1, Util.uuidToBinary(p.getUuid())); // article_id BINARY(16) NOT NULL

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Podjetje extractFromResultSet(ResultSet rs) throws SQLException {
        UUID id = Util.binaryToUuid(rs.getBytes("company_id"));
        String name = rs.getString("barcode");
        String davcna = rs.getString("tax_number");
        String maticna = rs.getString("registration_number");
        String phone = rs.getString("phone_number");
        boolean check = rs.getBoolean("taxpayer");
        String address = rs.getString("address");
        return new Podjetje(id, name, davcna,maticna,phone,check,address);
    }
}
