package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JavaBNB implements Serializable {

    private static ArrayList<Inmueble> inmueblesDisponibles;
    private static ArrayList<Cliente> clientes;

    public static void inicializadorJavaBNB() {
        inmueblesDisponibles = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public static ArrayList<Inmueble> getInmueblesDisponibles() {
        return inmueblesDisponibles;
    }

    public static void setInmueblesDisponibles(ArrayList<Inmueble> inmueblesDisponibles) {
        JavaBNB.inmueblesDisponibles = inmueblesDisponibles;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        JavaBNB.clientes = clientes;
    }

    /**
     * Añade inmuebles a la lista de inmuebles disponibles si, y solo si, no
     * existe algún inmueble con la misma dirección.
     *
     * @param inmueble Inmueble a añadir.
     */
    public static boolean añadirInmueble(Inmueble inmueble) {
        boolean existeInmuebleConMismaDireccion = inmueblesDisponibles.stream()
                .anyMatch(inmuebleExistente -> inmuebleExistente.getDireccion().equals(inmueble.getDireccion()));

        if (!existeInmuebleConMismaDireccion) {
            inmueblesDisponibles.add(inmueble);
        } else {
            System.out.println("El inmueble ya está añadido");
        }
        
        for (Inmueble inmueblee : inmueblesDisponibles){
            System.out.println(inmueblee.toString());
        }
        
        return !existeInmuebleConMismaDireccion;
    }

    /**
     * Busca inmuebles disponibles en una ciudad y entre dos fechas
     *
     * @param ciudad Ciudad en la que buscar inmuebles.
     * @param fechaEntrada Fecha de inicio de la disponibilidad.
     * @param fechaSalida Fecha de fin de la disponibilidad.
     * @return Lista de inmuebles disponibles en la ciudad y entre las fechas
     * dadas.
     */
    public static ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        ArrayList<Inmueble> inmueblesDisponiblesEnCiudad = new ArrayList<>();

        if (inmueblesDisponibles.isEmpty() == true) {
            return inmueblesDisponiblesEnCiudad;
        } else {
            for (Inmueble inmueble : inmueblesDisponibles) {
                if (inmueble.getDireccion().getCiudad().equalsIgnoreCase(ciudad) && inmueble.estaDisponible(fechaEntrada, fechaSalida)) {
                    inmueblesDisponiblesEnCiudad.add(inmueble);
                }
            }
        }

        return inmueblesDisponiblesEnCiudad;
        // Vamos a devolver una lista de inmuebles disponibles en esa ciudad y entre las fechas dadas.
    }

    /**
     * Estos 6 primeros métodos son para ordenar los inmuebles disponibles (sin
     * ningún filtro en ciudad, o tiempo) según distintos criterios
     */
    /**
     * SF: Sin Filtro
     *
     * Busca inmuebles disponibles ordenados por precio de menor a mayor.
     */
    public static void ordenarPorPrecioAscSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    /**
     * Ordena los inmuebles disponibles por precio de mayor a menor.
     */
    public static void ordenarPorPrecioDescSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    /**
     * Ordena los inmuebles disponibles por tipo, primero casas y luego
     * apartamentos.
     */
    public static void ordenarPorTipoSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    /**
     * Ordena los inmuebles disponibles por tipo.
     */
    public static void ordenarPorCalificacionAscSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    /**
     * Ordena los inmuebles disponibles por calificación de menor a mayor.
     */
    public static void ordenarPorCalificacionDescSF() {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

    /**
     * CF: Con Filtro
     *
     * Busca inmuebles disponibles tras un filtro de ciudad, fecha de entrada y
     * fecha de salida, ordenados por precio de menor a mayor.
     */
    public static void ordenarPorPrecioAscCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de
     * entrada y fecha de salida, por precio de mayor a menor.
     */
    public static void ordenarPorPrecioDescCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de
     * entrada y fecha de salida, por tipo, primero casas y luego apartamentos.
     */
    public static void ordenarPorTipoCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de
     * entrada y fecha de salida, por calificación de menor a mayor.
     */
    public static void ordenarPorCalificacionAscCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    /**
     * Ordena los inmuebles disponibles tras un filtro de ciudad, fecha de
     * entrada y fecha de salida, por calificación de mayor a menor.
     */
    public static void ordenarPorCalificacionDescCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

    /**
     * Calcula el precio total de una reserva.
     */
    public static double calcularPrecioTotal(Inmueble inmueble, Particular particular, LocalDate fechaEntrada, LocalDate fechaSalida) {
        long diasEstancia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double costoTotal = diasEstancia * inmueble.getPrecioNoche();
        if (particular.isVip()) {
            costoTotal *= 0.9;
        }
        return costoTotal;
    }

    /**
     * Procesa el pago de una reserva y la reserva de un inmueble si todo está
     * correcto.
     *
     * @param inmueble Inmueble a reservar.
     * @param fechaEntrada Día de comienzo de la reserva.
     * @param fechaSalida Día de fin de la reserva.
     */
    public static void procesarReserva(Inmueble inmueble, Particular particular, LocalDate fechaEntrada, LocalDate fechaSalida) {
        double saldoRestanteParticular = particular.getSaldo();
        double costoTotal = calcularPrecioTotal(inmueble, particular, fechaEntrada, fechaSalida);
        if (saldoRestanteParticular < costoTotal) {
            System.out.println("No hay dinero suficiente para realizar la reserva");
        } else {
            particular.disminuirSaldo(costoTotal);
            Reserva reserva = new Reserva(particular, inmueble, fechaEntrada, fechaSalida);
            particular.addReserva(reserva);
            inmueblesDisponibles.remove(inmueble);
        }
    }

    /**
     * Carga los datos de personas del fichero
     */
    public static void cargarDatos() {
        try {
            FileInputStream istreamClientes = new FileInputStream("./src/main/resources/data/copiasegClientes.dat");
            ObjectInputStream oisClientes = new ObjectInputStream(istreamClientes);
            clientes = (ArrayList<Cliente>) oisClientes.readObject();
            istreamClientes.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            FileInputStream istreamInmuebles = new FileInputStream("./src/main/resources/data/copiasegInmuebles.dat");
            ObjectInputStream oisInmuebles = new ObjectInputStream(istreamInmuebles);
            inmueblesDisponibles = (ArrayList<Inmueble>) oisInmuebles.readObject();
            istreamInmuebles.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    
    
    public static void guardarfoto(String foto){
    try {
            if (!foto.equals("")) {
                               FileOutputStream ostreamClientes = new FileOutputStream("./src/main/resources/data/copiasegClientes.dat");
                ObjectOutputStream oosClientes = new ObjectOutputStream(ostreamClientes);
                //guardamos el array de personas
                oosClientes.writeObject(clientes);
                ostreamClientes.close();
            } else {
                System.out.println("Error: No hay datos de clientes...");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    
    }
    /**
     * **** Serialización de los objetos *****
     */
    public static void guardarDatos() {
        try {
            if (!clientes.isEmpty()) {
                /**
                 * **** Serialización de todos los clientes *****
                 */
                FileOutputStream ostreamClientes = new FileOutputStream("./src/main/resources/data/copiasegClientes.dat");
                ObjectOutputStream oosClientes = new ObjectOutputStream(ostreamClientes);
                //guardamos el array de personas
                oosClientes.writeObject(clientes);
                ostreamClientes.close();
            } else {
                System.out.println("Error: No hay datos de clientes...");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            if (!inmueblesDisponibles.isEmpty()) {
                /**
                 * **** Serialización de los inmuebles *****
                 */
                FileOutputStream ostreamInmuebles = new FileOutputStream("./src/main/resources/data/copiasegInmuebles.dat");
                ObjectOutputStream oosInmuebles = new ObjectOutputStream(ostreamInmuebles);
                //guardamos el array de inmuebles
                oosInmuebles.writeObject(inmueblesDisponibles);
                ostreamInmuebles.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
