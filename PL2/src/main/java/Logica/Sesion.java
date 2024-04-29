/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

public class Sesion {

    public static Cliente user;
    //private static boolean finished;
    public static boolean esAnfitrion;

    public Sesion(Cliente user) {
        this.user = user;
        this.esAnfitrion = user instanceof Anfitrion;
    }

    /**
     * Genera una nueva sesión
     * @param cliente
     * @return una nueva sesión inicializada con "cliente" como "user".
     */
    public static Sesion nuevaSesion(Cliente cliente){
        return new Sesion(cliente);
    }
   
    //actualiza el nombre que se le introduzca en los arraylist de javabnb
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
