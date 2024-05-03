package Logica;

public class Sesion {

    public static Cliente user;
    public static Particular particular;
    public static boolean esAnfitrion;

    public Sesion(Cliente user) {
        this.user = user;
        this.esAnfitrion = user instanceof Anfitrion;
        this.particular = null; // inicializar particular como null
    }
   
    public static Sesion nuevaSesion(Cliente cliente) {
        return new Sesion(cliente);
    }

    public static Sesion nuevaSesionParticular(Particular particular) {
        // Crear una nueva sesión con el usuario particular
        Sesion sesion = new Sesion(particular);
        // Asignar el usuario particular a la sesión
        sesion.particular = particular;
        return sesion;
    }

    public static void updatenombre(String data) {
        boolean valido = Validacion.validarNombre(data);
        if (valido) {
            for (Cliente cliente : JavaBNB.clientes) {
                if (cliente.getDni().equals(user.getDni())) {
                    cliente.setNombre(data);
                }
            }
        }
    }
}
