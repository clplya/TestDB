package testdb.DAO;

import Objects.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBCustomerDao implements ICustomerDao {

    Customer customer;
    ArrayList<Customer> customerList;

    public DBCustomerDao() {
        customerList = new ArrayList<>();
        customer = null;
    }

    @Override
    public void addCustomer(int customerId, String customerName, int addressId, int active) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into customer(customerId,customerName,addressId,active,createdBy,createDate,lastUpdateBy) values (" + customerId + ",'" + customerName + "','" + addressId + "'," + active + ",1,now(),1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public void deleteCustomer(int deletedCustomerId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from customer where customerId=" + deletedCustomerId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select customerId,customerName,addressId,active from customer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                int addressId = rs.getInt(3);
                int active = rs.getInt(4);

                customer = new Customer(customerId, customerName, addressId, active);
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        }
        return customerList;
    }

    @Override
    public Customer getCustomer(int customerId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select customerId,customerName,addressId,active from customer where customerId =" + customerId);

            while (rs.next()) {
                int customerID = rs.getInt(1);
                String customerName = rs.getString(2);
                int addressId = rs.getInt(3);
                int active = rs.getInt(4);

                customer = new Customer(customerID, customerName, addressId, active);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return customer;

//        for (Customer c : allCustomers) {
//            if (c.getCustomerId() == Id) {
//                return c;
//        }
//        }
//        return null;
    }

}
