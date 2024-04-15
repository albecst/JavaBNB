/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
public class DatosInmueble {
    
    //Atributos
    private int maxHuespedes;
    private int habitaciones;
    private int camas;
    private int baños;

    //Constructor
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
