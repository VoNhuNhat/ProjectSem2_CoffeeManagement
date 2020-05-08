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
public class Product {

    private int proId;
    private String proName;
    private Category cateId;
    private double price;
    private int quantity;
    private String proImage;
    private Date createdDate;
    private Date updatedDate;

    public Product() {
    }

    public Product(int proId, String proName, Category cateId, double price, int quantity, String proImage, Date createdDate, Date updatedDate) {
        this.proId = proId;
        this.proName = proName;
        this.cateId = cateId;
        this.price = price;
        this.quantity = quantity;
        this.proImage = proImage;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Product(String proName, int cateId, double price, int quantity, String proImage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Category getCateId() {
        return cateId;
    }

    public void setCateId(Category cateId) {
        this.cateId = cateId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
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
