package Logica;

import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class InicioSesion {

    //Atributos
    private Administrador administrador;
    private Anfitrion anfitrion;
    private ArrayList<Cliente> clientes;


    /**
     * Constructor
     *
     * @param administrador
     * @param anfitrion
     * @param cliente
     */
    public InicioSesion(Administrador administrador, Anfitrion anfitrion, Cliente cliente) {
        this.administrador = administrador;
        this.anfitrion = anfitrion;
        this.clientes = new ArrayList<>();
    }

    public void registrarCliente(String dni, String nombre, String correo, String clave, String telefono) {
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                System.out.println("Este correo ya existe");
                return;
            }
            else if (cliente.getDni().equals(dni)){
                System.out.println("Este DNI ya existe");
                return;
            }
            else if (cliente.getTelefono().equals(telefono)){
                System.out.println("Este teléfono ya existe");
                return;
            }
        }
        Cliente nuevoCliente = new Cliente(dni, nombre, correo, clave, telefono);
        clientes.add(nuevoCliente);
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

