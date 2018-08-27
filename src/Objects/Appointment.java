package Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;

public class Appointment {

    private int appointmentId;
    private String title;
    private String description ;
    private String location;
    private String contact;
    private String URL;
    private Date startDate;
    private Date endDate;

    public Appointment(int appointmentId, String title, String description, String location,
            String contact, String URL, Date startDate, Date endDate) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.URL = URL;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location =location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date sDate) {
        this.startDate.setTime(sDate.getTime());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date eDate) {
        this.endDate.setTime(eDate.getTime());
    }

}
