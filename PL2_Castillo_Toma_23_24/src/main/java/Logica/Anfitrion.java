/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.time.LocalDateTime;

/**
 *
 * @author alber
 */
public class Anfitrion extends Cliente {
   
    //Atributos
    private LocalDateTime fechaRegistro;
    private boolean superanfitrion;
    
    //Constructor
    public Anfitrion(LocalDateTime fechaRegistro, boolean superanfitrion, String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.fechaRegistro = fechaRegistro;
        this.superanfitrion = superanfitrion;
    }
    
    //Métodos
    /*Falta añadir si es Superanfitrión o no (si la media de las reseñas de sus inmuebles es superior a 4).
    
    */
    
    //G&S
    /**
     * Get the value of superanfitrion
     *
     * @return the value of superanfitrion
     */
    public boolean isSuperanfitrion() {
        return superanfitrion;
    }

    /**
     * Set the value of superanfitrion
     *
     * @param superanfitrion new value of superanfitrion
     */
    public void setSuperanfitrion(boolean superanfitrion) {
        this.superanfitrion = superanfitrion;
    }

    
    /**
     * Get the value of fechaRegistro
     *
     * @return the value of fechaRegistro
     */
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Set the value of fechaRegistro
     *
     * @param fechaRegistro new value of fechaRegistro
     */
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
 
}
