package testdb.DAO;

import Objects.Appointment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DBAppointmentDao implements IAppointmentDao {

    private Appointment appointment;
    private final ArrayList<Appointment> appointmentList;

    public DBAppointmentDao() {
        appointmentList = new ArrayList<>();
        appointment = null;
    }

    @Override
    public void addAppointment(int appointmentId, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into appointment(appointmentId,title,description,location,contact,url,start,end,createDate,createdBy,lastUpdateBy) values (" + appointmentId + ",'" + title + "','" + description + "'," + location + "," + contact + "," + url + "," + start + "," + end + ",now(),1,1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAppointment(int deletedAppointmentId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from appointment where appointmentId=" + deletedAppointmentId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        Statement stmt = null;
        Timestamp ts;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select appointmentId,title,description,location,contact,url,start,end from appointment";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                String title = result.getString(2);
                String description = result.getString(3);
                String location = result.getString(4);
                String contact = result.getString(5);
                String url = result.getString(6);
                Date startDate = result.getDate(7);
                Date endDate = result.getDate(8);

                appointment = new Appointment(appointmentId, title, description, location, contact, url, startDate, endDate);
                appointmentList.add(appointment);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        }
        return appointmentList;
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select appointmentId,title,description,location,contact,url,start,end from appointment where appointmentId =" + appointmentId);

            while (result.next()) {
                int apptId = result.getInt(1);
                String title = result.getString(2);
                String description = result.getString(3);
                String location = result.getString(4);
                String contact = result.getString(5);
                String url = result.getString(6);
                Date startDate = result.getDate(7);
                Date endDate = result.getDate(8);

                appointment = new Appointment(apptId, title, description, location, contact, url, startDate, endDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointment;
    }

    @Override
    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = null;
            updateSql = "update appointment set appointment.title =" + upAppointmentTitle + " where appointment.appointmentId =" + upAppointmentId;
            stmt.executeUpdate(updateSql);

            String selectSql = "select appointmentId,title,description,location,contact,url,start,end from appointment where appointment.appointmentId=" + upAppointmentId;
            ResultSet result = stmt.executeQuery(selectSql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                String title = result.getString(2);
                String description = result.getString(3);
                String location = result.getString(4);
                String contact = result.getString(5);
                String url = result.getString(6);
                Date startDate = result.getDate(7);
                Date endDate = result.getDate(8);

                appointment = new Appointment(appointmentId, title, description, location, contact, url, startDate, endDate);
                System.out.println("Updated Appointment Title: " + appointment.getTitle());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
