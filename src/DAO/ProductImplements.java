/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Category;
import data.Product;
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
 * @author Admin
 */
public class ProductImplements implements ProductDAO {

    @Override
    public boolean insertNewProduct(Product p) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call InsertNewProduct (?, ?, ?, ?, ?)}");
            cstmt.setString(1, p.getProName());
            cstmt.setInt(2, p.getCateId().getCateId());
            cstmt.setDouble(3, p.getPrice());
            cstmt.setInt(4, p.getQuantity());
            cstmt.setString(5, p.getProImage());
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean updateProduct(Product p) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call UpdateProduct (?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, p.getProName());
            cstmt.setInt(2, p.getCateId().getCateId());
            cstmt.setDouble(3, p.getPrice());
            cstmt.setInt(4, p.getQuantity());
            cstmt.setString(5, p.getProImage());
            cstmt.setInt(6, p.getProId());
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean deleteProduct(Integer proId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call DeleteProduct (?)}");
            cstmt.setInt(1, proId);
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public List<Product> listOfProduct() {
        List<Product> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call ListOfProduct}");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public boolean findProductByProName(String proName) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindProductByProName (?)}");
            cstmt.setString(1, proName);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return bl;
    }

    @Override
    public Product findProductByName(String proName) {
        Product p = null;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindProductByProName (?)}");
            cstmt.setString(1, proName);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return p;
    }

    @Override
    public List<Product> checkExistProduct(String oldProName, String newProName) {
        List<Product> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call CheckExistProduct (?, ?)}");
            cstmt.setString(1, oldProName);
            cstmt.setString(2, newProName);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public List<Product> showProductByCategory(Integer cateId) {
        List<Product> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call LoadProductByCategory (?)}");
            cstmt.setInt(1, cateId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public List<Product> searchProductByName(String proName) {
        List<Product> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        if (proName == null || proName.length() == 0) {
            proName = "%";
        } else {
            proName = "%" + proName + "%";
        }
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call SearchProductByName (?)}");
            cstmt.setString(1, proName);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public Product FindProductByProId(int proId) {
        Product p = null;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindProductByProId(?)}");
            cstmt.setInt(1, proId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setProId(rs.getInt("ProId"));
                p.setProName(rs.getString("ProName"));
                Category c = new CategoryImplements().findCategoryById(rs.getInt("CateId"));
                p.setCateId(c);
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setProImage(rs.getString("ProImage"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return p;
    }

    @Override
    public boolean updateQuantityProductClickOrder(int proId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call updateQuantityProductClickOrder(?)}");
            cstmt.setInt(1, proId);
            int check = cstmt.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean updatePlusQuantityProduct(int proId, int quantity) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call updatePlusQuantityProduct(?,?)}");
            cstmt.setInt(1, proId);
            cstmt.setInt(2, quantity);
            int check = cstmt.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean updateSubstractQuantityProduct(int proId, int quantity) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call updateSubstractQuantityProduct(?,?)}");
            cstmt.setInt(1, proId);
            cstmt.setInt(2, quantity);
            int check = cstmt.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

}
