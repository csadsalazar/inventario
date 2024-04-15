package models;

public class Bien {
    private int idBien;
    private int codigo;
    private String nombre;
    private int placa;
    private String descripcion;
    private int valor;
    private Usuario usuario;
    private Dependencia nombreDependencia;
    private String estado;
    private String imagen1;
    private String imagen2;
    private Observacion informacion;

    // Constructor vacío
    public Bien() {
    }

    // Constructor
    public Bien(int idBien, int codigo, String nombre, int placa, String descripcion, int valor, Usuario usuario, Dependencia nombreDependencia, String estado, String imagen1, String imagen2, Observacion informacion) {
        this.idBien = idBien;
        this.codigo = codigo;
        this.nombre = nombre;
        this.placa = placa;
        this.descripcion = descripcion;
        this.valor = valor;
        this.usuario = usuario;
        this.nombreDependencia = nombreDependencia;
        this.estado = estado;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.informacion = informacion;
    }

    // Getters y setters
    public int getidBien() {
        return idBien;
    }

    public void setidBien(int idBien) {
        this.idBien = idBien;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dependencia getDependencia() {
        return nombreDependencia;
    }

    public void setDependencia(Dependencia nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public Observacion getObservacion() {
        return informacion;
    }

    public void setObservacion(Observacion informacion) {
        this.informacion = informacion;
    }
}
