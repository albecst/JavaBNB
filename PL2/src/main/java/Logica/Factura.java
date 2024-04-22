package Logica;

import Logica.DatosInmueble;

/**
 *
 * @author alber
 */
public class Factura {

    //Atributos
    private int idFactura;
    private double importe;
    private String concepto;
    private String fecha;
    private String hora;
    private String dniCliente;
    private String dniAnfitrion;
    private String idReserva;
    private DatosInmueble datosInmueble;

    /**
     * Constructor de la clase Factura.
     * @param idFactura Identificador de la factura.
     * @param importe Importe de la factura.
     * @param concepto Concepto de la factura.
     * @param fecha Fecha de la factura.
     * @param hora Hora de la factura.
     * @param dniCliente DNI del cliente.
     * @param dniAnfitrion DNI del anfitrión.
     * @param idReserva Identificador de la reserva.
     * @param datosInmueble Datos del inmueble.
     */
    public Factura(int idFactura, double importe, String concepto, String fecha, String hora, String dniCliente, String dniAnfitrion, String idReserva, DatosInmueble datosInmueble) {
        this.idFactura = idFactura;
        this.importe = importe;
        this.concepto = concepto;
        this.fecha = fecha;
        this.hora = hora;
        this.dniCliente = dniCliente;
        this.dniAnfitrion = dniAnfitrion;
        this.idReserva = idReserva;
        this.datosInmueble = datosInmueble;
    }

    //Faltan métodos para la factura, lo veremos cuando toquemos Swing (UI/UX).


    /**
     * Getters y Setters.
     *
     * Get the value of idFactura
     *
     * @return the value of idFactura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * Set the value of idFactura
     *
     * @param idFactura new value of idFactura
     */

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * Get the value of importe
     *
     * @return the value of importe
     */

    public double getImporte() {
        return importe;
    }

    /**
     * Set the value of importe
     *
     * @param importe new value of importe
     */

    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Get the value of concepto
     *
     * @return the value of concepto
     */

    public String getConcepto() {
        return concepto;
    }

    /**
     * Set the value of concepto
     *
     * @param concepto new value of concepto
     */

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */

    public String getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Get the value of hora
     *
     * @return the value of hora
     */

    public String getHora() {
        return hora;
    }

    /**
     * Set the value of hora
     *
     * @param hora new value of hora
     */

    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Get the value of dniCliente
     *
     * @return the value of dniCliente
     */

    public String getDniCliente() {
        return dniCliente;
    }

    /**
     * Set the value of dniCliente
     *
     * @param dniCliente new value of dniCliente
     */

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    /**
     * Get the value of dniAnfitrion
     *
     * @return the value of dniAnfitrion
     */

    public String getDniAnfitrion() {
        return dniAnfitrion;
    }

    /**
     * Set the value of dniAnfitrion
     *
     * @param dniAnfitrion new value of dniAnfitrion
     */

    public void setDniAnfitrion(String dniAnfitrion) {
        this.dniAnfitrion = dniAnfitrion;
    }

    /**
     * Get the value of idReserva
     *
     * @return the value of idReserva
     */

    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Set the value of idReserva
     *
     * @param idReserva new value of idReserva
     */

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
}
