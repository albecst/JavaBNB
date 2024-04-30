package Logica;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author alber
 */
public class Tarjeta implements Serializable {

    //Atributos
    private String titular;
    private String numeroTarjeta;
    private LocalDate fechaCaducidad;
    private String cvv;
    private double saldo;

    //Constructor
    public Tarjeta(String titular, String numeroTarjeta, int dia, int mes, int anio, LocalDate fechaCaducidad, String cvv, double Saldo) {
        this.titular = titular;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.saldo = saldo;
        this.cvv = cvv;
    }

    //Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Get the value of fechaCaducidad
     *
     * @return the value of fechaCaducidad
     */
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Set the value of fechaCaducidad
     *
     * @param fechaCaducidad new value of fechaCaducidad
     */
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public void setFechaCaducidad(int dia, int mes, int anio) {
        this.fechaCaducidad = LocalDate.of(anio, mes, dia);
    }


    /**
     * Get the value of numeroTarjeta
     *
     * @return the value of numeroTarjeta
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Set the value of numeroTarjeta
     *
     * @param numeroTarjeta new value of numeroTarjeta
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Get the value of titular
     *
     * @return the value of titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Set the value of titular
     *
     * @param titular new value of titular
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "titular=" + titular + ", numeroTarjeta=" + numeroTarjeta + ", fechaCaducidad=" + fechaCaducidad + ", cvv=" + cvv + ", saldo=" + saldo + '}';
    }

    
}
