/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.UserAccount;
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
 * @author tranq
 */
public class UserImplements implements UserDAO {

    @Override
    public UserAccount findUserByUsername(String username) {
        UserAccount ua = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call findUserByUsername(?) }");
            cs.setString(1, username);
            rs = cs.executeQuery();
            if (rs.next()) {
                ua = new UserAccount();
                ua.setUserId(rs.getInt("UserId"));
                ua.setUsername(rs.getString("UserName"));
                ua.setPassword(rs.getString("Password"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setImageUser(rs.getString("ImageUser"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ua;
    }

    @Override
    public boolean createNewUser(UserAccount ua) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewUser(?,?,?,?,?,?,?,?,?) }");
            cs.setString(1, ua.getFullname());
            cs.setString(2, ua.getUsername());
            cs.setString(3, ua.getPassword());
            cs.setString(4, ua.getPhoneNumber());
            cs.setString(5, ua.getEmail());
            cs.setInt(6, ua.getGender());
            cs.setString(7, ua.getAddress());
            cs.setDate(8, new Date(ua.getCreatedDate().getTime()));
            cs.setString(9, ua.getImageUser());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean findPhoneNumber(String phoneNumber) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call findPhoneNumber(?) }");
            cs.setString(1, phoneNumber);
            rs = cs.executeQuery();
            if (rs.next()) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean findEmail(String email) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call findEmail(?) }");
            cs.setString(1, email);
            rs = cs.executeQuery();
            if (rs.next()) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<UserAccount> getAllUsers() {
        List<UserAccount> list = new ArrayList<>();
        UserAccount ua;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getAllUsers }");
            rs = cs.executeQuery();
            while (rs.next()) {
                ua = new UserAccount();
                ua.setUsername(rs.getString("UserName"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(ua);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean updateUser(UserAccount ua) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateUser(?,?,?,?,?,?,?,?,?,?) }");
            cs.setInt(1, ua.getUserId());
            cs.setString(2, ua.getFullname());
            cs.setString(3, ua.getUsername());
            cs.setString(4, ua.getPassword());
            cs.setString(5, ua.getPhoneNumber());
            cs.setString(6, ua.getEmail());
            cs.setInt(7, ua.getGender());
            cs.setString(8, ua.getAddress());
            cs.setDate(9, new Date(ua.getCreatedDate().getTime()));
            cs.setString(10, ua.getImageUser());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<UserAccount> checkExistUsername(String oldUsername, String newUsername) {
        List<UserAccount> list = new ArrayList<>();
        UserAccount ua = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call checkExistUsername(?,?) }");
            cs.setString(1, oldUsername);
            cs.setString(2, newUsername);
            rs = cs.executeQuery();
            while (rs.next()) {
                ua = new UserAccount();
                ua.setUsername(rs.getString("UserName"));
                ua.setPassword(rs.getString("Password"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setImageUser(rs.getString("ImageUser"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(ua);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteUser(?) }");
            cs.setString(1, username);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<UserAccount> checkExistPhoneNumber(String oldPhoneNumber, String newPhoneNumber) {
        List<UserAccount> list = new ArrayList<>();
        UserAccount ua = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call checkExistPhoneNumber(?,?) }");
            cs.setString(1, oldPhoneNumber);
            cs.setString(2, newPhoneNumber);
            rs = cs.executeQuery();
            while (rs.next()) {
                ua = new UserAccount();
                ua.setUsername(rs.getString("UserName"));
                ua.setPassword(rs.getString("Password"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setImageUser(rs.getString("ImageUser"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(ua);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<UserAccount> checkExistEmail(String oldEmail, String newEmail) {
        List<UserAccount> list = new ArrayList<>();
        UserAccount ua = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call checkExistEmail(?,?) }");
            cs.setString(1, oldEmail);
            cs.setString(2, newEmail);
            rs = cs.executeQuery();
            while (rs.next()) {
                ua = new UserAccount();
                ua.setUsername(rs.getString("UserName"));
                ua.setPassword(rs.getString("Password"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setImageUser(rs.getString("ImageUser"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(ua);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean updatePasswordUser(int userId, String newPassword) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updatePasswordUser(?,?) }");
            cs.setInt(1, userId);
            cs.setString(2, newPassword);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public UserAccount getUserByUserId(int userId) {
        UserAccount ua = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getUserByUserId(?) }");
            cs.setInt(1, userId);
            rs = cs.executeQuery();
            if (rs.next()) {
                ua = new UserAccount();
                ua.setUserId(rs.getInt("UserId"));
                ua.setUsername(rs.getString("UserName"));
                ua.setPassword(rs.getString("Password"));
                ua.setFullname(rs.getString("Fullname"));
                ua.setPhoneNumber(rs.getString("PhoneNumber"));
                ua.setEmail(rs.getString("Email"));
                ua.setGender(rs.getInt("Gender"));
                ua.setImageUser(rs.getString("ImageUser"));
                ua.setAddress(rs.getString("Address"));
                ua.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ua;
    }
}
