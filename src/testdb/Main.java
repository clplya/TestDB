package testdb;

import Objects.Appointment;
import Objects.Customer;
import java.util.ArrayList;
import testdb.DAO.DBAppointmentDao;
import testdb.DAO.DBCustomerDao;

public class Main {
//when adding users- check out pg 427 on lambda expressions to sort a collection for authorization

    public static void main(String[] args) {
        //First Part of the class checks Customer class methods
        Customer printedCustomer;
        DBCustomerDao dbCustomer = new DBCustomerDao();
        ArrayList<Customer> customerList = dbCustomer.getAll();

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i).getCustomerName());
        }

        Customer customer = dbCustomer.getById(1);
        System.out.println(customer);

        boolean check = dbCustomer.add(customer);
        System.out.println(check);

        //Second part of the class checks the Appointment class methods
        DBAppointmentDao dbAppointment = new DBAppointmentDao();
        ArrayList<Appointment> appointmentList = dbAppointment.getAllAppointments();

        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println((appointmentList.get(i).getTitle()));
        }

        Appointment customerAppointment = dbAppointment.getCustomerAppointment(1);
        System.out.println(customerAppointment);

    }

}
