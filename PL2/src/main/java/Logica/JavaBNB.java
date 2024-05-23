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

public class JavaBNB implements Serializable {

    private static ArrayList<Inmueble> inmuebles;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Inmueble> inmueblesAnfitrion;

    public static void inicializadorJavaBNB() {
        inmuebles = new ArrayList<>();
        clientes = new ArrayList<>();
        inmueblesAnfitrion = new ArrayList<>();

    }

    public static ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

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

    public static boolean añadirCliente(Cliente cliente) {
        boolean existeClienteConMismoDni = clientes.stream()
                .anyMatch(clienteExistente -> clienteExistente.getDni().equals(cliente.getDni()));

        if (!existeClienteConMismoDni) {
            clientes.add(cliente);
        } else {
            System.out.println("El cliente ya está añadido");
        }
        return !existeClienteConMismoDni;
    }

    public static ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        ArrayList<Inmueble> inmueblesDisponiblesEnCiudad = new ArrayList<>();

        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getDireccion().getCiudad().equalsIgnoreCase(ciudad) && inmueble.estaDisponible(fechaEntrada, fechaSalida)) {
                inmueblesDisponiblesEnCiudad.add(inmueble);
            }
        }

        return inmueblesDisponiblesEnCiudad;
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

    public static ArrayList<Inmueble> filtrarCasas(ArrayList<Inmueble> inmueblesDisponibles) {
        ArrayList<Inmueble> casas = new ArrayList<>();
        for (Inmueble inmueble : inmueblesDisponibles) {
            if (inmueble.getTipo().equalsIgnoreCase("Casa")) {
                casas.add(inmueble);
            }
        }
        return casas;
    }

    public static ArrayList<Inmueble> filtrarApartamentos(ArrayList<Inmueble> inmueblesDisponibles) {
        ArrayList<Inmueble> apartamentos = new ArrayList<>();
        for (Inmueble inmueble : inmueblesDisponibles) {
            if (inmueble.getTipo().equalsIgnoreCase("Apartamento")) {
                apartamentos.add(inmueble);
            }
        }
        return apartamentos;
    }

    public static void ordenarPorCalificacionAscCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion));
        }
    }

    public static void ordenarPorCalificacionDescCF(ArrayList<Inmueble> inmueblesDisponiblesEnCiudad) {
        if (inmueblesDisponiblesEnCiudad != null) {
            inmueblesDisponiblesEnCiudad.sort(Comparator.comparingDouble(Inmueble::getCalificacion).reversed());
        }
    }

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

    public static void eliminarCliente(Cliente cliente) {
        if (cliente instanceof Particular) {
            Particular particular = (Particular) cliente;
            particular.getReservas().clear();
        } else if (cliente instanceof Anfitrion) {
            Anfitrion anfitrion = (Anfitrion) cliente;
            ArrayList<Inmueble> inmueblesAnfitrion = new ArrayList<>();
            for (Inmueble inmueble : inmuebles) {
                if (inmueble.getCliente().equals(anfitrion)) {
                    inmueblesAnfitrion.add(inmueble);
                }
            }
            for (Inmueble inmueble : inmueblesAnfitrion) {
                inmueble.getReservas().clear();
                inmuebles.remove(inmueble);
            }
        }
        clientes.remove(cliente);
        guardarDatos();
    }

    public static ArrayList<Inmueble> filtrarInmueblesPorAnfitrion(Cliente anfitrion) {
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getCliente().getDni().equals(anfitrion.getDni())) {
                inmueblesAnfitrion.add(inmueble);
            }
        }
        //System.out.println(inmueblesAnfitrion);

        return inmueblesAnfitrion;
    }

    public static void eliminarReservasDeInmueble(Inmueble inmueble) {
        System.out.println("Eliminando reservas asociadas al inmueble: " + inmueble.getTitulo());
        for (Reserva reserva : inmueble.getReservas()) {
            System.out.println("Eliminando reserva: " + reserva);
            reserva.getParticular().getReservas().remove(reserva);
        }
        inmueble.getReservas().clear();
        System.out.println("Reservas eliminadas correctamente.");
    }

}
