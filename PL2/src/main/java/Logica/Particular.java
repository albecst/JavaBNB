package Logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Particular extends Cliente implements Serializable {

    private Tarjeta tarjetaCredito;
    private boolean vip;
    private ArrayList <Reserva> reservas = new ArrayList<>();

    /**
     * Constructor de la clase Particular
     *
     * @param tarjetaCredito
     * @param vip
     * @param dni
     * @param nombre
     * @param correo
     * @param clave
     * @param telefono
     */
    public Particular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.tarjetaCredito = tarjetaCredito;
        this.vip = vip;
    }

    /**
     * Método para disminuir el saldo de la tarjeta de crédito
     * @param cantidad
     * @return
     */
    public String disminuirSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        if (vip){
        saldo -= cantidad*0.9;
        tarjetaCredito.setSaldo(saldo);  
        }else{
        saldo -= cantidad;
        tarjetaCredito.setSaldo(saldo);  
    }
         return "El saldo actual es: "+ saldo + "€";
    }

    /**
     * Método para aumentar el saldo de la tarjeta de crédito
     * @param cantidad
     * @return
     */
    public String aumentarSaldo(double cantidad) {
        Double saldo = tarjetaCredito.getSaldo();
        saldo += cantidad;
        tarjetaCredito.setSaldo(saldo);
        return "El saldo actual es: "+ saldo + "€";
    }

    public void addReserva(Reserva reserva) {
        if (!reservas.contains(reserva)){
            reservas.add(reserva);
            disminuirSaldo(reserva.calcularPrecioTotal());
        }
        else{
            System.out.println("La reserva ya existe");
        }
    }


    /**
     * Getters & Setters
     */
    
    
    //TODO: esto no sobra?
     /** Get the value of saldo
     *
     * @return the value of saldo
     */

    public double getSaldo() {
        return tarjetaCredito.getSaldo();
    }

    /**
     * Set the value of saldo
     *
     * @param saldo new value of saldo
     */

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

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    

    @Override
    public String toString() {
        return super.toString()+ tarjetaCredito.toString() + ", vip:" + vip ;
    }

    
}
