package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase que contiene los ArrayLists estáticos principales de inmuebles y clientes de la aplicación y varios
 * métodos estáticos importantes de búsqueda, serialización... 
 *
 */
public class JavaBNB implements Serializable {

    private static ArrayList<Inmueble> inmuebles;
    private static ArrayList<Cliente> clientes;

    /**
     * Inicializador de los ArrayLists de la aplicación
     */
    public static void inicializadorJavaBNB() {
        inmuebles = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public static ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Método para añadir un inmueble a la lista de inmuebles, si no existe ya
     * uno con la misma dirección.
     *
     * @param inmueble. El inmueble añadido a la lista.
     * @return booleano indicando si el inmueble que se intentó añadir ya
     * existía o no.
     */
    public static boolean añadirInmueble(Inmueble inmueble) {
        boolean existeInmuebleConMismaDireccion = inmuebles.stream()
                .anyMatch(inmuebleExistente -> inmuebleExistente.getDireccion().equals(inmueble.getDireccion()));

        if (!existeInmuebleConMismaDireccion) {
            inmuebles.add(inmueble);
        } else {
            System.out.println("El inmueble ya está añadido");
        }
        return !existeInmuebleConMismaDireccion;
    }

    
   

   
    public static ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        ArrayList<Inmueble> inmueblesDisponibles = new ArrayList<>();
    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for (Inmueble inmueble : inmuebles) {
            boolean coincideCiudad = ciudad.isEmpty() || inmueble.getDireccion().getCiudad().equalsIgnoreCase(ciudad);
            boolean coincideDisponibilidad = (fechaEntrada == null && fechaSalida == null) || (inmueble.estaDisponible(fechaEntrada, fechaSalida));
            System.out.println(inmueble.estaDisponible(fechaEntrada, fechaSalida)+ "   "+ inmueble.toString());
            if (coincideCiudad && coincideDisponibilidad) {
                inmueblesDisponibles.add(inmueble);
            }
        }

        return inmueblesDisponibles;
    }

   
    public static void ordenarPorPrecioAscSF() {
        if (inmuebles != null) {
            inmuebles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    public static void ordenarPorPrecioDescSF() {
        if (inmuebles != null) {
            inmuebles.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    public static void ordenarPorTipoSF() {
        if (inmuebles != null) {
            inmuebles.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    public static void ordenarPorCalificacionAscSF() {
        if (inmuebles != null) {
            inmuebles.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    public static void ordenarPorCalificacionDescSF() {
        if (inmuebles != null) {
            inmuebles.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

    /**
     * Método para ordenar la lista de inmuebles según su precio de 
     */
    public static void ordenarPorPrecioAscCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche));
        }
    }

    public static void ordenarPorPrecioDescCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getPrecioNoche).reversed());
        }
    }

    public static void ordenarPorTipoCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparing(Inmueble::getTipo));
        }
    }

    /**
     * Método para filtrar la lista de inmuebles disponibles según tipo.
     * 
     * @param inmueblesDisponibles
     * @return un ArrayList con solamente los inmuebles disponibles que sean de tipo "casa".
     */
    public static ArrayList<Inmueble> filtrarCasas(ArrayList<Inmueble> inmueblesDisponibles) {
        ArrayList<Inmueble> casas = new ArrayList<>();
        for (Inmueble inmueble : inmueblesDisponibles) {
            if (inmueble.getTipo().equalsIgnoreCase("Casa")) {
                casas.add(inmueble);
            }
        }
        return casas;
    }

    /**
     * Método para filtrar la lista de inmuebles disponibles según tipo.
     * 
     * @param inmueblesDisponibles
     * @return un ArrayList con solamente los inmuebles disponibles que sean de tipo "Apartamento".
     */
    public static ArrayList<Inmueble> filtrarApartamentos(ArrayList<Inmueble> inmueblesDisponibles) {
        ArrayList<Inmueble> apartamentos = new ArrayList<>();
        for (Inmueble inmueble : inmueblesDisponibles) {
            if (inmueble.getTipo().equalsIgnoreCase("Apartamento")) {
                apartamentos.add(inmueble);
            }
        }
        return apartamentos;
    }

     /**
     * Método para ordenar la lista de inmuebles disponibles según su calificación de forma ascendente.
     * 
     * @param inmueblesDisponibles
     */
    public static void ordenarPorCalificacionAscCF(ArrayList<Inmueble> inmueblesDisponibles) {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    /**
     * Método para ordenar la lista de inmuebles disponibles según su calificación de forma descendente.
     * 
     * @param inmueblesDisponibles
     */
    public static void ordenarPorCalificacionDescCF(ArrayList<Inmueble> inmueblesDisponibles) {
        if (inmueblesDisponibles != null) {
            inmueblesDisponibles.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

    
    /**
     * Método para cargar los datos serializados a los ArrayLists de clientes e inmuebles de la aplicación
     * 
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
            inmuebles = (ArrayList<Inmueble>) oisInmuebles.readObject();
            istreamInmuebles.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método para serializar los ArrayLists estáticos de clientes e inmuebles de la aplicación
     * 
     */
    public static void guardarDatos() {
        try {
            if (!clientes.isEmpty()) {
                FileOutputStream ostreamClientes = new FileOutputStream("./src/main/resources/data/copiasegClientes.dat");
                ObjectOutputStream oosClientes = new ObjectOutputStream(ostreamClientes);
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
            if (!inmuebles.isEmpty()) {
                FileOutputStream ostreamInmuebles = new FileOutputStream("./src/main/resources/data/copiasegInmuebles.dat");
                ObjectOutputStream oosInmuebles = new ObjectOutputStream(ostreamInmuebles);
                oosInmuebles.writeObject(inmuebles);
                ostreamInmuebles.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar de la aplicación a un anfitrión, todos sus inmuebles y sus respectivas reservas. 
     * Una vez eliminado el anfitrión, se serializan los datos cambiados.
     * 
     * @param cliente instancia de la clase Anfitrion.
     */
    public static void eliminarAnfitrion(Cliente cliente) {
        Anfitrion anfitrion = (Anfitrion) cliente;
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getAnfitrion().equals(anfitrion)) {
                inmueble.getReservas().clear();
                inmuebles.remove(inmueble);
            }
        }
        clientes.remove(cliente);
        guardarDatos();
    }

    /**
     * Método para eliminar de la aplicación a un cliente particular, y todas las reservas que haya hecho. 
     * Una vez eliminado el particular, se serializan los datos cambiados.
     * 
     * @param cliente instancia de la clase Particular.
     */
    public static void eliminarParticular(Cliente cliente) {
        Particular particular = (Particular) cliente;
        for (Inmueble inmueble : inmuebles) {
            for (Reserva reserva : inmueble.getReservas()) {
                if (reserva.getParticular().equals(particular)) {
                    inmueble.getReservas().remove(reserva);
                    inmuebles.remove(inmueble);
                }
            }
        }
        clientes.remove(cliente);
        guardarDatos();

    }

    /**
     * Método que filtra los inmuebles de la aplicación y devuelve los de un solo anfitrión.
     * 
     * @param anfitrion del que queramos conseguir los inmuebles.
     * @return un ArrayList con los inmuebles del anfitrión introducido
     */
    public static ArrayList<Inmueble> filtrarInmueblesPorAnfitrion(Anfitrion anfitrion) {
        ArrayList<Inmueble> inmueblesAnfitrion = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getAnfitrion().getDni().equals(anfitrion.getDni())) {
                inmueblesAnfitrion.add(inmueble);
            }
        }
        return inmueblesAnfitrion;
    }
    

    /**
     * Método para eliminar de la aplicación un inmueble y todas sus reservas asociadas. 
     * Una vez eliminado el particular, se serializan los datos cambiados.
     * 
     * @param inmueble a eliminar
     */
    public static void eliminarInmueble(Inmueble inmueble) {
        // Eliminar el inmueble de la lista de inmuebles
        System.out.println("Se ha eliminado el inmueble: " + inmueble.toString());

        inmuebles.remove(inmueble);

        // Borrar las reservas asociadas al inmueble
        inmueble.getReservas().clear();

        // Guardar los cambios en el archivo de datos
        guardarDatos();
    }

}
