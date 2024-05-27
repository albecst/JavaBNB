package Logica;

import UI_UX.App;

/**
 * La clase Sesion maneja la gestión de la sesión del usuario activo en la aplicación JavaBNB.
 * Contiene métodos que permiten iniciar y cerrar sesión, actualizar información del usuario y registrar nuevos clientes.
 */
public class Sesion {

    public static Cliente user;
    public static boolean esAnfitrion;

    /**
     * Inicia una nueva sesión con el cliente proporcionado.
     *
     * @param cliente el cliente que inicia la sesión
     */
    public static void nuevaSesion(Cliente cliente) {
        if (cliente != null) {
            user = cliente;
            esAnfitrion = user instanceof Anfitrion;
        } else {
            user = null;
            esAnfitrion = false;
        }
    }

    /**
     * Devuelve el cliente actual de la sesión.
     *
     * @return el cliente actual
     */
    public static Cliente devolverCliente() {
        return user;
    }

    /**
     * Actualiza el nombre del usuario en la sesión actual.
     *
     * @param data el nuevo nombre
     */
    public static void updatenombre(String data) {
        if (!Validacion.validarNombre(data)) {
            return;
        }
        for (Cliente cliente : JavaBNB.getClientes()) {
            if (cliente.getDni().equals(user.getDni())) {
                cliente.setNombre(data);
                return;
            }
        }
    }

    /**
     * Inicia sesión con el correo y la clave proporcionados.
     *
     * @param correo el correo del cliente.
     * @param clave la clave del cliente.
     * @return un entero que indica el tipo de usuario (1: administrador, 2: cliente, 3: anfitrión, 0: no encontrado).
     */
    public static int iniciarSesion(String correo, String clave) {
        boolean isHost = false;
        if (correo.equals("admin@java.bnb") && clave.equals("admin")) {
            System.out.println("Sesión iniciada como administrador");
            return 1;
        }
        for (Cliente cliente : JavaBNB.getClientes()) {
            System.out.println("Revisando cliente: " + cliente);
            isHost = (cliente instanceof Anfitrion);

            if (cliente.getCorreo().equals(correo.toLowerCase()) && cliente.getClave().equals(clave)) {
                App.sesion.nuevaSesion(cliente);
                System.out.println("Sesión iniciada por " + App.sesion.user);
                return isHost ? 3 : 2;
            }
        }
        return 0;
    }

    /**
     * Cierra la sesión actual sustituyendo los valores por "null".
     */
    public static void cerrarSesion() {
        user = null;
        esAnfitrion = false;
    }

    /**
     * Registra un nuevo cliente en la aplicación si los datos son válidos y abre una nueva sesión.
     *
     * @param cliente el nuevo cliente a registrar.
     */
    public static void registrarCliente(Cliente cliente) {
        if (Validacion.comprobarExistenciaCliente(cliente.getCorreo(), cliente.getDni(), cliente.getTelefono())) {
            return;
        }
        JavaBNB.getClientes().add(cliente);
        App.sesion.nuevaSesion(cliente);
        System.out.println("Nuevo cliente registrado: "+cliente.toString());
    }

    /**
     * Hace VIP al usuario actual si el código promocional es válido.
     *
     * @param promocode el código promocional.
     * @return true si el código es válido, en cuyo caso el usuario se convierte en VIP, y false en caso contrario.
     */
    public static boolean hacerVipSiPromocodeValido(String promocode) {
        if (user instanceof Particular && "JAVABNB2024".equals(promocode)) {
            ((Particular) user).setVip(true);
            return true;
        }
        return false;
    }

    /**
     * Verifica si el usuario actual es VIP.
     *
     * @return true si el usuario es VIP, false en caso contrario
     */
    public static boolean esUsuarioVip() {
        if (user instanceof Particular) {
            return ((Particular) user).isVip();
        }
        return false;
    }
}
