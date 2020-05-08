/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Customer;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface CustomerDAO {

    public int createNewCustomer(Customer c);

    public List<Customer> getAllCustomers();

    public Customer getCustomerByEmailAndStatus(String email, int status);

    public Customer getCustomerByCusId(int cusId);

    public Customer getCustomerByEmail(String email);

    public Customer getCustomerByTableId(int tableId, int status);

    public boolean updateStatusCustomer(int cusId, int status);

    public boolean updateInfoCustomer(Customer c);

    public boolean deleteCustomersByEmailAndStatus(String email, int status);
}
