package Logica;

import java.io.Serializable;
import java.time.LocalDate;

public class Tarjeta implements Serializable {

    //Atributos
    private String titular;
    private String numeroTarjeta;
    private LocalDate fechaCaducidad;
    private String cvv;
    private int dia;
    private int mes;
    private int anio;
    private double saldo;

    //Constructor
    public Tarjeta(String titular, String numeroTarjeta, int dia, int mes, int anio, LocalDate fechaCaducidad, String cvv, double saldo) {
        this.titular = titular;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.saldo = saldo;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.cvv = cvv;
    }

    //Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setFechaCaducidad(int dia, int mes, int anio) {
        this.fechaCaducidad = LocalDate.of(anio, mes, dia);
    }

    public void incrementarSaldo(double cantidad) {
        this.saldo += cantidad;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return ", Tarjeta{" + "número de la tarjeta:" + numeroTarjeta + ", fecha de caducidad:" + fechaCaducidad + ", cvv:" + cvv + ", saldo:" + saldo + '}';
    }

}
