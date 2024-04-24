package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class InicioSesion implements Serializable{

    //Atributos
    private Administrador administrador;
    private Anfitrion anfitrion;
    private Particular particular;
    private ArrayList<Cliente> clientes;
    private ArrayList<Anfitrion> anfitriones;
    private ArrayList<Particular> particulares;

    /**
     * Constructor de la clase InicioSesion.
     *
     * @param administrador
     * @param anfitrion
     * @param particular
     * @param cliente
     */
    public InicioSesion(Administrador administrador, Anfitrion anfitrion, Cliente cliente, Particular particular) {
        this.administrador = administrador;
        this.anfitrion = anfitrion;
        this.particular = particular;
        this.clientes = new ArrayList<>();
    }

    public boolean comprobarExistenciaCliente (String correo, String dni, String telefono){
        boolean statement = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                System.out.println("Este correo ya existe");
                statement = true;
                break;
            }
            else if (cliente.getDni().equals(dni)){
                System.out.println("Este DNI ya existe");
                statement = true;
                break;
            }
            else if (cliente.getTelefono().equals(telefono)){
                System.out.println("Este teléfono ya existe");
                statement = true;
                break;
            }
        }
        return statement;
    }


    //Aquí preguntaríamos al usuario si quiere registrarse como anfitrión o particular.
    public void registrarAnfitrion(LocalDate fechaRegistro, String dni, String nombre, String correo, String clave, String telefono) {
        if (comprobarExistenciaCliente(correo, dni, telefono)) {
            return;
        }
        Anfitrion nuevoAnfitrion = new Anfitrion(fechaRegistro, dni, nombre, correo, clave, telefono);
        clientes.add(nuevoAnfitrion);
        anfitriones.add(nuevoAnfitrion);
    }

    public void registrarParticular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
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

