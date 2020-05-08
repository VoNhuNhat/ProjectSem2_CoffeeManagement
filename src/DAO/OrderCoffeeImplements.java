/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Customer;
import data.OrderCoffee;
import data.UserAccount;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranq
 */
public class OrderCoffeeImplements implements OrderCoffeeDAO {

    @Override
    public boolean createNewOrder(OrderCoffee oc) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewOrder(?,?) }");
            cs.setInt(1, oc.getUserId().getUserId());
            cs.setInt(2, oc.getCusId().getCusId());
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
    public OrderCoffee getOrderByCusId(int cusId, int status) {
        OrderCoffee oc = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getOrderByCusId(?,?) }");
            cs.setInt(1, cusId);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                int userId = rs.getInt("UserId");
                UserAccount user = ui.getUserByUserId(userId);
                oc.setUserId(user);
                int customerId = rs.getInt("CusId");
                CustomerImplements ci = new CustomerImplements();
                Customer customer = ci.getCustomerByCusId(customerId);
                oc.setCusId(customer);
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oc;
    }

    @Override
    public int createNewOrderWithoutCustomerId(int userId) {
        int bl = 0;
        Connection con;
        PreparedStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareStatement("insert into OrderCoffee values(?,null,null,0,0,CONVERT(date,CURRENT_TIMESTAMP,101),NULL)", Statement.RETURN_GENERATED_KEYS);
            cs.setInt(1, userId);
            int check = cs.executeUpdate();
            if (check > 0) {
                rs = cs.getGeneratedKeys();
                rs.next();
                bl = rs.getInt(1);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public OrderCoffee getOrderByOrderId(int orderId) {
        OrderCoffee oc = null;
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getOrderByOrderId(?) }");
            cs.setInt(1, orderId);
            rs = cs.executeQuery();
            if (rs.next()) {
                oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                int userId = rs.getInt("UserId");
                UserAccount user = ui.getUserByUserId(userId);
                oc.setUserId(user);
                int customerId = rs.getInt("CusId");
                if (customerId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(customerId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderCoffeeImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oc;
    }

    @Override
    public boolean updateStatusOrderCoffee(int orderId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateStatusOrderCoffee(?,?) }");
            cs.setInt(1, orderId);
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
    public boolean deleteOrderCoffee(int orderId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteOrderCoffee(?) }");
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
    public boolean updateTotalMoneyOrderCoffee(int orderId, double totalMoney) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateTotalMoneyOrderCoffee(?,?) }");
            cs.setInt(1, orderId);
            cs.setDouble(2, totalMoney);
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
    public boolean updateUserCashierOrderCoffee(int orderId, int status) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateUserCashierOrderCoffee(?,?) }");
            cs.setInt(1, orderId);
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
    public List<OrderCoffee> getOrderCoffeeByDate(Date date, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getOrderCoffeeByDate(?,?)}");
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            cs.setDate(1, sDate);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> getOrderCoffeeBetweenDate(Date startDate, Date endDate, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getOrderCoffeeBetweenDate(?,?,?)}");
            java.sql.Date sDate = new java.sql.Date(startDate.getTime());
            java.sql.Date eDate = new java.sql.Date(endDate.getTime());
            cs.setDate(1, sDate);
            cs.setDate(2, eDate);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByUserAccount(String fullName, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByUserAccount(?,?)}");
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByCustomer(String fullName, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByCustomer(?,?)}");
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setInt(2, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByUserAccountDate(String fullName, Date date, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByUserAccountDate(?,?,?)}");
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setDate(2, sDate);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByCustomerDate(String fullName, Date date, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByCustomerDate(?,?,?)}");
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setDate(2, sDate);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByUserAccountBetweenDate(String fullName, Date startDate, Date endDate, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByUserAccountBetweenDate(?,?,?,?)}");
            java.sql.Date sDate = new java.sql.Date(startDate.getTime());
            java.sql.Date eDate = new java.sql.Date(endDate.getTime());
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setDate(2, sDate);
            cs.setDate(3, eDate);
            cs.setInt(4, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public List<OrderCoffee> searchOrderCoffeeByCustomerBetweenDate(String fullName, Date startDate, Date endDate, int status) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call searchOrderCoffeeByCustomerBetweenDate(?,?,?,?)}");
            java.sql.Date sDate = new java.sql.Date(startDate.getTime());
            java.sql.Date eDate = new java.sql.Date(endDate.getTime());
            String formatFullname = "";
            if (fullName.length() > 0) {
                formatFullname = "%" + fullName + "%";
            } else {
                formatFullname = "%";
            }
            cs.setString(1, formatFullname);
            cs.setDate(2, sDate);
            cs.setDate(3, eDate);
            cs.setInt(4, status);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }

    @Override
    public Double getSumTotalMoneyOrderByMonthAndYear(int month, int year, int status) {
        Double money = 0.0;
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getSumTotalMoneyOrderByMonthAndYear(?,?,?)}");
            cs.setInt(1, month);
            cs.setInt(2, year);
            cs.setInt(3, status);
            rs = cs.executeQuery();
            if (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                money = rs.getDouble("sumMoney");
            } else {
                money = 0.0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return money;
    }

    @Override
    public boolean updateCreatedDateOrderCoffee(int orderId) {
        boolean bl = false;
        Connection con;
        CallableStatement cs;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateCreatedDateOrderCoffee(?) }");
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
    public List<OrderCoffee> getOrderCoffeeByUser(String userName) {
        List<OrderCoffee> list = new ArrayList<>();
        Connection con;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{call getOrderCoffeeByUser(?)}");
            cs.setString(1, userName);
            rs = cs.executeQuery();
            while (rs.next()) {
                OrderCoffee oc = new OrderCoffee();
                oc.setOrderId(rs.getInt("OrderId"));
                UserImplements ui = new UserImplements();
                UserAccount user = ui.getUserByUserId(rs.getInt("UserId"));
                oc.setUserId(user);
                UserAccount userCashier = ui.getUserByUserId(rs.getInt("UserCashier"));
                oc.setUserCashier(userCashier);
                int cusId = rs.getInt("CusId");
                if (cusId > 0) {
                    CustomerImplements ci = new CustomerImplements();
                    Customer customer = ci.getCustomerByCusId(cusId);
                    oc.setCusId(customer);
                } else {
                    oc.setCusId(null);
                }
                oc.setTotalMoney(rs.getDouble("TotalMoney"));
                oc.setStatus(rs.getInt("Status"));
                oc.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplements.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(con, cs, rs);
        }
        return list;
    }
}
