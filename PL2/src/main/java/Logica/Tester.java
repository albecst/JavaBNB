/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Logica;

import java.time.LocalDate;

/**
 *
 * @author alber
 */
public class Tester {

    public static void main(String[] args) {
        DatosInmueble di1= new DatosInmueble(3,3,3,3);
        LocalDate inicioI1= LocalDate.of(2001, 1, 1);
        LocalDate finI1= LocalDate.of(2001, 2, 2);
        Direccion dir1= new Direccion("calle",1,"string???","madrid");
        Inmueble i1= new Inmueble("titulo",dir1, di1 , "tipo", 200.1, 3.5,"foto1",inicioI1,finI1);
        Inmueble i2= new Inmueble("titulo",dir1, di1 , "tipo", 200.1, 5,"foto1",inicioI1,finI1);
        Anfitrion a1=new Anfitrion(inicioI1,"dni","nombre","correo","clave","tlf");
         
        a1.addInmuebles(i1);
        //a1.addInmuebles(i2);
        System.out.println(a1.isSuperAnfitrion());
        
    }
}
