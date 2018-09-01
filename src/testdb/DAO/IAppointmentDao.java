package testdb.DAO;

import Objects.Appointment;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IAppointmentDao {

    public void addAppointment(int appointmentId, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end);

    public void deleteAppointment(int deletedAppointmentId);

    public ArrayList<Appointment> getAllAppointments();

    public Appointment getAppointment(int appointmentId);

    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle);
}
