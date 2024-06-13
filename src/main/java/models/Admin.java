package models;

public class Admin {

    private int PK_idAdministrador;
    private String user;
    private String state;
 
    // Constructor vacío
    public Admin() {
    }

    // Constructor
    public Admin(int PK_idAdministrador, String user, String state) {
        this.PK_idAdministrador = PK_idAdministrador;
        this.user = user;
        this.state = state;
    }

    public int getPK_idAdministrador() {
        return PK_idAdministrador;
    }

    public void setPK_idAdministrador(int PK_idAdministrador) {
        this.PK_idAdministrador = PK_idAdministrador;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}