package testdb.DAO;

import Objects.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static testdb.SQLStatements.selectAllCustomers;
import static testdb.SQLStatements.selectCustomerById;

public class DBCustomerDao implements ICustomerDao {

    List<Customer> allCustomers;
    Customer selectedCustomer;

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
        return true;
    }

    private void insertCustomer(Customer customer) {
        Statement stmt;
        int customerId = customer.getCustomerId();
        String customerName = customer.getCustomerName();
        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            stmt.executeUpdate("insert into customer values (" + customerId + "," + customerName + ")");
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
            ResultSet rs = stmt.executeQuery(selectAllCustomers());

            while (rs.next()) {
                allCustomers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return (ArrayList<Customer>) allCustomers;
    }

    @Override
    public Customer getById(int customerId) {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(selectCustomerById() + customerId);

            if (rs.getRow() != 0) {
                int customerIdDB = rs.getInt(1);
                String customerName = rs.getString(2);
                int active = rs.getInt(3);
                selectedCustomer = new Customer(customerIdDB, customerName, active);
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
