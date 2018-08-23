package testdb.DAO;

import Objects.Address;
import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAddressDao implements IAddressDao {

    private Address address;
    private final ArrayList<Address> addressList;
    private final ArrayList<Address> finalAddressList;

    public DBAddressDao() {
        addressList = new ArrayList<>();
        finalAddressList = new ArrayList<>();
        address = null;
    }

    @Override
    public boolean addAddress(Address address) {
        if (addressList.contains(address)) {
            finalAddressList.add(address);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAddress(Address deletedAddress) {

    }

    @Override
    public ArrayList<Address> getAllAddresses() {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from address");

            while (rs.next()) {
                addressList.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return addressList;
    }

    @Override
    public Address getCustomerAddress(int customerId) {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.addressId,a.address,a.address2,a.cityId,a.postalCode,a.phone from address a "
                    + "join customer c on a.addressId = c.addressId where c.customerId =" + customerId);

            while (rs.next()) {
                int addressId = rs.getInt(1);
                String address1 = rs.getString(2);
                String address2 = rs.getString(3);
                int cityId = rs.getInt(4);
                String postalCode = rs.getString(5);
                String phone = rs.getString(6);

                address = new Address(addressId, address1, address2, cityId, postalCode, phone);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return address;
    }

    @Override
    public void updateAddress(Address oldAddress, Address updatedAddress) {

    }

    @Override
    public void updateAddressInfo(int addressId) {
        Address customerAddress = getCustomerAddress(addressId);
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.addressId,c.customerId,a.title,a.description,a.location,a.contact,a.URL,a.`start`,a.`end` from customer c join address a on c.customerId = a.customerId\n"
                    + "where c.customerId =" + addressId);

            while (rs.next()) {

            }
        } catch (SQLException ex) {

        }

    }
}
