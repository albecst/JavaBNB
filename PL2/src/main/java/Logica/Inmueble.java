package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * La clase Inmueble representa un inmueble disponible para alquiler en la
 * plataforma JavaBNB. Incluye detalles como el título, la dirección, los datos
 * del inmueble, el tipo, el precio por noche, los servicios, la calificación,
 * la fotografía, la descripción, las valoraciones y las reservas asociadas.
 */
public class Inmueble implements Serializable {

    private String titulo;
    private Direccion direccion;
    private DatosInmueble datosInmueble;
    private String tipo; // Casa o apartamento
    private double precioNoche;
    private String servicios;
    private double calificacion; //calificación media del inmueble
    private String fotografia;
    private String descripcion;
    private int valoraciones;
    private ArrayList<Resenia> resenias; //Arraylist que guarda los comentarios y calificaciones individuales que se añadan
    private ArrayList<Reserva> reservas; // ArrayList para almacenar las reservas asociadas al inmueble
    private Anfitrion anfitrion;
    private static final long serialVersionUID = 6795168503584728871L; // Necesario para no tener problemas con la serialización de inmuebles

    /**
     * Constructor de la clase Inmueble.
     *
     * @param titulo el título del inmueble
     * @param descripcion la descripción del inmueble
     * @param direccion la dirección del inmueble
     * @param datosInmueble los datos del inmueble
     * @param tipo el tipo de inmueble (casa o apartamento)
     * @param precioNoche el precio por noche del inmueble
     * @param fotografia la fotografía del inmueble
     * @param servicios los servicios ofrecidos en el inmueble
     * @param anfitrion el anfitrión del inmueble
     */
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
        this.resenias = new ArrayList<>();
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
        // Verificar que las fechas no sean nulas
        if (fechaEntrada == null || fechaSalida == null) {
            return false;
        }

        // Verificar que la fecha de entrada sea anterior a la fecha de salida
        if (fechaEntrada.isAfter(fechaSalida)) {
            return false;
        }

        // Verificar que las fechas solicitadas estén dentro del rango correcto
        if (fechaEntrada.isBefore(LocalDate.now()) || fechaSalida.isBefore(LocalDate.now())) {
            return false;
        }

        // Verificar la disponibilidad en base a las reservas existentes
        for (Reserva reserva : reservas) {
            if (!comprobarFechasLibres(reserva, fechaEntrada, fechaSalida)) {
                return false; // No está disponible si hay conflicto de reserva
            }
        }

        // Si pasa todas las verificaciones, entonces está disponible
        return true;
    }

    /**
     * Verifica si las fechas solicitadas están libres de conflictos con una
     * reserva existente.
     *
     * @param reserva la reserva existente
     * @param fechaEntrada la fecha de entrada solicitada
     * @param fechaSalida la fecha de salida solicitada
     * @return true si las fechas están libres, false si hay un conflicto
     */
    public boolean comprobarFechasLibres(Reserva reserva, LocalDate fechaEntrada, LocalDate fechaSalida) {
        // Verificar si las fechas de inicio y fin de la reserva son iguales a las fechas solicitadas
        if (reserva.getFechaInicio().equals(fechaEntrada) || reserva.getFechaFin().equals(fechaSalida)) {
            return false; // No está libre si las fechas son iguales
        }

        // Verificar si la fecha de inicio de la reserva está dentro del rango de fechas solicitadas
        if (reserva.getFechaInicio().isAfter(fechaEntrada) && reserva.getFechaInicio().isBefore(fechaSalida)) {
            return false; // No está libre si la fecha de inicio de la reserva está dentro del rango de fechas solicitadas
        }

        // Verificar si la fecha de fin de la reserva está dentro del rango de fechas solicitadas
        if (reserva.getFechaFin().isAfter(fechaEntrada) && reserva.getFechaFin().isBefore(fechaSalida)) {
            return false; // No está libre si la fecha de fin de la reserva está dentro del rango de fechas solicitadas
        }

        // Verificar si la reserva cubre completamente el período solicitado
        if (reserva.getFechaInicio().isBefore(fechaEntrada) && reserva.getFechaFin().isAfter(fechaSalida)) {
            return false; // No está libre si la reserva cubre completamente el período solicitado
        }

        // Si no se cumple ninguna de las condiciones anteriores, entonces está libre
        return true;
    }

    // Getters y Setters
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

    /**
     * Establece una nueva calificación para el inmueble.
     *
     * @param nota la nueva calificación
     * @throws IllegalArgumentException si la calificación no está entre 0 y 5
     */
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

    public Anfitrion getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(Anfitrion anfitrion) {
        this.anfitrion = anfitrion;
        JavaBNB.guardarDatos();
    }

    public int getValoraciones() {
        return valoraciones;
    }

    public ArrayList<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(ArrayList<Resenia> resenias) {
        this.resenias = resenias;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Método para agregar una reseña al inmueble.
     *
     * @param resenia la reseña a agregar
     */
    public void addResenias(Resenia resenia) {
        this.resenias.add(resenia);
        JavaBNB.guardarDatos();
    }

    /**
     * Método para agregar una reserva al inmueble.
     *
     * @param reserva la reserva a agregar
     */
    public void agregarReserva(Reserva reserva) {
        if (!reservas.contains(reserva)) {
            reservas.add(reserva);
            JavaBNB.guardarDatos();
        }
    }

    /**
     * Método para eliminar una reserva del inmueble.
     *
     * @param reserva la reserva a eliminar
     */
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        JavaBNB.guardarDatos();
    }

    
    @Override
    public String toString() {
        return "Inmueble{" + ", titulo=" + titulo + ", direccion=" + direccion + ", datosInmueble=" + datosInmueble + ", tipo=" + tipo + ", precioNoche=" + precioNoche + ", servicios=" + servicios + ", calificacion=" + calificacion + ", fotografia=" + fotografia + ", descripcion=" + descripcion + '}';
    }
}
