package testdb.DAO;

import Objects.Address;
import java.util.ArrayList;

public interface IAddressDao {

    public boolean addAddress(Address address);

    public void deleteAddress(Address address);

    public ArrayList<Address> getAllAddresses();

    public Address getCustomerAddress(int customerId);

    public void updateAddress(Address oldAddress, Address updatedAddress);

    public void updateAddressInfo(int addressId);
}
