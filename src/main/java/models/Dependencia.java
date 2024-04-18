package models;

public class Dependencia {
    private int PK_idDependencia;
    private int codigo;
    private String nombreDependencia;

    // Constructor vacio
    public Dependencia() {
    }

    // Constructor 
    public Dependencia(int PK_idDependencia, String nombreDependencia, int codigo) {
        this.PK_idDependencia = PK_idDependencia;
        this.nombreDependencia = nombreDependencia;
        this.codigo = codigo;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
