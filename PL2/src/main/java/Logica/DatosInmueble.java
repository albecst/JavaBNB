package Logica;

import java.io.Serializable;

/**
 * La clase DatosInmueble representa los datos básicos de un inmueble,
 * incluyendo la cantidad de huéspedes que puede alojar, el número de
 * habitaciones, camas y baños.
 */
public class DatosInmueble implements Serializable {

    private int maxHuespedes;
    private int habitaciones;
    private int camas;
    private int baños;

    /**
     * Constructor de la clase DatosInmueble.
     *
     * @param maxHuespedes la cantidad máxima de huéspedes que pueden alojarse
     * en el inmueble
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
    public int getBaños() {
        return baños;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getMaxHuespedes() {
        return maxHuespedes;
    }

    public void setMaxHuespedes(int maxHuespedes) {
        this.maxHuespedes = maxHuespedes;
    }

    @Override
    public String toString() {
        return "Como máximo " + maxHuespedes + " huéspedes se pueden alojar en este inmueble. El inmueble tiene " + habitaciones + " habitaciones, " + camas + " camas, y " + baños + " baños.";
    }
}
