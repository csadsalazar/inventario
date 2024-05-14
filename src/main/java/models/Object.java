package models;

import java.util.Date;

public class Object {
    private int idBien;
    private long codigo;
    private String nombre;
    private int placa;
    private String descripcion;
    private long valor;
    private User usuario;
    private Dependency PK_idDependencia;
    private String estado;
    private String imagen1;
    private String imagen2;
    private Observation informacion;
    private Date fecha;


    // Constructor vacío
    public Object() {
    }

    // Constructor
    public Object(int idBien, long codigo, String nombre, int placa, String descripcion, long valor, User usuario, Dependency PK_idDependencia, String estado, String imagen1, String imagen2, Observation informacion, Date fecha) {
        this.idBien = idBien;
        this.codigo = codigo;
        this.nombre = nombre;
        this.placa = placa;
        this.descripcion = descripcion;
        this.valor = valor;
        this.usuario = usuario;
        this.PK_idDependencia = PK_idDependencia;
        this.estado = estado;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.informacion = informacion;
        this.fecha = fecha;
    }

    // Getters y setters
    public int getidBien() {
        return idBien;
    }

    public void setidBien(int idBien) {
        this.idBien = idBien;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
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

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Dependency getDependencia() {
        return PK_idDependencia;
    } 

    public void setDependencia(Dependency PK_idDependencia) {
        this.PK_idDependencia = PK_idDependencia;
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

    public Observation getObservacion() {
        return informacion;
    }

    public void setObservacion(Observation informacion) {
        this.informacion = informacion;
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
}