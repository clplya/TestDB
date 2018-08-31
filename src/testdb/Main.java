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
        customerMethods();
        userMethods();
        
        // Second part of the class checks the Appointment class methods
        //DBAppointmentDao dbAppointment = new DBAppointmentDao();
        //ArrayList<Appointment> appointmentList = dbAppointment.getAllAppointments();
        //for (int i = 0; i < appointmentList.size(); i++) {
        //  System.out.println((appointmentList.get(i).getTitle()));
        //}
        //      Appointment customerAppointment = dbAppointment.getCustomerAppointment(1);
        //  System.out.println(customerAppointment.getDescription());
        //Third part of the class checks the Address class methods
        //    DBAddressDao dbAddress = new DBAddressDao();
        //       ArrayList<Address> addressList = dbAddress.getAllAddresses();
//        for (int i = 0; i < addressList.size(); i++) {
//            System.out.println((addressList.get(i).getAddress()));
//        }
        //     Address customerAddress = dbAddress.getCustomerAddress(1);
        // System.out.println(customerAddress.getAddress());
        //Fourth part of the class checks the User class methods
        // System.out.println(user.getUserName());
        //Second part of the class checks the Appointment class methods
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
            dbCustomer.addCustomer(4, "test", 1,1);
            Customer addedCustomer = dbCustomer.getCustomer(4);
            System.out.println(addedCustomer.getCustomerName());
        } else { //Customer not present
            System.out.println("Customer not present");
            dbCustomer.addCustomer(4, "test", 1,1);
            Customer addedCustomer = dbCustomer.getCustomer(4);
            System.out.println(addedCustomer.getCustomerName());
        }
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
    }
}
