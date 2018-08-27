package testdb.DAO;

import Objects.Customer;
import java.util.ArrayList;

public interface ICustomerDao {

    public boolean addCustomer(Customer customer);

    public ArrayList<Customer> getAllCustomers();

    public Customer getById(int Id);

}
