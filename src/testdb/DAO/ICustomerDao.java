package testdb.DAO;

import Objects.Customer;
import java.util.ArrayList;

public interface ICustomerDao {

    boolean addCustomer(Customer customer);

    ArrayList<Customer> getAllCustomers();

    Customer getById(int Id);

}
