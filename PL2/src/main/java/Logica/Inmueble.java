package Logica;

import java.io.Serializable;
import java.time.LocalDate;

public class Inmueble implements Serializable {

    private String titulo;
    private Direccion direccion;
    private DatosInmueble datosInmueble;
    private String tipo; //Casa o apartamento
    private double precioNoche;
    private String servicios;
    private double calificacion; //Si es menor a 0 o mayor a 5 error
    private String fotografia; //Será un String, porque la fotografía no deja de ser una ruta dentro de nuestro dispositivo.
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private String descripcion;

    //Constructor
    public Inmueble(String titulo, String descripcion, Direccion direccion, DatosInmueble datosInmueble, String tipo, double precioNoche, String fotografia, String servicios) {
        this.titulo = titulo;
        this.direccion = direccion;
        this.datosInmueble = datosInmueble;
        this.tipo = tipo;
        this.precioNoche = precioNoche;
        this.calificacion = 0;
        this.fotografia = fotografia;
        this.servicios = servicios;
        this.descripcion = descripcion;
    }

    /**
     * Método para comprobar si un inmueble está disponible en unas fechas
     * concretas.
     *
     * @param fechaEntrada Fecha de entrada.
     * @param fechaSalida Fecha de salida.
     * @return True si está disponible, false si no lo está.
     */
    public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return fechaInicioReserva.isAfter(fechaSalida) || fechaFinReserva.isBefore(fechaEntrada);
    }

    /**
     * Getters & Setters /
     *
     * /**
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
        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5.");
        } else {
            this.calificacion = calificacion;
        }
    }

    /**
     * Get the value of servicios
     *
     * @return the value of servicios
     */
    public String getServicios() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Inmueble{" + "titulo=" + titulo + ", direccion=" + direccion + ", datosInmueble=" + datosInmueble + ", tipo=" + tipo + ", precioNoche=" + precioNoche + ", servicios=" + servicios + ", calificacion=" + calificacion + ", fotografia=" + fotografia + ", fechaFinReserva=" + fechaFinReserva + ", descripcion=" + descripcion + '}';
    }
    


}
