/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Area;
import data.CoffeeTable;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranq
 */
public class CoffeeTableImplements implements CoffeeTableDAO {

    @Override
    public boolean createNewCoffeeTable(CoffeeTable ct) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewCoffeeTable(?,?) }");
            cs.setString(1, ct.getTableName());
            cs.setInt(2, ct.getAreaId().getAreaId());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public CoffeeTable findTableCoffeeByName(String nameTable, int areaId) {
        CoffeeTable ct = null;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            ps = con.prepareStatement("select * from CoffeeTable where TableName = ? AND AreaId = ?");
            ps.setString(1, nameTable);
            ps.setInt(2, areaId);
            rs = ps.executeQuery();
            if (rs.next()) {
                ct = new CoffeeTable();
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                int getAreaId = rs.getInt("AreaId");
                Area a = new AreaImplements().findAreaById(getAreaId);
                ct.setAreaId(a);
                ct.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ct;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTables(int areaId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTables(?)}");
            cs.setInt(1, areaId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> checkExistCoffeeTable(String oldName, String newName, int areaId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call checkExistCoffeeTable(?,?,?)}");
            cs.setString(1, oldName);
            cs.setString(2, newName);
            cs.setInt(3, areaId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean updateCoffeeTable(CoffeeTable ct) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateCoffeeTable(?,?,?) }");
            cs.setInt(1, ct.getTableId());
            cs.setString(2, ct.getTableName());
            cs.setInt(3, ct.getAreaId().getAreaId());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean deleteCoffeeTable(int tableId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteCoffeeTable(?) }");
            cs.setInt(1, tableId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<CoffeeTable> checkExistCoffeeTableInArea(String newName, int areaId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call checkExistCoffeeTableInArea(?,?)}");
            cs.setString(1, newName);
            cs.setInt(2, areaId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int getQuantityCoffeeTableInArea(int areaId) {
        int count = 0;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getQuantityCoffeeTableInArea(?) }");
            cs.setInt(1, areaId);
            rs = cs.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public CoffeeTable getCoffeeTableByTableId(int tableId) {
        CoffeeTable ct = null;
        Connection con;
        CallableStatement ps;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            ps = con.prepareCall("{ call getCoffeeTableByTableId(?) }");
            ps.setInt(1, tableId);
            rs = ps.executeQuery();
            if (rs.next()) {
                ct = new CoffeeTable();
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                int getAreaId = rs.getInt("AreaId");
                Area a = new AreaImplements().findAreaById(getAreaId);
                ct.setAreaId(a);
                ct.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ct;
    }

    @Override
    public boolean updateStatusCoffeeTable(int status, int tableId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateStatusCoffeeTable(?,?) }");
            cs.setInt(1, status);
            cs.setInt(2, tableId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTablesByStatus(int areaId, int status) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTablesByStatus(?,?)}");
            cs.setInt(1, areaId);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getCoffeeTablesByCustomer(Integer cusId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call GetCoffeeTablesByCustomer(?)}");
            cs.setInt(1, cusId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTablesWithoutAreId() {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTablesWithoutAreId}");
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTablesWithoutAreIdByStatus(int status) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTablesWithoutAreIdByStatus(?)}");
            cs.setInt(1, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getCoffeeTableNotSelectedTable(int tableId, int areaId, int status) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getCoffeeTableNotSelectedTable(?,?,?)}");
            cs.setInt(1, tableId);
            cs.setInt(2, areaId);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getCoffeeTableWithoutCustomer(int areaId, int tableId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getCoffeeTableWithoutCustomer(?,?)}");
            cs.setInt(1, areaId);
            cs.setInt(2, tableId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTablesWithoutSelectedTable(int areaId, int tableId) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTablesWithoutSelectedTable(?,?)}");
            cs.setInt(1, areaId);
            cs.setInt(2, tableId);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<CoffeeTable> getAllCoffeeTablesByTwoStatus(int areaId, int statusOne, int statusTwo) {
        List<CoffeeTable> list = new ArrayList<>();
        CoffeeTable ct = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getAllCoffeeTablesByTwoStatus(?,?,?)}");
            cs.setInt(1, areaId);
            cs.setInt(2, statusOne);
            cs.setInt(3, statusTwo);
            rs = cs.executeQuery();
            while (rs.next()) {
                ct = new CoffeeTable();
                Area area = new AreaImplements().findAreaById(rs.getInt("AreaId"));
                ct.setAreaId(area);
                ct.setTableId(rs.getInt("TableId"));
                ct.setTableName(rs.getString("TableName"));
                ct.setStatus(rs.getInt("Status"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeTableImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
