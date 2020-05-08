/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.CoffeeTable;
import data.Product;
import data.TableProduct;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
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
public class TableProductImplements implements TableProductDAO {

    @Override
    public boolean createNewTableProduct(TableProduct tp) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewTableProduct(?,?,?) }");
            cs.setInt(1, tp.getProId().getProId());
            cs.setInt(2, tp.getTableId().getTableId());
            cs.setInt(3, tp.getQuantityOrder());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public TableProduct checkExistTableProduct(int proId, int tableId, int status) {
        TableProduct tp = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call checkExistTableProduct(?,?,?) }");
            cs.setInt(1, proId);
            cs.setInt(2, tableId);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                tp = new TableProduct();
                tp.setTblProId(rs.getInt("TblProId"));
                ProductImplements pi = new ProductImplements();
                Product findProduct = pi.FindProductByProId(rs.getInt("ProId"));
                tp.setProId(findProduct);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                tp.setTableId(coffeeTable);
                tp.setQuantityOrder(rs.getInt("QuantityOrder"));
                tp.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tp;
    }

    @Override
    public boolean updateQuantityTableProduct(int tblProId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateQuantityTableProduct(?) }");
            cs.setInt(1, tblProId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<TableProduct> getTableProductByTableStatus(int tableId, int status) {
        List<TableProduct> list = new ArrayList<>();
        TableProduct tp = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getTableProductByTableStatus(?,?) }");
            cs.setInt(1, tableId);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                tp = new TableProduct();
                tp.setTblProId(rs.getInt("TblProId"));
                ProductImplements pi = new ProductImplements();
                Product findProduct = pi.FindProductByProId(rs.getInt("ProId"));
                tp.setProId(findProduct);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                tp.setTableId(coffeeTable);
                tp.setQuantityOrder(rs.getInt("QuantityOrder"));
                tp.setStatus(rs.getInt("Status"));
                list.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean updateQuantityTableProductEmpty(int tblProId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateQuantityTableProductEmpty(?) }");
            cs.setInt(1, tblProId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updateQuantityTableProductClickTable(int tblProId, int quantity) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateQuantityTableProductClickTable(?,?) }");
            cs.setInt(1, tblProId);
            cs.setInt(2, quantity);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean deleteTableProduct(int tblProId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteTableProduct(?) }");
            cs.setInt(1, tblProId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updatePlusQuantityOrderTableProduct(int tblProId, int quantity) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updatePlusQuantityOrderTableProduct(?,?) }");
            cs.setInt(1, tblProId);
            cs.setInt(2, quantity);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updateSubstractQuantityOrderTableProduct(int tblProId, int quantity) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateSubstractQuantityOrderTableProduct(?,?) }");
            cs.setInt(1, tblProId);
            cs.setInt(2, quantity);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean createNewTableProductWithQuantity(TableProduct tp) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewTableProductWithQuantity(?,?,?) }");
            cs.setInt(1, tp.getProId().getProId());
            cs.setInt(2, tp.getTableId().getTableId());
            cs.setInt(3, tp.getQuantityOrder());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updateTableIdTableProduct(int oldTableId, int newTableId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateTableIdTableProduct(?,?) }");
            cs.setInt(1, oldTableId);
            cs.setInt(2, newTableId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<TableProduct> checkProductExistInTableProduct(int proId, int newTableId) {
        Connection con;
        List<TableProduct> list = new ArrayList<>();
        TableProduct tp = null;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        ResultSet rs;
        try {
            cs = con.prepareCall("{ call checkProductExistInTableProduct(?,?) }");
            cs.setInt(1, proId);
            cs.setInt(2, newTableId);
            rs = cs.executeQuery();
            while (rs.next()) {
                tp = new TableProduct();
                tp.setTblProId(rs.getInt("TblProId"));
                ProductImplements pi = new ProductImplements();
                Product findProduct = pi.FindProductByProId(rs.getInt("ProId"));
                tp.setProId(findProduct);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                tp.setTableId(coffeeTable);
                tp.setQuantityOrder(rs.getInt("QuantityOrder"));
                tp.setStatus(rs.getInt("Status"));
                list.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean updateStatusTableProduct(int tblProId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateStatusTableProduct(?,?) }");
            cs.setInt(1, tblProId);
            cs.setInt(2, status);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public double getSumMoneyTableProductByCustomer(int cusId) {
        double sumMoney = 0;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getSumMoneyTableProductByCustomer(?) }");
            cs.setInt(1, cusId);
            rs = cs.executeQuery();
            if (rs.next()) {
                sumMoney = rs.getDouble("sumMoney");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sumMoney;

    }

    @Override
    public List<TableProduct> getTableProductByOrderId(int orderId) {
        Connection con;
        List<TableProduct> list = new ArrayList<>();
        TableProduct tp = null;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        ResultSet rs;
        try {
            cs = con.prepareCall("{ call getTableProductByOrderId(?) }");
            cs.setInt(1, orderId);
            rs = cs.executeQuery();
            while (rs.next()) {
                tp = new TableProduct();
                tp.setTblProId(rs.getInt("TblProId"));
                ProductImplements pi = new ProductImplements();
                Product findProduct = pi.FindProductByProId(rs.getInt("ProId"));
                tp.setProId(findProduct);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                tp.setTableId(coffeeTable);
                tp.setQuantityOrder(rs.getInt("QuantityOrder"));
                tp.setStatus(rs.getInt("Status"));
                list.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<TableProduct> getTableProductByProduct(int proId) {
        Connection con;
        List<TableProduct> list = new ArrayList<>();
        TableProduct tp = null;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        ResultSet rs;
        try {
            cs = con.prepareCall("{ call getTableProductByProduct(?) }");
            cs.setInt(1, proId);
            rs = cs.executeQuery();
            while (rs.next()) {
                tp = new TableProduct();
                tp.setTblProId(rs.getInt("TblProId"));
                ProductImplements pi = new ProductImplements();
                Product findProduct = pi.FindProductByProId(rs.getInt("ProId"));
                tp.setProId(findProduct);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                tp.setTableId(coffeeTable);
                tp.setQuantityOrder(rs.getInt("QuantityOrder"));
                tp.setStatus(rs.getInt("Status"));
                list.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
