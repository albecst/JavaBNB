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
    private double calificacion;
    private String fotografia;
    private String descripcion;
    private int valoraciones;
    private ArrayList<Reserva> reservas; // ArrayList para almacenar las reservas asociadas al inmueble
    private Anfitrion anfitrion;
    private static final long serialVersionUID = 6795168503584728871L;
    
    

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

    /**
     * Método para obtener todas las reservas asociadas al inmueble.
     *
     * @return una lista de reservas asociadas al inmueble
     */
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
    /**
     * Obtiene la fotografía del inmueble.
     *
     * @return la fotografía del inmueble
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * Establece la fotografía del inmueble.
     *
     * @param fotografia la nueva fotografía del inmueble
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene la calificación del inmueble.
     *
     * @return la calificación del inmueble
     */
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

    /**
     * Obtiene los servicios ofrecidos en el inmueble.
     *
     * @return los servicios ofrecidos en el inmueble
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * Establece los servicios ofrecidos en el inmueble.
     *
     * @param servicios los nuevos servicios ofrecidos en el inmueble
     */
    public void setServicios(String servicios) {
        this.servicios = servicios;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene el precio por noche del inmueble.
     *
     * @return el precio por noche del inmueble
     */
    public double getPrecioNoche() {
        return precioNoche;
    }

    /**
     * Establece el precio por noche del inmueble.
     *
     * @param precioNoche el nuevo precio por noche del inmueble
     */
    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene el tipo de inmueble (casa o apartamento).
     *
     * @return el tipo de inmueble
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de inmueble (casa o apartamento).
     *
     * @param tipo el nuevo tipo de inmueble
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene los datos del inmueble.
     *
     * @return los datos del inmueble
     */
    public DatosInmueble getDatosInmueble() {
        return datosInmueble;
    }

    /**
     * Establece los datos del inmueble.
     *
     * @param datosInmueble los nuevos datos del inmueble
     */
    public void setDatosInmueble(DatosInmueble datosInmueble) {
        this.datosInmueble = datosInmueble;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene la dirección del inmueble.
     *
     * @return la dirección del inmueble
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del inmueble.
     *
     * @param direccion la nueva dirección del inmueble
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene el título del inmueble.
     *
     * @return el título del inmueble
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del inmueble.
     *
     * @param titulo el nuevo título del inmueble
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene la descripción del inmueble.
     *
     * @return la descripción del inmueble
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del inmueble.
     *
     * @param descripcion la nueva descripción del inmueble
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene el anfitrión del inmueble.
     *
     * @return el anfitrión del inmueble
     */
    public Anfitrion getAnfitrion() {
        return anfitrion;
    }

    /**
     * Establece el anfitrión del inmueble.
     *
     * @param anfitrion el nuevo anfitrión del inmueble
     */
    public void setAnfitrion(Anfitrion anfitrion) {
        this.anfitrion = anfitrion;
        JavaBNB.guardarDatos();
    }

    /**
     * Obtiene el número de valoraciones del inmueble.
     *
     * @return el número de valoraciones
     */
    public int getValoraciones() {
        return valoraciones;
    }

    /**
     * Devuelve una representación en cadena de los datos del inmueble.
     *
     * @return una cadena que representa los datos del inmueble
     */
    @Override
    public String toString() {
        return "Inmueble{" + ", titulo=" + titulo + ", direccion=" + direccion + ", datosInmueble=" + datosInmueble + ", tipo=" + tipo + ", precioNoche=" + precioNoche + ", servicios=" + servicios + ", calificacion=" + calificacion + ", fotografia=" + fotografia + ", descripcion=" + descripcion + '}';
    }
}
