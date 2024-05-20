package Logica;

import UI_UX.Aplicacion;

public class Sesion {

    public static Cliente user;
    public static boolean esAnfitrion;

    public static void nuevaSesion(Cliente cliente) {
        user = cliente;
        esAnfitrion = user instanceof Anfitrion;
    }

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
     *
     * @param correo
     * @param clave
     * @return int referente a qué tipo de "persona" es.
     */
    public static int iniciarSesion(String correo, String clave) {
        boolean isHost = false;
        if (correo.equals("admin@java.bnb") && clave.equals("admin")) {
            System.out.println("Sesión iniciada como administrador");
            return 1;
        }
        for (Cliente cliente : JavaBNB.getClientes()) {
            System.out.println(cliente.toString());
            isHost = (cliente instanceof Anfitrion);

            if (cliente.getCorreo().equals(correo.toLowerCase()) && cliente.getClave().equals(clave)) {
                Aplicacion.sesion.nuevaSesion(cliente);
                System.out.println("Sesión iniciada por " + Aplicacion.sesion.user);
                return isHost ? 3 : 2;
            }
        }
        return 0;
    }

    public static void cerrarSesion() {
        user = null;
        esAnfitrion = false;
    }

    public static void registrarCliente(Cliente cliente) {
        if (Validacion.comprobarExistenciaCliente(cliente.getCorreo(), cliente.getDni(), cliente.getTelefono())) {
            return;
        }
        JavaBNB.getClientes().add(cliente);
        Aplicacion.sesion.nuevaSesion(cliente);
        System.out.println(cliente.toString());
    }

    public static boolean hacerVipSiPromocodeValido(String promocode) {
        if (user instanceof Particular && "JAVABNB2024".equals(promocode)) {
            ((Particular) user).setVip(true);
            return true;
        }
        return false;
    }

    // Nuevo método para verificar si el usuario actual es VIP
    public static boolean esUsuarioVip() {
        if (user instanceof Particular) {
            return ((Particular) user).isVip();
        }
        return false;
    }
}
