package testdb;

public class SQLStatements {

    private final static String selectAllCustomers = "select * from customer";
    private final static String selectCustomerById = "select c.customerId,c.customerName from customer where customerId = ";

    private final static String selectAllAppointments = "select * from appointment";
    private final static String selectAppointmentById = "select a.appointmentId,c.customerId,a.title,a.description,a.location,a.contact,"
            + "a.URL,a.`start`,a.`end` from customer c join appointment a on c.customerId = a.customerId where c.customerId =";

    public static String selectAllCustomers() {
        return selectAllCustomers;
    }

    public static String selectCustomerById() {
        return selectCustomerById;
    }

    public static String selectAllAppointments() {
        return selectAllAppointments;
    }

    public static String selectAppointmentById() {
        return selectAppointmentById;
    }
}
