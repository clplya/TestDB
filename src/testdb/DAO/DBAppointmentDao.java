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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;
import static testdb.SQLStatements.selectAllAppointments;
import static testdb.SQLStatements.selectAppointmentById;

public class DBAppointmentDao implements IAppointmentDao {

    private Appointment appointment;
    private final ArrayList<Appointment> appointmentList;
    private final ArrayList<Appointment> finalAppointmentList;
    private JdbcRowSet rowSet = null;

    public DBAppointmentDao() {
        appointmentList = new ArrayList<>();
        finalAppointmentList = new ArrayList<>();
        appointment = null;
    }

    @Override
    public Appointment createAppointment(Appointment a) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("appointmentId", a.getAppointmentId());
        } catch (SQLException ex) {
            Logger.getLogger(DBAppointmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (appointmentList.contains(appointment)) {
            finalAppointmentList.add(appointment);
        }
        return a;
    }

    @Override
    public void deleteAppointment(Appointment deletedAppointment) {

    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(selectAllAppointments());

            while (rs.next()) {
                appointmentList.add(new Appointment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDate(8)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointmentList;
    }

    @Override
    public Appointment getCustomerAppointment(int customerId) {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(selectAppointmentById() + customerId);

            while (rs.next()) {
                int appointmentId = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String location = rs.getString(4);
                String contact = rs.getString(5);
                String URL = rs.getString(6);
                Date startDate = rs.getDate(7);
                Date endDate = rs.getDate(8);

                appointment = new Appointment(appointmentId, title, description, location, contact, URL, startDate, endDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointment;
    }

    @Override
    public void updateAppointment(Appointment oldAppointment, Appointment updatedAppointment) {

    }

    @Override
    public void updateAppointmentInfo(int appointmentId) {
        Appointment customerAppointment = getCustomerAppointment(appointmentId);
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.appointmentId,c.customerId,a.title,a.description,a.location,a.contact,a.URL,a.`start`,a.`end` from customer c join appointment a on c.customerId = a.customerId\n"
                                             + "where c.customerId =" + appointmentId);

            while (rs.next()) {

            }
        } catch (SQLException ex) {

        }

    }
}
