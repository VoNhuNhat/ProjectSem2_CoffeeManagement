/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.CoffeeTable;
import data.OrderCoffee;
import data.OrderDetail;
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
public class OrderDetailImplements implements OrderDetailDAO {

    @Override
    public boolean createNewOrderDetail(OrderDetail od) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewOrderDetail(?,?,?) }");
            cs.setInt(1, od.getOrderId().getOrderId());
            cs.setInt(2, od.getTableId().getTableId());
            cs.setInt(3, od.getStatus());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public OrderDetail getOrderDetailByTableStatus(int tableId, int status) {
        OrderDetail od = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getOrderDetailByTableStatus(?,?) }");
            cs.setInt(1, tableId);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                od = new OrderDetail();
                od.setOrderDetailId(rs.getInt("OrderDetailId"));
                OrderCoffeeImplements oci = new OrderCoffeeImplements();
                OrderCoffee order = oci.getOrderByOrderId(rs.getInt("OrderId"));
                od.setOrderId(order);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                od.setTableId(coffeeTable);
                od.setStatus(rs.getInt("Status"));
                od.setCreatedDate(rs.getDate("CreatedDate"));
                od.setUpdatedDate(rs.getDate("UpdatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return od;
    }

    @Override
    public boolean updateTableIdOrderDetail(int oldTableId, int newTableId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateTableIdOrderDetail(?,?,?) }");
            cs.setInt(1, oldTableId);
            cs.setInt(2, newTableId);
            cs.setInt(3, status);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public boolean updateStatusOrderDetail(int OrderDetailId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateStatusOrderDetail(?,?) }");
            cs.setInt(1, OrderDetailId);
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

    @Override
    public boolean deleteOrderDetail(int OrderDetailId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteOrderDetail(?) }");
            cs.setInt(1, OrderDetailId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<OrderDetail> getOrderDetailByTable(int tableId) {
        List<OrderDetail> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getOrderDetailByTable(?)}");
            cs.setInt(1, tableId);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od = new OrderDetail();
                od.setOrderDetailId(rs.getInt("OrderDetailId"));
                OrderCoffeeImplements oci = new OrderCoffeeImplements();
                OrderCoffee order = oci.getOrderByOrderId(rs.getInt("OrderId"));
                od.setOrderId(order);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                od.setTableId(coffeeTable);
                od.setStatus(rs.getInt("Status"));
                od.setCreatedDate(rs.getDate("CreatedDate"));
                od.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public boolean deleteOrderDetailByOrderId(int orderId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteOrderDetailByOrderId(?) }");
            cs.setInt(1, orderId);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getOrderDetailsByOrderId(?)}");
            cs.setInt(1, orderId);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od = new OrderDetail();
                od.setOrderDetailId(rs.getInt("OrderDetailId"));
                OrderCoffeeImplements oci = new OrderCoffeeImplements();
                OrderCoffee order = oci.getOrderByOrderId(rs.getInt("OrderId"));
                od.setOrderId(order);
                CoffeeTableImplements cti = new CoffeeTableImplements();
                CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(rs.getInt("TableId"));
                od.setTableId(coffeeTable);
                od.setStatus(rs.getInt("Status"));
                od.setCreatedDate(rs.getDate("CreatedDate"));
                od.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

}
