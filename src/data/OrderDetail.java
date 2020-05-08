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
public class OrderDetail {
    private int orderDetailId;
    private OrderCoffee orderId;
    private CoffeeTable tableId;
    private int status;
    private Date createdDate;
    private Date updatedDate;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, OrderCoffee orderId, CoffeeTable tableId, int status, Date createdDate, Date updatedDate) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.tableId = tableId;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderCoffee getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderCoffee orderId) {
        this.orderId = orderId;
    }

    public CoffeeTable getTableId() {
        return tableId;
    }

    public void setTableId(CoffeeTable tableId) {
        this.tableId = tableId;
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
