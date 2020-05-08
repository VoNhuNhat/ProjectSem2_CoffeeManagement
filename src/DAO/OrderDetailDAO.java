/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.OrderDetail;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface OrderDetailDAO {

    public boolean createNewOrderDetail(OrderDetail od);

    public OrderDetail getOrderDetailByTableStatus(int tableId, int status);

    public boolean updateTableIdOrderDetail(int oldTableId, int newTableId, int status);

    public boolean updateStatusOrderDetail(int OrderDetailId, int status);

    public boolean deleteOrderDetail(int OrderDetailId);

    public List<OrderDetail> getOrderDetailByTable(int tableId);

    public boolean deleteOrderDetailByOrderId(int orderId);

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId);
}
