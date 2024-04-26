package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class InicioSesion implements Serializable {

    //Atributos
    public static Administrador administrador;
    public static Anfitrion anfitrion;
    public static Particular particular;
    public static ArrayList<Cliente> clientes;
    public static ArrayList<Anfitrion> anfitriones;
    public static ArrayList<Particular> particulares;

    /**
     * Constructor de la clase InicioSesion.
     *
     * @param administrador
     * @param clientes
     * @param anfitriones
     * @param particulares
     */
    public InicioSesion() {
        this.clientes = new ArrayList<>();
        this.anfitriones = new ArrayList<>();
        this.particulares = new ArrayList<>();
    }

    public static boolean comprobarExistenciaCliente(String correo, String dni, String telefono) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                if (cliente.getCorreo().equals(correo)) {
                    System.out.println("Este correo ya existe");
                    return true;

                } else if (cliente.getDni().equals(dni)) {
                    System.out.println("Este DNI ya existe");
                    return true;

                } else if (cliente.getTelefono().equals(telefono)) {
                    System.out.println("Este teléfono ya existe");
                    return true;

                }

            }
        }
        return false;

    }

    //Aquí preguntaríamos al usuario si quiere registrarse como anfitrión o particular.
    public static void registrarAnfitrion(String dni, String nombre, String correo, String clave, String telefono) {
        if (comprobarExistenciaCliente(correo, dni, telefono)) {
            return;
        }
        Anfitrion nuevoAnfitrion = new Anfitrion(dni, nombre, correo, clave, telefono);
        clientes.add(nuevoAnfitrion);
        anfitriones.add(nuevoAnfitrion);
        
        for (Anfitrion anfitrion:anfitriones){
            System.out.println(anfitrion.toString());
    }
    }
    
    public static void registrarParticular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        for (Cliente cliente : clientes) {
            if (comprobarExistenciaCliente(correo, dni, telefono)) {
                return;
            }
            Particular nuevoParticular = new Particular(tarjetaCredito, vip, dni, nombre, correo, clave, telefono);
            clientes.add(nuevoParticular);
            particulares.add(nuevoParticular);
        }
    }

    public void iniciarSesion(String correo, String clave) {
        if (administrador.correo().equals(correo) && administrador.clave().equals(clave)) {
            System.out.println("Sesión iniciada como administrador");
        } else if (anfitrion.getCorreo().equals(correo) && anfitrion.getClave().equals(clave)) {
            System.out.println("Sesión iniciada como anfitrión");
        } else {
            for (Cliente cliente : clientes) {
                if (cliente.getCorreo().equals(correo) && cliente.getClave().equals(clave)) {
                    System.out.println("Sesión iniciada como cliente");
                }
            }
        }
    }

}
