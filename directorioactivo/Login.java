/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.acceso;

import co.gov.invima.utils.PropertiesReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ldap.LdapContext;

/**
 *
 * @author mgualdrond
 */
public class Login {
    
    private static Logger log = Logger.getLogger(Login.class.getName());
    
    public boolean acceso(String usuario, String clave) {
        boolean b = false;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.cadenaAD");
            LdapContext ctx = ActiveDirectory.getConnection(usuario, clave, pr.getProperty("dominio"), pr.getProperty("servidor"));
            
            User u = ActiveDirectory.getUser(usuario, ctx);
            
            ctx.close();
            if (u != null) {
                //System.out.println("username" + u.getUserPrincipal() + "miembro: " + u.getMember().get(0));
                b = true;
                // u.getMember().
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al validar usuario", e);
        } finally {
            return b;
        }
    }
    
    public User obtenerUsuario(String usuario, String clave) {
        User b = null;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.cadenaAD");
            LdapContext ctx = ActiveDirectory.getConnection(usuario, clave, pr.getProperty("dominio"), pr.getProperty("servidor"));
            
            User u = ActiveDirectory.getUser(usuario, ctx);
            b=u;
            ctx.close();
           
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener usuario", e);
        } finally {
            return b;
        }
    }
    
    public List<User> listarUsuarios() {
        List<User> users1 = null;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.cadenaAD");
            
            LdapContext ctx = ActiveDirectory.getConnection(pr.getProperty("usuarioRiesgos"), pr.getProperty("claveRiesgos"), pr.getProperty("dominio"), pr.getProperty("servidor"));
            User[] users = ActiveDirectory.getUsers(ctx);
            users1 = Arrays.asList(users);
            ctx.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener lista de usuarios", e);
        } finally {
            return users1;
        }
    }
    
    public User obtenerUsuarioActivoOtroGrupo(String usuario){
        User user = null;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.cadenaAD");
            
            LdapContext ctx = ActiveDirectory.getConnection(pr.getProperty("usuarioRiesgos"), pr.getProperty("claveRiesgos"), pr.getProperty("dominio"), pr.getProperty("servidor"));
            user = ActiveDirectory.getUserActive(usuario, ctx);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener  usuario Avtivo", e);
        } finally {
            return user;
        }
    }
    
    public List<User> listarUsuariosInactivos() {
        List<User> users1 = null;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.cadenaAD");
            
            LdapContext ctx = ActiveDirectory.getConnection(pr.getProperty("usuarioRiesgos"), pr.getProperty("claveRiesgos"), pr.getProperty("dominio"), pr.getProperty("servidor"));
            User[] users = ActiveDirectory.getUsersInactive(ctx);
            users1 = Arrays.asList(users);
            ctx.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener lista de usuarios", e);
        } finally {
            return users1;
        }
    }
}
