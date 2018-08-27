package testdb.DAO;

import Objects.Appointment;
import java.util.ArrayList;

public interface IAppointmentDao {

    public Appointment createAppointment(Appointment appointment);

    public void deleteAppointment(Appointment appointment);

    public ArrayList<Appointment> getAllAppointments();

    public Appointment getCustomerAppointment(int customerId);

    public void updateAppointment(Appointment oldAppointment, Appointment updatedAppointment);

    public void updateAppointmentInfo(int appointmentId);
}
