package models;

public class User {
    private int PK_idUsuario;
    private String nombre;
    private String usuario;
    private int cedula;
    private String contrasena;
    private String dependencia;
    private String cargo;
    private String contrato;
    private String sede;

    // Constructor vacío
    public User() {
    }

    // Constructor
    public User(int PK_idUsuario, String nombre, String usuario, int cedula, String contrasena, String dependencia, String cargo, String contrato, String sede) {
        this.PK_idUsuario = PK_idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.dependencia = dependencia;
        this.cargo = cargo;
        this.contrato = contrato;
        this.sede = sede;
    }

    public int getPK_idUsuario() {
        return PK_idUsuario;
    }

    public void setPK_idUsuario(int PK_idUsuario) {
        this.PK_idUsuario = PK_idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
