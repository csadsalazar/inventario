package models;

public class Administrador {

    private int PK_idAdministrador;
    private String usuario;
    private String estado;
 
    // Constructor vacío
    public Administrador() {
    }

    // Constructor
    public Administrador(int PK_idAdministrador, String usuario, String estado) {
        this.PK_idAdministrador = PK_idAdministrador;
        this.usuario = usuario;
        this.estado = estado;
    }

    public int getPK_idAdministrador() {
        return PK_idAdministrador;
    }

    public void setPK_idAdministrador(int PK_idAdministrador) {
        this.PK_idAdministrador = PK_idAdministrador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
