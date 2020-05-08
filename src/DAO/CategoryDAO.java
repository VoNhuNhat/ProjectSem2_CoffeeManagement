/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Category;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategoryDAO {

    public boolean insertNewCategory(Category c);

    public boolean updateCategory(Category c);

    public boolean deleteCategoryAndProduct(Integer cateId, String cateName);

    public boolean deleteCategory(Integer cateId);

    public List<Category> listOfCategory();

    public Category findCategoryById(Integer cateId);

    public Category findCategoryByName(String cateName);

    public List<Category> checkExistCategory(String oldCateName, String newCateName);

    public boolean checkCategoryName(String cateName);
}
