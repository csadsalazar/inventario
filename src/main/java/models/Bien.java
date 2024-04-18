package models;

public class Bien {
    private int idBien;
    private long codigo;
    private String nombre;
    private int placa;
    private String descripcion;
    private long valor;
    private Usuario usuario;
    private Dependencia PK_idDependencia;
    private String estado;
    private String imagen1;
    private String imagen2;
    private Observacion informacion;

    // Constructor vacío
    public Bien() {
    }

    // Constructor
    public Bien(int idBien, long codigo, String nombre, int placa, String descripcion, long valor, Usuario usuario, Dependencia PK_idDependencia, String estado, String imagen1, String imagen2, Observacion informacion) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dependencia getDependencia() {
        return PK_idDependencia;
    }

    //public int getDependencia() {
      //  return PK_idDependencia.getPK_idDependencia();
    //}


    public void setDependencia(Dependencia PK_idDependencia) {
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

    public Observacion getObservacion() {
        return informacion;
    }

    public void setObservacion(Observacion informacion) {
        this.informacion = informacion;
    }
}
