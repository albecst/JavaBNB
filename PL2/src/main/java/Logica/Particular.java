package Logica;

import java.io.Serializable;

/**
 * La clase Particular representa un cliente particular que utiliza la
 * plataforma JavaBNB. Hereda de la clase Cliente e incluye detalles adicionales
 * como una tarjeta de crédito y el estado VIP.
 */
public class Particular extends Cliente implements Serializable {

    private Tarjeta tarjetaCredito;
    private boolean vip;
    private static final long serialVersionUID = -5515338684542348855L; // Necesario para no tener problemas con la serialización de particulares

    /**
     * Constructor de la clase Particular.
     *
     * @param tarjetaCredito la tarjeta de crédito del cliente
     * @param vip indica si el cliente es VIP
     * @param dni el DNI del cliente
     * @param nombre el nombre del cliente
     * @param correo el correo electrónico del cliente
     * @param clave la clave del cliente
     * @param telefono el teléfono del cliente
     */
    public Particular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.tarjetaCredito = tarjetaCredito;
        this.vip = vip;
    }

    /**
     * Método para disminuir el saldo de la tarjeta de crédito. Si el cliente es
     * VIP, se aplica un descuento del 10%.
     *
     * @param cantidad la cantidad a disminuir del saldo
     *
     */
    public void disminuirSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        if (vip) {
            saldo -= cantidad * 0.9;
        } else {
            saldo -= cantidad;
        }
        tarjetaCredito.setSaldo(saldo);
    }

    /**
     *
     * Método para aumentar el saldo de la tarjeta de crédito.
     *
     * @param cantidad la cantidad a aumentar al saldo
     *
     */
    public void aumentarSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        saldo += cantidad;
        tarjetaCredito.setSaldo(saldo);
    }

    //Getters y Setters
    public double getSaldo() {
        return tarjetaCredito.getSaldo();
    }

    public void setSaldo(double saldo) {
        tarjetaCredito.setSaldo(saldo);
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Tarjeta getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(Tarjeta tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public String toString() {
        return super.toString() + tarjetaCredito.toString() + ", vip:" + vip;
    }
}
