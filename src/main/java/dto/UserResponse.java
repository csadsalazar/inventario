package dto;

import java.util.List;

public class UserResponse {
 
    private String correo;
    private String nombreCompleto;
    private List<String> grupos;
    private String nombreUsuario;


    public UserResponse(String correo, String nombreCompleto, List<String> grupos, String nombreUsuario) {
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.grupos = grupos;
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    
    public List<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "UserResponse [correo=" + correo + ", nombreCompleto=" + nombreCompleto + ", grupos=" + grupos
                + ", nombreUsuario=" + nombreUsuario + "]";
    }


    
}