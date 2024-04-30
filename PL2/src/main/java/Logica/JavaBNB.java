package Logica;

import UI_UX.Aplicacion;
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
    public static ArrayList<Inmueble> inmueblesDisponibles;
    public static ArrayList<Cliente> clientes;
    

    /**
     * Constructor de la clase JavaBNB.
     */
    public JavaBNB() {
        inmueblesDisponibles = new ArrayList<>();
        clientes = new ArrayList<>();
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
            //datos de todos los clientes
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

            //datos de todos los inmuebles         
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
    }//fin guardarDatos

    /**
     * Realizar un registro
     */
    public static boolean comprobarExistenciaCliente(String correo, String dni, String telefono) {  //dejar solo comprobarUsuario?
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
        //instanciamos obj de tipo anfitrion, añadimos a lista de clientes y iniciamos sesion
        Anfitrion nuevoAnfitrion = new Anfitrion(dni, nombre.toLowerCase(), correo.toLowerCase(), clave, telefono);
        clientes.add(nuevoAnfitrion);
        Aplicacion.sesion.nuevaSesion(nuevoAnfitrion);
        System.out.println(nuevoAnfitrion.toString());
        
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public static void registrarParticular(Tarjeta tarjetaCredito, boolean vip, String dni, String nombre, String correo, String clave, String telefono) {
        if (comprobarExistenciaCliente(correo, dni, telefono)) {
            return;
        }
        Particular nuevoParticular = new Particular(tarjetaCredito, vip, dni, nombre.toLowerCase(), correo.toLowerCase(), clave, telefono);
        clientes.add(nuevoParticular);
        Aplicacion.sesion.nuevaSesion(nuevoParticular);
        System.out.println(nuevoParticular.toString());


        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());

        }
    }
    
    //TODO: Deberíamos poner que también se asocie al correo, porque si no pueden existir 2 personas con el mismo correo (me ha pasado)
    public static boolean comprobarUsuario(String dni) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                if (cliente.getDni().equals(dni)) {
                    System.out.println("Este DNI ya existe");
                    return true;
                }
            }
        }
        return false;
    }
    //iniciar sesion. si tipo es 0, el cliente aun no esta registrado. si es 1 es admin y si es 2/3 es cliente

    /**
     *
     * @param correo
     * @param clave
     * @return
     */
    public static int iniciarSesion(String correo, String clave) {
        int tipo = 0;
        boolean isHost = false;
        if (correo.equals("admin@javabnb.com") && clave.equals("admin")) {    //a administrador se deberia poder acceder sin instanciar
            System.out.println("Sesión iniciada como administrador");
            tipo = 1;
        } else {
            for (Cliente cliente : clientes) {
                isHost = (cliente instanceof Anfitrion);
                System.out.println("Cliente no registrado");

                if (isHost = false && cliente.getCorreo().equals(correo.toLowerCase()) && cliente.getClave().equals(clave)) {
                    System.out.println("Sesión iniciada como particular");
                    Aplicacion.sesion.nuevaSesion(cliente);
                    System.out.println(Aplicacion.sesion.user);
                    //System.out.println("is host? " + Sesion.esAnfitrion); //false
                    tipo = 2;

                } else if (isHost = true && cliente.getCorreo().equals(correo.toLowerCase()) && cliente.getClave().equals(clave)) {
                    System.out.println("Sesión iniciada como anfitrion");
                    Aplicacion.sesion.nuevaSesion(cliente);
                    System.out.println(Aplicacion.sesion.user);
                    //System.out.println("is host? " + Sesion.esAnfitrion);//true. es host
                    tipo = 3;

                } else {
                    System.out.println("Cliente no registrado");
                    tipo = 0;
                }
            }
        }
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }

        //System.out.println("el tipo es:" + tipo);
        return tipo;

    }
}
