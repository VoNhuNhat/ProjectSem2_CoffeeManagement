/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author tranq
 */
public class TableProduct {
    int TblProId;
    Product ProId;
    CoffeeTable TableId;
    int QuantityOrder;
    int Status;
    Date CreatedDate;
    Date UpdatedDate;

    public TableProduct() {
    }

    public TableProduct(int TblProId, Product ProId, CoffeeTable TableId, int QuantityOrder, int Status, Date CreatedDate, Date UpdatedDate) {
        this.TblProId = TblProId;
        this.ProId = ProId;
        this.TableId = TableId;
        this.QuantityOrder = QuantityOrder;
        this.Status = Status;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
    }

    public int getTblProId() {
        return TblProId;
    }

    public void setTblProId(int TblProId) {
        this.TblProId = TblProId;
    }

    public Product getProId() {
        return ProId;
    }

    public void setProId(Product ProId) {
        this.ProId = ProId;
    }

    public CoffeeTable getTableId() {
        return TableId;
    }

    public void setTableId(CoffeeTable TableId) {
        this.TableId = TableId;
    }

    public int getQuantityOrder() {
        return QuantityOrder;
    }

    public void setQuantityOrder(int QuantityOrder) {
        this.QuantityOrder = QuantityOrder;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }
    
    

}
