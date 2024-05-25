package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Inmueble implements Serializable {

    private String titulo;
    private Direccion direccion;
    private DatosInmueble datosInmueble;
    private String tipo; // Casa o apartamento
    private double precioNoche;
    private String servicios;
    private double calificacion;
    private String fotografia;
    private String descripcion;
    private int valoraciones;
    private ArrayList<Reserva> reservas; // ArrayList para almacenar las reservas asociadas al inmueble
    //private Cliente cliente;
    private Anfitrion anfitrion;

    // Constructor
    public Inmueble(String titulo, String descripcion, Direccion direccion, DatosInmueble datosInmueble, String tipo, double precioNoche, String fotografia, String servicios, Anfitrion anfitrion) {
        this.titulo = titulo;
        this.direccion = direccion;
        this.datosInmueble = datosInmueble;
        this.tipo = tipo;
        this.precioNoche = precioNoche;
        this.calificacion = 0;
        this.fotografia = fotografia;
        this.servicios = servicios;
        this.descripcion = descripcion;
        this.valoraciones = 0;
        this.reservas = new ArrayList<>(); // Inicialización del ArrayList de reservas
        this.anfitrion = anfitrion;
    }

    // Método para agregar una reserva al inmueble
    public void agregarReserva(Reserva reserva) {
        if (!reservas.contains(reserva)) {
            reservas.add(reserva);
            JavaBNB.guardarDatos();
        }
    }

    // Método para eliminar una reserva del inmueble
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        JavaBNB.guardarDatos();
    }

    // Método para obtener todas las reservas asociadas al inmueble
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Método para comprobar si un inmueble está disponible en unas fechas
     * concretas.
     *
     * @param fechaEntrada Fecha de entrada.
     * @param fechaSalida Fecha de salida.
     * @return True si está disponible, false si no lo está.
     */
    public boolean estaDisponible2(LocalDate fechaEntrada, LocalDate fechaSalida) {
        if (fechaEntrada.isAfter(fechaSalida) || fechaEntrada.isBefore(LocalDate.now()) || fechaSalida.isBefore(LocalDate.now())) {
            return false;
        }

        for (Reserva reserva : reservas) {
            if (!comprobarFechasLibres(reserva, fechaEntrada, fechaSalida)) {
                return false;
            }
        }
        return true;
    }

    private boolean comprobarFechasLibres2(Reserva reserva, LocalDate fechaEntrada, LocalDate fechaSalida) {
        if (reserva.getFechaInicio().isBefore(fechaSalida) && reserva.getFechaFin().isAfter(fechaEntrada)) {
            return false;
        }
        return true;
    }

    public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
        boolean disponible = true;
        if (fechaEntrada.isAfter(fechaSalida) || fechaEntrada.isBefore(LocalDate.now()) || fechaSalida.isBefore(LocalDate.now())) {
            disponible = false;
        }
        for (Reserva reserva : this.reservas) {
            if (this.equals(reserva.getInmueble()) && (!comprobarFechasLibres(reserva, fechaEntrada, fechaSalida))) {
                disponible = false;
            }

        }

        return disponible;
    }

    //TODO: si son iguales no lo pilla. 
    public boolean comprobarFechasLibres(Reserva reserva, LocalDate fechaEntrada, LocalDate fechaSalida) {

        boolean estalibre = true;

        if (reserva.getFechaInicio().isBefore(fechaSalida) && reserva.getFechaFin().isAfter(fechaSalida)) //si quiero reservar para fecha final ya pillada
        {
            estalibre = false;
        }
        if (reserva.getFechaInicio().equals(fechaEntrada) || reserva.getFechaInicio().equals(fechaSalida) || reserva.getFechaFin().equals(fechaEntrada) || reserva.getFechaFin().equals(fechaSalida)) {
            estalibre = false;
        }
        if (reserva.getFechaInicio().isBefore(fechaEntrada) && reserva.getFechaFin().isAfter(fechaEntrada)) //reservar para fecha inicial ya pillada
        {
            estalibre = false;
        }
        if (reserva.getFechaInicio().isBefore(fechaSalida) && reserva.getFechaFin().isAfter(fechaSalida)) { //si quiero reservar para fecha final ya pillada
            estalibre = false;
        }
        if (reserva.getFechaInicio().isAfter(fechaEntrada) && reserva.getFechaFin().isBefore(fechaSalida)) //reservar para periodo de tiempo con reserva en medio
        {
            estalibre = false;
        }
        if (reserva.getFechaInicio().isBefore(fechaEntrada) && reserva.getFechaFin().isAfter(fechaEntrada)) { //reservar para fecha inicial ya pillada
            estalibre = false;
        }
        if (reserva.getFechaInicio().isAfter(fechaEntrada) && reserva.getFechaFin().isBefore(fechaSalida)) { //reservar para periodo de tiempo con reserva en medio
            estalibre = false;
        }

        return estalibre;
    }

    // Getters & Setters
    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
        JavaBNB.guardarDatos();
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double nota) {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5.");
        } else {
            this.calificacion = (this.calificacion * this.valoraciones + nota) / (this.valoraciones + 1);
            this.valoraciones++;
            JavaBNB.guardarDatos();
        }
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
        JavaBNB.guardarDatos();
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
        JavaBNB.guardarDatos();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        JavaBNB.guardarDatos();
    }

    public DatosInmueble getDatosInmueble() {
        return datosInmueble;
    }

    public void setDatosInmueble(DatosInmueble datosInmueble) {
        this.datosInmueble = datosInmueble;
        JavaBNB.guardarDatos();
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        JavaBNB.guardarDatos();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        JavaBNB.guardarDatos();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        JavaBNB.guardarDatos();
    }

    public Cliente getCliente() {
        return anfitrion;
    }

    public void setCliente(Anfitrion anfitrion) {
        this.anfitrion = anfitrion;
        JavaBNB.guardarDatos();
    }

    public int getValoraciones() {
        return valoraciones;
    }

    @Override
    public String toString() {
        return "Inmueble{" + ", titulo=" + titulo + ", direccion=" + direccion + ", datosInmueble=" + datosInmueble + ", tipo=" + tipo + ", precioNoche=" + precioNoche + ", servicios=" + servicios + ", calificacion=" + calificacion + ", fotografia=" + fotografia + ", descripcion=" + descripcion + '}';
    }
}
