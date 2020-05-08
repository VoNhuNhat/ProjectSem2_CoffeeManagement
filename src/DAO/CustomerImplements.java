/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Customer;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranq
 */
public class CustomerImplements implements CustomerDAO {

    @Override
    public int createNewCustomer(Customer c) {
        int bl = 0;
        Connection con;
        PreparedStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();

        try {
            cs = con.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,CURRENT_TIMESTAMP,null)", Statement.RETURN_GENERATED_KEYS);
            cs.setString(1, c.getFullname());
            cs.setString(2, c.getPhoneNumber());
            cs.setString(3, c.getEmail());
            cs.setInt(4, c.getGender());
            cs.setDate(5, new Date(c.getArriveDate().getTime()));
            cs.setTime(6, c.getArriveHour());
            cs.setInt(7, c.getStatus());

            int check = cs.executeUpdate();
            if (check > 0) {
                rs = cs.getGeneratedKeys();
                rs.next();
                bl = rs.getInt(1);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        Customer c;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getAllCustomers }");
            rs = cs.executeQuery();
            while (rs.next()) {
                c = new Customer();
                c.setCusId(rs.getInt("CusId"));
                c.setFullname(rs.getString("Fullname"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getInt("Gender"));
                c.setArriveDate(rs.getDate("ArriveDate"));
                c.setArriveHour(rs.getTime("ArriveHour"));
                c.setStatus(rs.getInt("Status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Customer getCustomerByEmailAndStatus(String email, int status) {
        Customer c = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getCustomerByEmailAndStatus(?,?) }");
            cs.setString(1, email);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setCusId(rs.getInt("CusId"));
                c.setFullname(rs.getString("Fullname"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getInt("Gender"));
                c.setArriveDate(rs.getDate("ArriveDate"));
                c.setArriveHour(rs.getTime("ArriveHour"));
                c.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public Customer getCustomerByCusId(int cusId) {
        Customer c = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getCustomerByCusId(?) }");
            cs.setInt(1, cusId);
            rs = cs.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setCusId(rs.getInt("CusId"));
                c.setFullname(rs.getString("Fullname"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getInt("Gender"));
                c.setArriveDate(rs.getDate("ArriveDate"));
                c.setArriveHour(rs.getTime("ArriveHour"));
                c.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer c = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getCustomerByEmail(?) }");
            cs.setString(1, email);
            rs = cs.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setCusId(rs.getInt("CusId"));
                c.setFullname(rs.getString("Fullname"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getInt("Gender"));
                c.setArriveDate(rs.getDate("ArriveDate"));
                c.setArriveHour(rs.getTime("ArriveHour"));
                c.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public Customer getCustomerByTableId(int tableId, int status) {
        Customer c = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getCustomerByTableId(?,?) }");
            cs.setInt(1, tableId);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setCusId(rs.getInt("CusId"));
                c.setFullname(rs.getString("Fullname"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getInt("Gender"));
                c.setArriveDate(rs.getDate("ArriveDate"));
                c.setArriveHour(rs.getTime("ArriveHour"));
                c.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public boolean updateStatusCustomer(int cusId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();

        try {
            cs = con.prepareCall("{ call updateStatusCustomer(?,?) }");
            cs.setInt(1, cusId);
            cs.setInt(2, status);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updateInfoCustomer(Customer c) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();

        try {
            cs = con.prepareCall("{ call updateInfoCustomer(?,?,?,?,?,?,?,?) }");
            cs.setString(1, c.getFullname());
            cs.setString(2, c.getPhoneNumber());
            cs.setString(3, c.getEmail());
            cs.setInt(4, c.getGender());
            cs.setDate(5, new Date(c.getArriveDate().getTime()));
            cs.setTime(6, c.getArriveHour());
            cs.setInt(7, c.getStatus());
            cs.setInt(8, c.getCusId());

            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean deleteCustomersByEmailAndStatus(String email, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteCustomersByEmailAndStatus(?, ?) }");
            cs.setString(1, email);
            cs.setInt(2, status);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

}
