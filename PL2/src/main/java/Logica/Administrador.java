package Logica;

import java.util.ArrayList;



public class Administrador {   //antes:   public record Administrador(String correo, String clave) {

    /**
     * Métodos para consultar los usuarios.
     */
    public static void imprimirParticulares() {
        
    }

    public static void imprimirHosts() {
        
    }

    /**
     * Método para gestionar los inmuebles.
     */
    public void gestionarInmuebles() {
        //Y aquí el de gestionar inmuebles.
    }

    public void gestionarReservas() {
        //Y aquí el de gestionar reservas.

    }
}

/**
 * Recordemos que en un record, los atributos son finales y no se pueden
 * modificar (por eso no hay setters), y Los métodos getter en una clase record
 * en Java no se definen explícitamente en el código, son proporcionados
 * automáticamente por el compilador.
 */
