package models;

import java.util.Date;

public class Observacion {
    private int idBienPorUsuario;
    private Usuario usuario;
    private Bien bien;
    private String asunto;
    private String informacion;
    private Date fechaObservacion;

    // Constructor vacío
    public Observacion() {
    }

    // Constructor
    public Observacion(int idBienPorUsuario, Usuario usuario, Bien bien, String asunto, String informacion, Date fechaObservacion) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
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
