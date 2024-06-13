package models;

import java.util.Date;

public class Observation {
    private int idobjectperuser;
    private User user;
    private Object object;
    private String affair;
    private String information;
    private Date observationdate; 

    // Constructor vacío
    public Observation() {
    }

    // Constructor
    public Observation(int idobjectperuser, User user, Object object, String affair, String information, Date observationdate) {
        this.idobjectperuser = idobjectperuser;
        this.user = user;
        this.object = object;
        this.affair = affair;
        this.information = information;
        this.observationdate = observationdate;
    }

    // Getters y setters
    public int getIdObjectPerUser() {
        return idobjectperuser;
    }

    public void setIdObjectPerUser(int idobjectperuser) {
        this.idobjectperuser = idobjectperuser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getAffair() {
        return affair;
    }

    public void setAffair(String affair) {
        this.affair = affair;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getObservationDate() {
        return observationdate;
    }

    public void setObservationDate(Date observationdate) {
        this.observationdate = observationdate;
    }
}