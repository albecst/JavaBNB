/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author alber
 */
public class Administrador {
   
    //Atributos
    private String correo;
    private String clave;

    //Constructor

    public Administrador(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    //Métodos
    public void gestionarUsuarios(){
        //Aquí falta el código para gestionar los usuarios.
    }
    
    public void gestionarInmuebles() {
        //Y aquí el de gestionar inmuebles.
    }
    
    //G&S
    /**
     * Get the value of clave
     *
     * @return the value of clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }


    /**
     * Get the value of correo
     *
     * @return the value of correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Set the value of correo
     *
     * @param correo new value of correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
