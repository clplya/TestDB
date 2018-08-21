package testdb.DAO;

import Objects.Appointment;
import java.util.ArrayList;

public interface IAppointmentDao {

    boolean addAppointment(Appointment appointment);

    void deleteAppointment(Appointment appointment);

    ArrayList<Appointment> getAllAppointments();

    Appointment getCustomerAppointment(int customerId);

    void updateAppointment(Appointment oldAppointment, Appointment updatedAppointment);

    void updateAppointmentInfo(int appointmentId);
}
