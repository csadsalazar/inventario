package models;

import java.util.Date;

public class ObservationAdmin {
    private int idObservacionAdmin;
    private Admin admin;
    private Object bien;
    private String informacion;
    private Date fechaObservacion; 

    // Constructor vacío
    public ObservationAdmin() {
    }

    // Constructor
    public ObservationAdmin(int idObservacionAdmin, Admin admin, Object bien, String informacion, Date fechaObservacion) {
        this.idObservacionAdmin = idObservacionAdmin;
        this.admin = admin;
        this.bien = bien;
        this.informacion = informacion;
        this.fechaObservacion = fechaObservacion;
    }

    // Getters y setters
    public int getIdObservacionAdmin() {
        return idObservacionAdmin;
    }

    public void setIdObservacionAdmin(int idObservacionAdmin) {
        this.idObservacionAdmin = idObservacionAdmin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Object getBien() {
        return bien;
    }

    public void setBien(Object bien) {
        this.bien = bien;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Date getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(Date fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }
}

