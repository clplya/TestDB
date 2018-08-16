package testdb.DAO;

import Objects.Customer;
import java.util.ArrayList;

public interface ICustomerDao {

    ArrayList<Customer> getAll();

    Customer getById(int Id);

    boolean add(Customer customer);
}
