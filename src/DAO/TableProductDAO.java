/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Product;
import data.TableProduct;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface TableProductDAO {

    public boolean createNewTableProduct(TableProduct tp);

    public TableProduct checkExistTableProduct(int proId, int tableId, int status);

    public boolean updateQuantityTableProduct(int tblProId);

    public List<TableProduct> getTableProductByTableStatus(int tableId, int status);

    public boolean updateQuantityTableProductEmpty(int tblProId);

    public boolean updateQuantityTableProductClickTable(int tblProId, int quantity);

    public boolean deleteTableProduct(int tblProId);

    public boolean updatePlusQuantityOrderTableProduct(int tblProId, int quantity);

    public boolean updateSubstractQuantityOrderTableProduct(int tblProId, int quantity);

    public boolean createNewTableProductWithQuantity(TableProduct tp);

    public boolean updateTableIdTableProduct(int oldTableId, int newTableId);

    public List<TableProduct> checkProductExistInTableProduct(int proId, int newTableId);

    public boolean updateStatusTableProduct(int tblProId, int status);

    public double getSumMoneyTableProductByCustomer(int cusId);

    public List<TableProduct> getTableProductByOrderId(int orderId);

    public List<TableProduct> getTableProductByProduct(int proId);

}
