package Logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserva implements Serializable {

    //Atributos  TODO: fechainicio y fechafin ya están dentro de inmueble
    private Inmueble inmueble;
    private Particular particular;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaReserva;

    /**
     * Constructor de la clase Reserva.
     *
     * @param particular Particular que realiza la reserva.
     * @param inmueble Inmueble que se reserva.
     * @param fechaInicio Fecha de inicio de la reserva.
     * @param fechaFin Fecha de fin de la reserva. 
     * fechaReserva será la fecha del instante en el que se realice la reserva
     */
    public Reserva(Particular particular, Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin) {
        this.inmueble = inmueble;
        this.particular = particular;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaReserva = LocalDate.now();
        
    }

    /**
     * Calcula el precio total de una reserva.
     */
    public double calcularPrecioTotal() {
        long diasEstancia = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        double costoTotal = diasEstancia * inmueble.getPrecioNoche();
        if (particular.isVip()) {
            costoTotal *= 0.9;
        }
        return costoTotal;
    }


    /**
     * Getters & Setters
     *
     * Get the value of inmueble
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

    public void generaFicha() throws IOException {

        String nombreFichero = this.particular.getNombre() + this.inmueble.getTitulo();
        PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nombreFichero + ".txt")));
        DateTimeFormatter formatoCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fr = fechaReserva.format(formatoCorto);

        salida.println("-------------------------------- Reserva --------------------------------");
        salida.println("\n");
        salida.println("DNI: " + this.particular.getDni());
        salida.println("\n");
        salida.println("Nombre: " + this.particular.getNombre());
        salida.println("\n");
        salida.println("Fecha en que se hizo la reserva: " + fr);
        salida.println("\n");
        salida.println("Dirección del inmueble: " + this.inmueble.getDireccion().toString());
        salida.println("\n");
        salida.println("Tipo de inmueble: " + this.inmueble.getTipo());
        salida.println("\n");
        salida.println("Título del inmueble: " + this.inmueble.getTitulo());
        salida.println("\n");
        salida.println("Fecha de llegada: " + fechaInicio);
        salida.println("\n");
        salida.println("Fecha de salida: " + fechaFin);
        salida.println("\n");
        salida.println("Precio: " + calcularPrecioTotal());
        salida.println("\n");
        salida.println("-------------------------------------------------------------------------------");
        salida.close();
    }

    
}
