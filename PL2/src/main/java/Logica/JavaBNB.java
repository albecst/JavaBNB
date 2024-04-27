package Logica;

import Logica.*;
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

    //Atributos
    private static ArrayList<Inmueble> inmueblesDisponibles;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Anfitrion> anfitriones;
    private static ArrayList<Particular> particulares;

    /**
     * Constructor de la clase JavaBNB.
     */
    public JavaBNB() {
        inmueblesDisponibles = new ArrayList<>();
        clientes = new ArrayList<>();
        anfitriones = new ArrayList<>();
        particulares = new ArrayList<>();   //hay que poner this.particulares ????????????????????????????????????????????????? 
    }

    /**
     * Añade inmuebles a la lista de inmuebles disponibles si, y solo si, no
     * existe algún inmueble con la misma dirección.
     *
     * @param inmueble Inmueble a añadir.
     */
    public static void añadirInmueble(Inmueble inmueble) {
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
            //Lectura de los objetos de tipo persona
            FileInputStream istreamClientes = new FileInputStream("copiasegClientes.dat");
            FileInputStream istreamAnfitriones = new FileInputStream("copiasegAnfitriones.dat");
            FileInputStream istreamParticulares = new FileInputStream("copiasegParticulares.dat");
            FileInputStream istreamInmuebles = new FileInputStream("copiasegInmuebles.dat");

            ObjectInputStream oisClientes = new ObjectInputStream(istreamClientes);
            ObjectInputStream oisAnfitriones = new ObjectInputStream(istreamAnfitriones);
            ObjectInputStream oisParticulares = new ObjectInputStream(istreamParticulares);
            ObjectInputStream oisInmuebles = new ObjectInputStream(istreamInmuebles);

            clientes = (ArrayList<Cliente>) oisClientes.readObject();
            anfitriones = (ArrayList<Anfitrion>) oisAnfitriones.readObject();
            particulares = (ArrayList<Particular>) oisParticulares.readObject();
            inmueblesDisponibles = (ArrayList<Inmueble>) oisInmuebles.readObject();

            istreamClientes.close();
            istreamAnfitriones.close();
            istreamParticulares.close();
            istreamInmuebles.close();

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin cargarDatos

    /**
     * **** Serialización de los objetos *****
     */
    public static void guardarDatos() {
        try {
            //Si hay datos los guardamos...
            if (!clientes.isEmpty()) {

                /**
                 * **** Serialización de todos los clientes *****
                 */
                FileOutputStream ostreamClientes = new FileOutputStream("copiasegClientes.dat");
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

            if (!anfitriones.isEmpty()) {
                /**
                 * **** Serialización de los anfitriones *****
                 */

                FileOutputStream ostreamAnfitriones = new FileOutputStream("copiasegAnfitrion.dat");
                ObjectOutputStream oosAnfitriones = new ObjectOutputStream(ostreamAnfitriones);
                //guardamos el array de anfitriones
                oosAnfitriones.writeObject(anfitriones);
                ostreamAnfitriones.close();
            } else {
                System.out.println("Error: No hay datos de anfitriones...");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {

            if (!particulares.isEmpty()) {
                /**
                 * **** Serialización de los particulares *****
                 */

                FileOutputStream ostreamParticulares = new FileOutputStream("copiasegParticulares.dat");
                ObjectOutputStream oosParticulares = new ObjectOutputStream(ostreamParticulares);
                //guardamos el array de particulares
                oosParticulares.writeObject(particulares);
                ostreamParticulares.close();
            } else {
                System.out.println("Error: No hay datos de particulares...");
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

                FileOutputStream ostreamInmuebles = new FileOutputStream("copiasegInmuebles.dat");
                ObjectOutputStream oosInmuebles = new ObjectOutputStream(ostreamInmuebles);
                //guardamos el array de inmuebles
                oosInmuebles.writeObject(inmueblesDisponibles);
                ostreamInmuebles.close();
            } else {
                System.out.println("Error: No hay datos de inmuebles...");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }//fin guardarDatos

    /**
     * Realizar un registro
     */
    public static boolean comprobarExistenciaCliente(String correo, String dni, String telefono) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                if (cliente.getCorreo().equals(correo)) {
                    System.out.println("Este correo ya existe");
                    return true;

                } else if (cliente.getDni().equals(dni)) {
                    System.out.println("Este DNI ya existe");
                    return true;

                } else if (cliente.getTelefono().equals(telefono)) {
                    System.out.println("Este teléfono ya existe");
                    return true;

                }

            }
        }
        return false;

    }

    //Aquí preguntaríamos al usuario si quiere registrarse como anfitrión o particular.
    public static void registrarAnfitrion(String dni, String nombre, String correo, String clave, String telefono) {
        if (comprobarExistenciaCliente(correo, dni, telefono)) {
            return;
        }
        Anfitrion nuevoAnfitrion = new Anfitrion(dni, nombre.toLowerCase(), correo, clave, telefono);
        clientes.add(nuevoAnfitrion);
        anfitriones.add(nuevoAnfitrion);

        for (Anfitrion anfitrion : anfitriones) {
            System.out.println(anfitrion.toString());
        }
    }

    public static void registrarParticular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        for (Cliente cliente : clientes) {  //este for sobra
            if (comprobarExistenciaCliente(correo, dni, telefono)) {
                return;
            }
            Particular nuevoParticular = new Particular(tarjetaCredito, vip, dni, nombre.toLowerCase(), correo, clave, telefono);
            clientes.add(nuevoParticular);
            particulares.add(nuevoParticular);
        }
    }
}
