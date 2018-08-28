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
        //First Part of the class checks Customer class methods
        DBCustomerDao dbCustomer = new DBCustomerDao();

        ArrayList<Customer> customerList = dbCustomer.getAllCustomers();

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i).getCustomerName());
        }
        customerList.forEach(System.out::println);

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(dbCustomer.getById(i));
        }

        Customer addedCustomer = new Customer(3, "Test Name", 1);
        boolean check = dbCustomer.addCustomer(addedCustomer);
        System.out.println(check);
        System.out.println(addedCustomer.getCustomerName());

        //Second part of the class checks the Appointment class methods
        DBAppointmentDao dbAppointment = new DBAppointmentDao();
        ArrayList<Appointment> appointmentList = dbAppointment.getAllAppointments();

        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println((appointmentList.get(i).getTitle()));
        }

        Appointment customerAppointment = dbAppointment.getCustomerAppointment(1);
        System.out.println(customerAppointment.getDescription());

        //Third part of the class checks the Address class methods
        DBAddressDao dbAddress = new DBAddressDao();
        ArrayList<Address> addressList = dbAddress.getAllAddresses();

        for (int i = 0; i < addressList.size(); i++) {
            System.out.println((addressList.get(i).getAddress()));
        }

        Address customerAddress = dbAddress.getCustomerAddress(1);
        System.out.println(customerAddress.getAddress());
        //Fourth part of the class checks the User class methods
        DBUserDao dbUser = new DBUserDao();
        ArrayList<User> userList = dbUser.getAllUsers();

        for (int i = 0; i < userList.size(); i++) {
            System.out.println((userList.get(i).getUserName()));
        }

        User user = dbUser.getUser(1);
        System.out.println(user.getUserName());
        //Second part of the class checks the Appointment class methods
    }
}
