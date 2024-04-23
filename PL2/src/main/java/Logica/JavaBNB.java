package Logica;

import Logica.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JavaBNB {

    //Atributos
    private ArrayList<Inmueble> inmueblesDisponibles;
    private Particular particular;  ////////////////////////////////////////////arraylist
    private Anfitrion anfitrion;

    /**
     * Constructor de la clase JavaBNB.
     */
    public JavaBNB() {
        inmueblesDisponibles = new ArrayList<>();
    }

    /**
     * Añade inmuebles a la lista de inmuebles disponibles si, y solo si, no existe algún inmueble con la misma dirección.
     * @param inmueble Inmueble a añadir.
     */
    public void añadirInmueble(Inmueble inmueble) {
        boolean existeInmuebleConMismaDireccion = inmueblesDisponibles.stream()
                .anyMatch(inmuebleExistente -> inmuebleExistente.getDireccion().equals(inmueble.getDireccion()));

        if (!existeInmuebleConMismaDireccion) {
            inmueblesDisponibles.add(inmueble);
        } else {
            System.out.println("El inmueble ya está añadido");
        }
    }

    /**
     * Busca inmuebles disponibles en una ciudad y entre dos fechas
     *
     * @param ciudad       Ciudad en la que buscar inmuebles.
     * @param fechaEntrada Fecha de inicio de la disponibilidad.
     * @param fechaSalida  Fecha de fin de la disponibilidad.
     * @return Lista de inmuebles disponibles en la ciudad y entre las fechas dadas.
     */
    public ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        ArrayList<Inmueble> inmueblesDisponiblesEnCiudad = new ArrayList<>();

        if (inmueblesDisponibles.isEmpty() == true){
            return inmueblesDisponiblesEnCiudad;
        }
        else{
            for (Inmueble inmueble : this.inmueblesDisponibles) {
                if (inmueble.getDireccion().getCiudad().equalsIgnoreCase(ciudad) && inmueble.estaDisponible(fechaEntrada, fechaSalida)) {
                    inmueblesDisponiblesEnCiudad.add(inmueble);
                }
            }
        }

        return inmueblesDisponiblesEnCiudad;
        // Vamos a devolver una lista de inmuebles disponibles en esa ciudad y entre las fechas dadas.
    }

    /** Estos 6 primeros métodos son para ordenar los inmuebles disponibles (sin ningún filtro en ciudad, o tiempo) según distintos criterios */
    /**
     * SF: Sin Filtro
     *
     * Busca inmuebles disponibles ordenados por precio de menor a mayor.
     */
    public void ordenarPorPrecioAscSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    /**
     * Ordena los inmuebles disponibles por precio de mayor a menor.
     */
    public void ordenarPorPrecioDescSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    /**
     * Ordena los inmuebles disponibles por tipo, primero casas y luego apartamentos.
     */
    public void ordenarPorTipoSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    /**
     * Ordena los inmuebles disponibles por tipo.
     */
    public void ordenarPorCalificacionAscSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    /**
     * Ordena los inmuebles disponibles por calificación de menor a mayor.
     */
    public void ordenarPorCalificacionDescSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }


    /**
     * CF: Con Filtro
     *
     * Busca inmuebles disponibles tras un filtro de ciudad, fecha de entrada y fecha de salida, ordenados por precio de menor a mayor.
     */
    public void ordenarPorPrecioAscCF(ArrayList <Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de entrada y fecha de salida, por precio de mayor a menor.
     */
    public void ordenarPorPrecioDescCF(ArrayList <Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de entrada y fecha de salida, por tipo, primero casas y luego apartamentos.
     */
    public void ordenarPorTipoCF(ArrayList <Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de entrada y fecha de salida, por calificación de menor a mayor.
     */
    public void ordenarPorCalificacionAscCF(ArrayList <Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de entrada y fecha de salida, por calificación de mayor a menor.
     */
    public void ordenarPorCalificacionDescCF(ArrayList <Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

    /**
     * Calcula el precio total de una reserva.
     */
    public double calcularPrecioTotal(Inmueble inmueble, LocalDate fechaEntrada, LocalDate fechaSalida) {
        long diasEstancia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double costoTotal = diasEstancia * inmueble.getPrecioNoche();
        if (particular.isVip()) {
            costoTotal *= 0.9;
        }
        return costoTotal;
    }


    /**
     * Procesa el pago de una reserva y la reserva de un inmueble si todo está correcto.
     *
     * @param inmueble     Inmueble a reservar.
     * @param fechaEntrada Día de comienzo de la reserva.
     * @param fechaSalida  Día de fin de la reserva.
     */
    public void procesarReserva(Inmueble inmueble, LocalDate fechaEntrada, LocalDate fechaSalida) {
        double saldoRestanteParticular = particular.getSaldo();
        double costoTotal = calcularPrecioTotal(inmueble, fechaEntrada, fechaSalida);
        if (saldoRestanteParticular < costoTotal) {
            System.out.println("No hay dinero suficiente para realizar la reserva");
        } else {
            particular.disminuirSaldo(costoTotal);
            Reserva reserva = new Reserva(particular, inmueble, fechaEntrada, fechaSalida);
            particular.addReserva(reserva);
            inmueblesDisponibles.remove(inmueble);
        }
    }
}
