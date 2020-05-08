/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Product;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ProductDAO {

    public boolean insertNewProduct(Product p);

    public boolean updateProduct(Product p);

    public boolean deleteProduct(Integer proId);

    public List<Product> listOfProduct();

    public boolean findProductByProName(String proName);

    public Product findProductByName(String proName);

    public List<Product> checkExistProduct(String oldProName, String newProName);

    public List<Product> showProductByCategory(Integer cateId);

    public List<Product> searchProductByName(String proName);

    public Product FindProductByProId(int proId);
    
    public boolean updateQuantityProductClickOrder(int proId);
    
    public boolean updatePlusQuantityProduct(int proId,int quantity);
    
    public boolean updateSubstractQuantityProduct(int proId,int quantity);
    
}
