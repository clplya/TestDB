package testdb;

import Objects.Appointment;
import Objects.Customer;
import java.util.ArrayList;
import testdb.DAO.DBAppointmentDao;
import testdb.DAO.DBCustomerDao;

public class Main {

    public static void main(String[] args) {
        //check Customer class
        DBCustomerDao dbCustomer = new DBCustomerDao();
        ArrayList<Customer> customerList = dbCustomer.getAll();

        System.out.println(customerList);

        Customer customer = dbCustomer.getById(1);
        System.out.println(customer);

        boolean check = dbCustomer.add(customer);
        System.out.println(check);

        //check Appointment class
        DBAppointmentDao dbAppointment = new DBAppointmentDao();
        ArrayList<Appointment> appointmentList = dbAppointment.getAllAppointments();

        System.out.println(appointmentList);

        Appointment customerAppointment;
        dbAppointment.getCustomerAppointment(1);

    }

}
