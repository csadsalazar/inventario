package models;

import java.util.Date;

public class Observation {
    private int idobjectperuser;
    private Date observationdate; 
    private String affair, information;
    private User user;
    private Object object;

    public Observation() {
    }

    public Observation(int idobjectperuser, Date observationdate, String affair, String information, User user,
            Object object) {
        this.idobjectperuser = idobjectperuser;
        this.observationdate = observationdate;
        this.affair = affair;
        this.information = information;
        this.user = user;
        this.object = object;
    }

    public int getIdobjectperuser() {
        return idobjectperuser;
    }

    public void setIdobjectperuser(int idobjectperuser) {
        this.idobjectperuser = idobjectperuser;
    }

    public Date getObservationdate() {
        return observationdate;
    }

    public void setObservationdate(Date observationdate) {
        this.observationdate = observationdate;
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

    @Override
    public String toString() {
        return "Observation [idobjectperuser=" + idobjectperuser + ", observationdate=" + observationdate + ", affair="
                + affair + ", information=" + information + ", user=" + user + ", object=" + object
                + ", getIdobjectperuser()=" + getIdobjectperuser() + ", getObservationdate()=" + getObservationdate()
                + ", getAffair()=" + getAffair() + ", getInformation()=" + getInformation() + ", getUser()=" + getUser()
                + ", getObject()=" + getObject() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}