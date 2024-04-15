/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class Inmueble {
    
    //Atributos
    private String titulo;
    private Direccion direccion;
    private DatosInmueble datosInmueble;
    private String tipo; //Casa o apartamento
    private double precioNoche;
    private ArrayList <String> servicios = new ArrayList<>();
    private double calificacion; //Si es menor a 0 o mayor a 5 error
    private String fotografia; //Será un String, porque la fotografía no deja de ser una ruta dentro de nuestro dispositivo.
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    
    //Constructor
    public Inmueble(String titulo, Direccion direccion, DatosInmueble datosInmueble, String tipo, double precioNoche, double calificacion, String fotografia, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        this.titulo = titulo;
        this.direccion = direccion;
        this.datosInmueble = datosInmueble;
        this.tipo = tipo;
        this.precioNoche = precioNoche;
        this.calificacion = calificacion;
        this.fotografia = fotografia;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
    }

    
    //Métodos
    /*1º: Método para añadir los servicios al ArrayList.
      2º: Método para añadir los servicios al ArrayList.*/
    public void añadirServicio(String servicio){
        if(!servicios.contains(servicio)){
            servicios.add(servicio);
        }
    }
    
    public void eliminarServicio(String servicio){
        if(servicios.contains(servicio)){
            servicios.remove(servicio);
        }
    }
    
    /*3º: Método para ver si está disponible en esas fechas */
    public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
        // Supongamos que las fechas de reserva están almacenadas en dos atributos de tipo LocalDate
        // llamados fechaInicioReserva y fechaFinReserva.
        // Si no hay reservas que se superpongan con las fechas proporcionadas, el inmueble está disponible.
        return fechaInicioReserva.isAfter(fechaSalida) || fechaFinReserva.isBefore(fechaEntrada);
    }

    
    
    
    
    
    
    
    
    //G&S
    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }
    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        this.fechaFinReserva = fechaFinReserva;
    }

    /**
     * Get the value of fotografía
     *
     * @return the value of fotografía
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * Set the value of fotografía
     *
     * @param fotografia new value of fotografía
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    
    /**
     * Get the value of calificacion
     *
     * @return the value of calificacion
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Set the value of calificacion
     *
     * @param calificacion new value of calificacion
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    
    /**
     * Get the value of servicios
     *
     * @return the value of servicios
     */
    public ArrayList<String> getServicios() {
        return servicios;
    }

    /**
     * Get the value of precioNoche
     *
     * @return the value of precioNoche
     */
    public double getPrecioNoche() {
        return precioNoche;
    }

    /**
     * Set the value of precioNoche
     *
     * @param precioNoche new value of precioNoche
     */
    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }


    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    /**
     * Get the value of datosInmueble
     *
     * @return the value of datosInmueble
     */
    public DatosInmueble getDatosInmueble() {
        return datosInmueble;
    }

    /**
     * Set the value of datosInmueble
     *
     * @param datosInmueble new value of datosInmueble
     */
    public void setDatosInmueble(DatosInmueble datosInmueble) {
        this.datosInmueble = datosInmueble;
    }


    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    /**
     * Get the value of titulo
     *
     * @return the value of titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Set the value of titulo
     *
     * @param titulo new value of titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
