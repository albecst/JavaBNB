/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Logica.Anfitrion;
import Logica.Cliente;
import java.util.ArrayList;

/**
 *
 * @author cristina
 */
public class Sesion {

    private static Cliente user;
    //private static boolean finished;
    private static boolean esAnfitrion;

    public Sesion(Cliente user) {
        this.user = user;
        this.esAnfitrion = user instanceof Anfitrion;
    }

    public static Cliente getUser() {
        return user;
    }

    public static void setUser(Cliente user) {
        Sesion.user = user;
    }

    public static boolean isEsAnfitrion() {
        return esAnfitrion;
    }

    public static void setEsAnfitrion(boolean esAnfitrion) {
        Sesion.esAnfitrion = esAnfitrion;
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
            if (esAnfitrion = true) {
                for (Anfitrion anfitrion : JavaBNB.anfitriones) {
                    if (anfitrion.getDni().equals(user.getDni())) {
                        anfitrion.setNombre(data);
                    }
                }
            } else {
                for (Particular particular : JavaBNB.particulares) {
                    if (particular.getDni().equals(user.getDni())) {
                        particular.setNombre(data);
                    }
                }
            }
        }
    }
}
