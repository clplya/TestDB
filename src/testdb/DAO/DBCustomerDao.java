package testdb.DAO;

import Objects.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBCustomerDao implements ICustomerDao {

    private final ArrayList<Customer> allCustomers;
    private Customer selectedCustomer;

    public DBCustomerDao() {
        allCustomers = new ArrayList<>();
        selectedCustomer = null;
    }

    @Override
    public boolean add(Customer customer) {
        if (!allCustomers.contains(customer)) {
            allCustomers.add(customer);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Customer> getAll() {
        Statement stmt;
        allCustomers.clear();

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from customer");

            while (rs.next()) {
                allCustomers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCustomers;
    }

    @Override
    public Customer getById(int Id) {
        Statement stmt;
        selectedCustomer = null;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select c.customerId,c.customerName,a.address,a.phone from customer c join address a on c.addressId = a.addressId\n"
                    + "where c.CustomerId = " + Id);

            if (rs.getRow() != 0) {
                int customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                selectedCustomer = new Customer(customerId, customerName, address, phone);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return selectedCustomer;

//        for (Customer c : allCustomers) {
//            if (c.getCustomerId() == Id) {
//                return c;
//        }
//        }
//        return null;
    }

}
