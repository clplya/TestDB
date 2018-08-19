package testdb.DAO;

import Objects.Appointment;
import java.util.ArrayList;

public interface IAppointmentDao {

    boolean addAppointment(Appointment appointment);

    ArrayList<Appointment> getAllAppointments();

    Appointment getCustomerAppointment(int customerId);
}
