package Logica;

import java.io.Serializable;

/**
 *
 * @author alber
 */
public class DatosInmueble implements Serializable {
    
    //Atributos
    private int maxHuespedes;
    private int habitaciones;
    private int camas;
    private int baños;

    /**
     * Constructor de la clase DatosInmueble
     * @param maxHuespedes
     * @param habitaciones
     * @param camas
     * @param baños
     */
    public DatosInmueble(int maxHuespedes, int habitaciones, int camas, int baños) {
        this.maxHuespedes = maxHuespedes;
        this.habitaciones = habitaciones;
        this.camas = camas;
        this.baños = baños;
    }
    
    //G&S
    /**
     * Get the value of baños
     *
     * @return the value of baños
     */
    public int getBaños() {
        return baños;
    }

    /**
     * Set the value of baños
     *
     * @param baños new value of baños
     */
    public void setBaños(int baños) {
        this.baños = baños;
    }


    /**
     * Get the value of camas
     *
     * @return the value of camas
     */
    public int getCamas() {
        return camas;
    }

    /**
     * Set the value of camas
     *
     * @param camas new value of camas
     */
    public void setCamas(int camas) {
        this.camas = camas;
    }


    /**
     * Get the value of habitaciones
     *
     * @return the value of habitaciones
     */
    public int getHabitaciones() {
        return habitaciones;
    }

    /**
     * Set the value of habitaciones
     *
     * @param habitaciones new value of habitaciones
     */
    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }


    /**
     * Get the value of maxHuespedes
     *
     * @return the value of maxHuespedes
     */
    public int getMaxHuespedes() {
        return maxHuespedes;
    }

    /**
     * Set the value of maxHuespedes
     *
     * @param maxHuespedes new value of maxHuespedes
     */
    public void setMaxHuespedes(int maxHuespedes) {
        this.maxHuespedes = maxHuespedes;
    }

}
