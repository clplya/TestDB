package testdb.DAO;

import Objects.Appointment;
import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean addAppointment(Appointment appointment) {
        if (appointmentList.contains(appointment)) {
            appointmentList.add(appointment);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        Statement stmt;
        appointmentList.clear();

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from appointment");

            while (rs.next()) {
                appointmentList.add(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointmentList;
    }

    @Override
    public Appointment getCustomerAppointment(int customerId) {
        Statement stmt;
        appointment = null;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.appointmentId,c.customerId,a.title,a.description,a.location,a.contact,a.URL,a.`start`,a.`end` from customer c join appointment a on c.customerId = a.customerId\n"
                    + "where c.customerId =" + customerId);

            if (rs.getRow() != 0) {
                int appointmentId = rs.getInt(1);
                int custId = rs.getInt(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                String location = rs.getString(5);
                String contact = rs.getString(6);
                String URL = rs.getString(7);
                Date startDate = rs.getDate(8);
                Date endDate = rs.getDate(9);
                appointment = new Appointment(appointmentId, custId, title, description, location, contact, URL, startDate, endDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointment;
    }

}

/*private final SimpleIntegerProperty appointmentId;
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty title;
    private final SimpleStringProperty description;
    private final SimpleStringProperty location;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty URL;
    private final Date startDate;
    private final Date endDate;*/
