package testdb.DAO;

import Objects.Address;
import java.util.ArrayList;

public interface IAddressDao {

    boolean addAddress(Address address);

    void deleteAddress(Address address);

    ArrayList<Address> getAllAddresses();

    Address getCustomerAddress(int customerId);

    void updateAddress(Address oldAddress, Address updatedAddress);

    void updateAddressInfo(int addressId);
}
