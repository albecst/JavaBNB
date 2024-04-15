package Aplicacion;

import Logica.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JavaBNB {
    
    //Atributos
    private ArrayList<Inmueble> inmueblesDisponibles;
    private Particular particular;
    private Anfitrion anfitrion;

    /**
     * Constructor de la clase JavaBNB.
     * @param inmueblesDisponibles Lista de inmuebles disponibles.
     */
    public JavaBNB(ArrayList<Inmueble> inmueblesDisponibles) {
        this.inmueblesDisponibles = inmueblesDisponibles;
    }

    //Métodos
    /**
     * Busca inmuebles disponibles en una ciudad y entre dos fechas.
     * @param ciudad Ciudad en la que buscar inmuebles.
     * @param fechaEntrada Fecha de inicio de la disponibilidad.
     * @param fechaSalida Fecha de fin de la disponibilidad.
     * @return Lista de inmuebles disponibles en la ciudad y entre las fechas dadas.
     */
    public ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        ArrayList<Inmueble> inmueblesDisponiblesEnCiudad = new ArrayList<>();
        
        if (inmueblesDisponibles != null && !inmueblesDisponibles.isEmpty()) {
            for (Inmueble inmueble : this.inmueblesDisponibles) {
                if (inmueble.getDireccion().getCiudad().equalsIgnoreCase(ciudad) && inmueble.estaDisponible(fechaEntrada, fechaSalida)) {
                    inmueblesDisponiblesEnCiudad.add(inmueble);
                }
            }
        }

        return inmueblesDisponiblesEnCiudad;
    }
    /**
     * Busca inmuebles disponibles en una ciudad y entre dos fechas, ordenados por precio de menor a mayor.
     */


    public void ordenarPorPrecioAsc() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }
    /**
     * Ordena los inmuebles disponibles por precio de menor a mayor.
     */


    public void ordenarPorPrecioDesc() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }
    /**
     * Ordena los inmuebles disponibles por precio de mayor a menor.
     */


    public void ordenarPorTipo() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }
    /**
     * Ordena los inmuebles disponibles por tipo.
     */


    public void ordenarPorCalificacionAsc() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }
    /**
     * Ordena los inmuebles disponibles por calificación de menor a mayor.
     */


    public void ordenarPorCalificacionDesc() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }
    /**
     * Ordena los inmuebles disponibles por calificación de mayor a menor.
     */


    public double calcularPrecioTotal(Inmueble inmueble, LocalDate fechaEntrada, LocalDate fechaSalida){
        long diasEstancia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double costoTotal = diasEstancia * inmueble.getPrecioNoche();
        if (particular.isVip()){
            costoTotal *= 0.9;
    }
        return costoTotal;
    
    }
    /**
     * Calcula el precio total de la estancia en un inmueble para un rango de fechas.
     */


    public void procesarPagoReserva(double costoTotal){
    double saldoRestanteParticular = particular.getSaldo(); 
        if (saldoRestanteParticular < costoTotal){
            System.out.println("No hay dinero suficiente para realizar la reserva");
        }
        else{
            particular.disminuirSaldo(costoTotal);
        }
    }
}
