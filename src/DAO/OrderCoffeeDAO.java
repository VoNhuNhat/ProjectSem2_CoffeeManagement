/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.OrderCoffee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface OrderCoffeeDAO {

    public boolean createNewOrder(OrderCoffee oc);

    public OrderCoffee getOrderByCusId(int cusId, int status);

    public int createNewOrderWithoutCustomerId(int userId);

    public OrderCoffee getOrderByOrderId(int orderId);

    public boolean updateStatusOrderCoffee(int orderId, int status);

    public boolean deleteOrderCoffee(int orderId);

    public boolean updateTotalMoneyOrderCoffee(int orderId, double totalMoney);

    public boolean updateUserCashierOrderCoffee(int orderId, int status);

    public List<OrderCoffee> getOrderCoffeeByDate(Date date, int status);

    public List<OrderCoffee> getOrderCoffeeBetweenDate(Date startDate, Date endDate, int status);

    public List<OrderCoffee> searchOrderCoffeeByUserAccount(String fullName, int status);

    public List<OrderCoffee> searchOrderCoffeeByCustomer(String fullName, int status);

    public List<OrderCoffee> searchOrderCoffeeByUserAccountDate(String fullName, Date date, int status);

    public List<OrderCoffee> searchOrderCoffeeByCustomerDate(String fullName, Date date, int status);

    public List<OrderCoffee> searchOrderCoffeeByUserAccountBetweenDate(String fullName, Date startDate, Date endDate, int status);

    public List<OrderCoffee> searchOrderCoffeeByCustomerBetweenDate(String fullName, Date startDate, Date endDate, int status);

    public Double getSumTotalMoneyOrderByMonthAndYear(int month, int year, int status);

    public boolean updateCreatedDateOrderCoffee(int orderId);

    public List<OrderCoffee> getOrderCoffeeByUser(String userName);

}
