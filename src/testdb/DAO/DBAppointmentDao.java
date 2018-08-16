package testdb.DAO;

import Objects.Appointment;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBAppointmentDao implements IAppointmentDao{
       private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();


    @Override
    public void addAppointment(Appointment appointment) {
                appointmentList.add(appointment);                
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Appointment getCustomerAppointment(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
