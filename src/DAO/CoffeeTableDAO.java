/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.CoffeeTable;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface CoffeeTableDAO {

    public boolean createNewCoffeeTable(CoffeeTable ct);

    public CoffeeTable findTableCoffeeByName(String nameTable, int areaId);

    public List<CoffeeTable> getAllCoffeeTables(int areaId);

    public List<CoffeeTable> checkExistCoffeeTable(String oldName, String newName, int areaId);

    public List<CoffeeTable> checkExistCoffeeTableInArea(String newName, int areaId);

    public boolean updateCoffeeTable(CoffeeTable ct);

    public boolean deleteCoffeeTable(int tableId);

    public int getQuantityCoffeeTableInArea(int areaId);

    public CoffeeTable getCoffeeTableByTableId(int tableId);

    public boolean updateStatusCoffeeTable(int status, int tableId);

    public List<CoffeeTable> getAllCoffeeTablesByStatus(int areaId, int status);

    public List<CoffeeTable> getCoffeeTablesByCustomer(Integer cusId);

    public List<CoffeeTable> getAllCoffeeTablesWithoutAreId();

    public List<CoffeeTable> getAllCoffeeTablesWithoutAreIdByStatus(int status);

    public List<CoffeeTable> getCoffeeTableNotSelectedTable(int tableId, int areaId, int status);

    public List<CoffeeTable> getCoffeeTableWithoutCustomer(int areaId, int tableId);

    public List<CoffeeTable> getAllCoffeeTablesWithoutSelectedTable(int areaId, int tableId);

    public List<CoffeeTable> getAllCoffeeTablesByTwoStatus(int areaId, int statusOne, int statusTwo);

}
