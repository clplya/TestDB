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
    public boolean addCustomer(Customer customer) {
        allCustomers.addAll(getAllCustomers());
        for (int i = 0; i < allCustomers.size(); i++) {
            allCustomers.get(i);
        }
        if (allCustomers.contains(customer)) {
            return false;
        }
        insertCustomer(customer);
        //allCustomers.add(customer);
        return true;
    }

    private void insertCustomer(Customer customer) {
        Statement stmt;
        int customerId = customer.getCustomerId();
        String customerName = customer.getCustomerName();
        String address = customer.getAddress();
        String phone = customer.getPhone();
        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            int insert = stmt.executeUpdate("insert into customer values (" + customerId + "," + customerName + ", " + address + "," + phone + ")");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
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
    public Customer getById(int customerId) {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select c.customerId,c.customerName,a.address,a.phone from customer c join address a on c.addressId = a.addressId where c.CustomerId = " + customerId);

            if (rs.getRow() != 0) {
                int customerIdDB = rs.getInt(1);
                String customerName = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                selectedCustomer = new Customer(customerIdDB, customerName, address, phone);
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
