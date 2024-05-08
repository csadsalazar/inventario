package models;

import java.util.Date;

public class Observation {
    private int idBienPorUsuario;
    private User usuario;
    private Object bien;
    private String asunto;
    private String informacion;
    private Date fechaObservacion;

    // Constructor vacío
    public Observation() {
    }

    // Constructor
    public Observation(int idBienPorUsuario, User usuario, Object bien, String asunto, String informacion, Date fechaObservacion) {
        this.idBienPorUsuario = idBienPorUsuario;
        this.usuario = usuario;
        this.bien = bien;
        this.asunto = asunto;
        this.informacion = informacion;
        this.fechaObservacion = fechaObservacion;
    }

    // Getters y setters
    public int getIdBienPorUsuario() {
        return idBienPorUsuario;
    }

    public void setIdBienPorUsuario(int idBienPorUsuario) {
        this.idBienPorUsuario = idBienPorUsuario;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Object getBien() {
        return bien;
    }

    public void setBien(Object bien) {
        this.bien = bien;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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
