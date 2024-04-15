package Logica;

/**
 * @param correo Atributos
 * @author alber
 */
public record Administrador(String correo, String clave) {

    /**
     * Método para gestionar los usuarios.
     */
    public void gestionarUsuarios() {
        //Aquí falta el código para gestionar los usuarios.
    }

    /**
     * Método para gestionar los inmuebles.
     */
    public void gestionarInmuebles() {
        //Y aquí el de gestionar inmuebles.
    }
}

/** Recordemos que en un record, los atributos son finales y no se pueden modificar (por eso no hay setters), y Los métodos getter en una clase record en Java no se definen explícitamente en el código, son proporcionados automáticamente por el compilador. */
