package testdb;

import Objects.Address;
import Objects.Appointment;
import Objects.Customer;
import Objects.User;
import java.util.ArrayList;
import testdb.DAO.DBAddressDao;
import testdb.DAO.DBAppointmentDao;
import testdb.DAO.DBCustomerDao;
import testdb.DAO.DBUserDao;

public class Main {
//when adding users- check out pg 427 on lambda expressions to sort a collection for authorization

    public static void main(String[] args) {
        System.out.println("******ADDRESS METHODS:");
        addressMethods();
        System.out.println("******APPOINTMENT METHODS:");
        appointmentMethods();
        System.out.println("******CUSTOMER METHODS:");
        customerMethods();
        System.out.println("******USER METHODS:");
        userMethods();

    }

    private static void addressMethods() {
        DBAddressDao dbAddress = new DBAddressDao();

        //get all addresses
        ArrayList<Address> addressList = dbAddress.getAllAddresses();

        for (int i = 0; i < addressList.size(); i++) {
            System.out.println((addressList.get(i).getAddress1()));  //first & second print
        }
        //get single address
        Address address = dbAddress.getAddress(1);
        System.out.println(address.getAddress1());

        //get all & delete address if found
        boolean addressPresent = false;
        //int presentAddressId = addressList.get()

        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getAddressId() == 4) {
                addressPresent = true;
            }
        }

        if (addressPresent == true) { //Address present
            dbAddress.deleteAddress(4);
            System.out.println("Address present");
            dbAddress.addAddress(4, "100 Main St.", "", 4, "29210", "8505235241");
            Address addedAddress = dbAddress.getAddress(4);
            System.out.println(addedAddress.getAddress1());
        } else { //Address not present
            System.out.println("Address not present");
            dbAddress.addAddress(4, "100 Main St.", "", 4, "29210", "8505235241");
            Address addedAddress = dbAddress.getAddress(4);
            System.out.println(addedAddress.getAddress1());
        }

        dbAddress.updateAddress(4, "'105 Side St.'");
    }

    //APPOINTMENT METHODS
    private static void appointmentMethods() {
        DBAppointmentDao dbAppointment = new DBAppointmentDao();

//get all customers
        ArrayList<Appointment> appointmentList = dbAppointment.getAllAppointments();

        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println((appointmentList.get(i).getTitle()));  //first & second print
        }
//get single appointment
        Appointment getAppointment = dbAppointment.getAppointment(1);
        System.out.println(getAppointment.getTitle());

//get all & delete appointment if found
        boolean appointmentPresent = false;
        //int presentAppointmentId = appointmentList.get()

        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getAppointmentId() == 4) {
                appointmentPresent = true;
            }
        }

        if (appointmentPresent == true) { //appointment present
            dbAppointment.deleteAppointment(4);
            System.out.println("Appointment present");
            dbAppointment.addAppointment(4, "Test Appointment", "This is a test Appointment", "Your Doctor's Office", "Tammy at the Front Desk", now(), now());
            Appointment addedAppointment = dbAppointment.getAppointment(4);
            System.out.println(addedAppointment.getTitle());
        } else { //Appointment not present
            System.out.println("Appointment not present");
            dbAppointment.addAppointment(4, "Test Appointment", "This is a test Appointment", "Your Doctor's Office", "Tammy at the Front Desk", now(), now());
            Appointment addedAppointment = dbAppointment.getAppointment(4);
            System.out.println(addedAppointment.getTitle());
        }

        dbAppointment.updateAppointmentTitle(4, "'Second Test Title'");
    }

    //CUSTOMER METHODS
    private static void customerMethods() {
        DBCustomerDao dbCustomer = new DBCustomerDao();

//get all customers
        ArrayList<Customer> customerList = dbCustomer.getAllCustomers();

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println((customerList.get(i).getCustomerName()));  //first & second print
        }
//get single customer
        Customer getCustomer = dbCustomer.getCustomer(1);
        System.out.println(getCustomer.getCustomerName());

//get all & delete customer if found
        boolean customerPresent = false;
        //int presentCustomerId = customerList.get()

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerId() == 4) {
                customerPresent = true;
            }
        }

        if (customerPresent == true) { //customer present
            dbCustomer.deleteCustomer(4);
            System.out.println("Customer present");
            dbCustomer.addCustomer(4, "test", 1, 1);
            Customer addedCustomer = dbCustomer.getCustomer(4);
            System.out.println(addedCustomer.getCustomerName());
        } else { //Customer not present
            System.out.println("Customer not present");
            dbCustomer.addCustomer(4, "test", 1, 1);
            Customer addedCustomer = dbCustomer.getCustomer(4);
            System.out.println(addedCustomer.getCustomerName());
        }

        dbCustomer.updateCustomer(4, "'Barbara'");
    }

    //USER METHODS
    private static void userMethods() {
        DBUserDao dbUser = new DBUserDao();

//get all users
        ArrayList<User> userList = dbUser.getAllUsers();

        for (int i = 0; i < userList.size(); i++) {
            System.out.println((userList.get(i).getUserName()));  //first & second print
        }
//get single user
        User getUser = dbUser.getUser(1);
        System.out.println(getUser.getUserName());

//get all & delete user if found
        boolean userPresent = false;
        //int presentUserId = userList.get()

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId() == 4) {
                userPresent = true;
            }
        }

        if (userPresent == true) {
            dbUser.deleteUser(4);
            System.out.println("User present");
            dbUser.addUser(4, "test", "test", 1);
            User getAddedUser = dbUser.getUser(4);
            System.out.println(getAddedUser.getUserName());
        } else {
            System.out.println("User not present");
            dbUser.addUser(4, "test", "test", 1);
            User getAddedUser = dbUser.getUser(4);
            System.out.println(getAddedUser.getUserName());
        }

        dbUser.updateUserName(4, "'King'");
    }
}
