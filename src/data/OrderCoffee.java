/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class OrderCoffee {
    private int orderId;
    private UserAccount userId;
    private UserAccount userCashier;
    private Customer cusId;
    private double totalMoney;
    private int status;
    private Date createdDate;
    private Date updatedDate;

    public OrderCoffee() {
    }

    public OrderCoffee(int orderId, UserAccount userId, UserAccount userCashier, Customer cusId, double totalMoney, int status, Date createdDate, Date updatedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.userCashier = userCashier;
        this.cusId = cusId;
        this.totalMoney = totalMoney;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
    }

    public UserAccount getUserCashier() {
        return userCashier;
    }

    public void setUserCashier(UserAccount userCashier) {
        this.userCashier = userCashier;
    }
    
    public Customer getCusId() {
        return cusId;
    }

    public void setCusId(Customer cusId) {
        this.cusId = cusId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
