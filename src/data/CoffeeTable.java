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
public class CoffeeTable {

    int TableId;
    String TableName;
    Area AreaId;
    int Status;
    Date CreatedDate;
    Date UpdatedDate;

    public CoffeeTable() {
    }

    public CoffeeTable(int TableId, String TableName, Area AreaId, int Status, Date CreatedDate, Date UpdatedDate) {
        this.TableId = TableId;
        this.TableName = TableName;
        this.AreaId = AreaId;
        this.Status = Status;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
    }

    public int getTableId() {
        return TableId;
    }

    public void setTableId(int TableId) {
        this.TableId = TableId;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public Area getAreaId() {
        return AreaId;
    }

    public void setAreaId(Area AreaId) {
        this.AreaId = AreaId;
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

    @Override
    public String toString() {
        return TableName;
    }

}
