package Logica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class InicioSesion implements Serializable {

    //Atributos
    //public static Administrador administrador; //no deberia estar 
    //public static Anfitrion anfitrion;
    //public static Particular particular;
    //public static ArrayList<Cliente> clientes;
    public static ArrayList<Anfitrion> anfitriones;
    public static ArrayList<Particular> particulares;

    /**
     * Constructor de la clase InicioSesion.
     *
     * @param administrador
     *
     * @param anfitrion
     * @param particular
     */
    /**
     * public InicioSesion() { this.clientes = new ArrayList<>();
     * this.anfitriones = new ArrayList<>(); this.particulares = new
     * ArrayList<>(); }
     *
     */
    public boolean comprobarUsuario(String dni) {
        boolean particularExiste = false;
        boolean anfitrionExiste = false;
        for (Particular particular : particulares) {
            if (particular.getDni().equals(dni)) {
                particularExiste = true;
            }
        }
        for (Anfitrion anfitrion : anfitriones) {
            if (anfitrion.getDni().equals(dni)) {
                anfitrionExiste = true;
            }
        }
        return anfitrionExiste || particularExiste;
    }

    public static int iniciarSesion(String correo, String clave) {
        int tipo = 0;
        if (correo.equals("admin@javabnb.com") && clave.equals("admin")) {    //a administrador se deberia poder acceder sin instanciar
            System.out.println("Sesión iniciada como administrador");
            tipo = 1;
        } else {
            for (Particular particular : particulares) {
                if (particular.getCorreo().equals(correo) && particular.getClave().equals(clave)) {
                    System.out.println("Sesión iniciada como cliente");
                    tipo = 2;
                }
            }
            for (Anfitrion anfitrion : anfitriones) {
                if (anfitrion.getCorreo().equals(correo) && anfitrion.getClave().equals(clave)) {
                    System.out.println("Sesión iniciada como anfitrion");
                    tipo = 3;
                }
            }
        }
        return tipo;
    }

    /**
     * CODIGO ANTIGUO: else if (anfitrion.getCorreo().equals(correo) &&
     * anfitrion.getClave().equals(clave)) { System.out.println("Sesión iniciada
     * como anfitrión"); } else { for (Cliente cliente : clientes) { if
     * (cliente.getCorreo().equals(correo) && cliente.getClave().equals(clave))
     * { System.out.println("Sesión iniciada como cliente"); } } }
     */
}
