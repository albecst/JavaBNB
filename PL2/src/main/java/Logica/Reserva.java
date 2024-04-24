package Logica;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {

    //Atributos
    private Inmueble inmueble;
    private Particular particular;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;


    /**
     * Constructor de la clase Reserva.
     *
     * @param particular  Particular que realiza la reserva.
     * @param inmueble    Inmueble que se reserva.
     * @param fechaInicio Fecha de inicio de la reserva.
     * @param fechaFin    Fecha de fin de la reserva.
     */
    public Reserva(Particular particular, Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin) {
        this.inmueble = inmueble;
        this.particular = particular;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Getters & Setters
     *
     * Get the value of fechaFin
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * Set the value of inmueble
     *
     * @param inmueble new value of inmueble
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * Get the value of particular
     *
     * @return the value of particular
     */
    public Particular getParticular() {
        return particular;
    }

    /**
     * Set the value of particular
     *
     * @param particular new value of particular
     */
    public void setParticular(Particular particular) {
        this.particular = particular;
    }

    /**
     * Get the value of fechaInicio
     *
     * @return the value of fechaInicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Set the value of fechaInicio
     *
     * @param fechaInicio new value of fechaInicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Get the value of fechaFin
     *
     * @return the value of fechaFin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Set the value of fechaFin
     *
     * @param fechaFin new value of fechaFin
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}