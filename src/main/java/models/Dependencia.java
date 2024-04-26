package models;

public class Dependencia {
    private int PK_idDependencia;
    private String centroDeCosto;
    private String nombreDependencia;

    // Constructor vacio
    public Dependencia() {
    }

    // Constructor 
    public Dependencia(int PK_idDependencia, String nombreDependencia, String centroDeCosto) {
        this.PK_idDependencia = PK_idDependencia;
        this.nombreDependencia = nombreDependencia;
        this.centroDeCosto = centroDeCosto;
    }

    // Getters y Setters
    public int getPK_idDependencia() {
        return PK_idDependencia;
    }

    public void setPK_idDependencia(int PK_idDependencia) {
        this.PK_idDependencia = PK_idDependencia;
    }

    public String getnombreDependencia() {
        return nombreDependencia;
    }

    public void setnombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public String getCentroDeCosto() {
        return centroDeCosto;
    }

    public void setCentroDeCosto(String centroDeCosto) {
        this.centroDeCosto = centroDeCosto;
    }
}
