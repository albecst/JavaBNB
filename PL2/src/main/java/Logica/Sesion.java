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
        for (Cliente cliente : JavaBNB.clientes) {
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
     * @return
     */
    public static int iniciarSesion(String correo, String clave) {
        boolean isHost = false;
        if (correo.equals("admin@javabnb.com") && clave.equals("admin")) {
            System.out.println("Sesión iniciada como administrador");
            return 1;
        }
        for (Cliente cliente : JavaBNB.clientes) {
            System.out.println(cliente.toString());
            isHost = (cliente instanceof Anfitrion);
            if (cliente.getCorreo().equals(correo.toLowerCase()) && cliente.getClave().equals(clave)) {
                System.out.println("Sesión iniciada");
                Aplicacion.sesion.nuevaSesion(cliente);
                System.out.println(Aplicacion.sesion.user);
                return isHost ? 3 : 2;
            }
        }
        return 0;
    }
    
        public static void registrarCliente(Cliente cliente) {
        if (Validacion.comprobarExistenciaCliente(cliente.getCorreo(), cliente.getDni(), cliente.getTelefono())) {
            return;
        }
        JavaBNB.clientes.add(cliente);
        Aplicacion.sesion.nuevaSesion(cliente);
        System.out.println(cliente.toString());
    }
    
}
