package Logica;

import java.io.Serializable;

/**
 *
 * @author alber
 */
public class Direccion implements Serializable {
   
    //Atributos
    private String calle; 
    private int numero;
    private String cp;
    private String ciudad;
    
    /**
     * Constructor de la clase Direccion
     * @param calle
     * @param numero
     * @param cp
     * @param ciudad
     */
    public Direccion(String calle, int numero, String cp, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
    }
    
    
    //G&S
    /**
     * Get the value of ciudad
     *
     * @return the value of ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Set the value of ciudad
     *
     * @param ciudad new value of ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
    /**
     * Get the value of cp
     *
     * @return the value of cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Set the value of cp
     *
     * @param cp new value of cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }


    /**
     * Get the value of numero
     *
     * @return the value of numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Set the value of numero
     *
     * @param numero new value of numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }


    /**
     * Get the value of calle
     *
     * @return the value of calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Set the value of calle
     *
     * @param calle new value of calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

}
