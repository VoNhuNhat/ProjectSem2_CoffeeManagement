/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Category;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
public class CategoryImplements implements CategoryDAO {

    @Override
    public boolean insertNewCategory(Category c) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call InsertCategory (?)}");
            cstmt.setString(1, c.getCateName());
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean updateCategory(Category c) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call UpdateCategory (?, ?)}");
            cstmt.setString(1, c.getCateName());
            cstmt.setInt(2, c.getCateId());
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public boolean deleteCategoryAndProduct(Integer cateId, String cateName) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call DeleteCategoryAndProduct (?, ?)}");
            cstmt.setInt(1, cateId);
            cstmt.setString(2, cateName);
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

    @Override
    public List<Category> listOfCategory() {
        List<Category> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call ListOfCategory}");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCateId(rs.getInt("CateId"));
                c.setCateName(rs.getString("CateName"));
                c.setCreatedDate(rs.getDate("CreatedDate"));
                c.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public Category findCategoryById(Integer cateId) {
        Category c = null;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindCategoryById (?)}");
            cstmt.setInt(1, cateId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setCateId(rs.getInt("CateId"));
                c.setCateName(rs.getString("CateName"));
                c.setCreatedDate(rs.getDate("CreatedDate"));
                c.setUpdatedDate(rs.getDate("UpdatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return c;
    }

    @Override
    public Category findCategoryByName(String cateName) {
        Category c = null;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindCategoryByCateName (?)}");
            cstmt.setString(1, cateName);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                c = new Category();
                c.setCateId(rs.getInt("CateId"));
                c.setCateName(rs.getString("CateName"));
                c.setCreatedDate(rs.getDate("CreatedDate"));
                c.setUpdatedDate(rs.getDate("UpdatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return c;
    }

    @Override
    public List<Category> checkExistCategory(String oldCateName, String newCateName) {
        List<Category> list = new ArrayList<>();
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call CheckExistCategory (?, ?)}");
            cstmt.setString(1, oldCateName);
            cstmt.setString(2, newCateName);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCateId(rs.getInt("CateId"));
                c.setCateName(rs.getString("CateName"));
                c.setCreatedDate(rs.getDate("CreatedDate"));
                c.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return list;
    }

    @Override
    public boolean checkCategoryName(String cateName) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call FindCategoryByCateName (?)}");
            cstmt.setString(1, cateName);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, rs);
        }
        return bl;
    }

    @Override
    public boolean deleteCategory(Integer cateId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt = null;
        con = ConnectDB.openConnection();
        try {
            cstmt = con.prepareCall("{call DeleteCategory (?)}");
            cstmt.setInt(1, cateId);
            int i = cstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cstmt, null);
        }
        return bl;
    }

}
