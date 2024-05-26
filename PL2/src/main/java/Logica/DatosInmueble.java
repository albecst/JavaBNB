package Logica;

import java.io.Serializable;

/**
 * La clase DatosInmueble representa los datos básicos de un inmueble, incluyendo
 * la cantidad de huéspedes que puede alojar, el número de habitaciones, camas y baños.
 */
public class DatosInmueble implements Serializable {
    
    private int maxHuespedes;
    private int habitaciones;
    private int camas;
    private int baños;

    /**
     * Constructor de la clase DatosInmueble.
     *
     * @param maxHuespedes la cantidad máxima de huéspedes que pueden alojarse en el inmueble
     * @param habitaciones el número de habitaciones en el inmueble
     * @param camas el número de camas en el inmueble
     * @param baños el número de baños en el inmueble
     */
    public DatosInmueble(int maxHuespedes, int habitaciones, int camas, int baños) {
        this.maxHuespedes = maxHuespedes;
        this.habitaciones = habitaciones;
        this.camas = camas;
        this.baños = baños;
    }
    
    // Getters y Setters

    /**
     * Obtiene el valor de baños.
     *
     * @return el valor de baños
     */
    public int getBaños() {
        return baños;
    }

    /**
     * Establece el valor de baños.
     *
     * @param baños el nuevo valor de baños
     */
    public void setBaños(int baños) {
        this.baños = baños;
    }

    /**
     * Obtiene el valor de camas.
     *
     * @return el valor de camas
     */
    public int getCamas() {
        return camas;
    }

    /**
     * Establece el valor de camas.
     *
     * @param camas el nuevo valor de camas
     */
    public void setCamas(int camas) {
        this.camas = camas;
    }

    /**
     * Obtiene el valor de habitaciones.
     *
     * @return el valor de habitaciones
     */
    public int getHabitaciones() {
        return habitaciones;
    }

    /**
     * Establece el valor de habitaciones.
     *
     * @param habitaciones el nuevo valor de habitaciones
     */
    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    /**
     * Obtiene el valor de maxHuespedes.
     *
     * @return el valor de maxHuespedes
     */
    public int getMaxHuespedes() {
        return maxHuespedes;
    }

    /**
     * Establece el valor de maxHuespedes.
     *
     * @param maxHuespedes el nuevo valor de maxHuespedes
     */
    public void setMaxHuespedes(int maxHuespedes) {
        this.maxHuespedes = maxHuespedes;
    }

    /**
     * Devuelve una representación en cadena de los datos del inmueble.
     *
     * @return una cadena que representa los datos del inmueble
     */
    @Override
    public String toString() {
        return "Como máximo " + maxHuespedes + " huéspedes se pueden alojar en este inmueble. El inmueble tiene " + habitaciones + " habitaciones, " + camas + " camas, y " + baños + " baños.";
    }
}
