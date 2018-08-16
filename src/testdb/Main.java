package testdb;

import Objects.Customer;
import java.util.ArrayList;
import testdb.DAO.DBCustomerDao;

public class Main {

    public static void main(String[] args) {
        DBCustomerDao dbCustomer = new DBCustomerDao();
        ArrayList<Customer> customerList = dbCustomer.getAll();
        
        System.out.println(customerList);
        
        Customer customer = dbCustomer.getById(1);
        System.out.println(customer);
        
        boolean check = dbCustomer.add(customer);
        System.out.println(check);
        

        
    }
    
}
