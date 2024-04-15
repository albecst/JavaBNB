/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author alber
 */
public class Particular extends Cliente {

    //Atributos
    private Tarjeta tarjetaCredito;
    private boolean vip;

    //Constructor
    public Particular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.tarjetaCredito = tarjetaCredito;
        this.vip = vip;
    }

    //G&S
    public double disminuirSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        return saldo -= cantidad;
    }

    public double aumentarSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        return saldo += cantidad;
    }

    public double getSaldo() {
        return tarjetaCredito.getSaldo();
    }

    public void setSaldo(double saldo) {
        tarjetaCredito.setSaldo(saldo);
    }

    /**
     * Get the value of vip
     *
     * @return the value of vip
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * Set the value of vip
     *
     * @param vip new value of vip
     */
    public void setVip(boolean vip) {
        this.vip = vip;
    }

    /**
     * Get the value of tarjetaCredito
     *
     * @return the value of tarjetaCredito
     */
    public Tarjeta getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     * Set the value of tarjetaCredito
     *
     * @param tarjetaCredito new value of tarjetaCredito
     */
    public void setTarjetaCredito(Tarjeta tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

}
