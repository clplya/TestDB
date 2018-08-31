package testdb.DAO;

import Objects.Customer;
import java.util.ArrayList;

public interface ICustomerDao {

    public void addCustomer(int customerId, String customerName, int addressId, int active);
    
    public void deleteCustomer(int deletedCustomerId);

    public ArrayList<Customer> getAllCustomers();

    public Customer getCustomer(int Id);
    
    public void updateCustomer(int customerId,String customerName);

}
