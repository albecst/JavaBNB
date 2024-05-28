package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.nio.file.Paths;

/**
 * La clase Reserva representa una reserva realizada por un cliente para un
 * inmueble.
 */
public class Reserva implements Serializable {

    // Atributos de la clase
    private Inmueble inmueble; // El inmueble que se reserva
    private Particular particular; // El cliente que realiza la reserva
    private LocalDate fechaInicio; // Fecha de inicio de la estancia
    private LocalDate fechaFin; // Fecha de fin de la estancia
    private LocalDate fechaReserva; // Fecha en que se hizo la reserva

    /**
     * Constructor de la clase Reserva.
     *
     * @param particular El cliente que realiza la reserva
     * @param inmueble El inmueble que se reserva
     * @param fechaInicio La fecha de inicio de la estancia
     * @param fechaFin La fecha de fin de la estancia La fecha de reserva será
     * la fecha del momento en el que se llame al constructor
     */
    public Reserva(Particular particular, Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin) {
        this.inmueble = inmueble;
        this.particular = particular;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaReserva = LocalDate.now(); // La fecha de reserva es la fecha actual
    }

    /**
     * Calcula el precio total de la reserva.
     *
     * @return El costo total de la estancia
     */
    public double calcularPrecioTotal() {
        if (fechaInicio == null || fechaFin == null) {
            return 0.0; 
        }
        long diasEstancia = ChronoUnit.DAYS.between(fechaInicio, fechaFin); // Calcula el número de días entre la fecha de inicio y la fecha de fin
        double costoTotal = diasEstancia * inmueble.getPrecioNoche(); // Calcula el costo total de la estancia
        if (particular.isVip()) { // Aplica un descuento del 10% si el cliente es VIP
            costoTotal *= 0.9;
        }
        return costoTotal;
    }

    // Métodos Getters y Setters
    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Particular getParticular() {
        return particular;
    }

    public void setParticular(Particular particular) {
        this.particular = particular;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Genera un archivo de texto con la información de la reserva.
     *
     * @param directorio El directorio donde se guardará el archivo
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public void generaFicha(String directorio) throws IOException {
        // Asegúrate de que el directorio termine con una barra diagonal
        if (!directorio.endsWith(File.separator)) {
            directorio += File.separator;
        }

        // Crea el directorio si no existe
        File dir = new File(directorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Nombre del archivo basado en el DNI del particular y el título del inmueble
        String nombreFichero = this.particular.getDni() + "_" + this.inmueble.getTitulo();

        // Reemplazar caracteres especiales y eliminar caracteres no permitidos
        nombreFichero = nombreFichero
                .replaceAll("[ñ]", "n")
                .replaceAll("[á]", "a")
                .replaceAll("[é]", "e")
                .replaceAll("[í]", "i")
                .replaceAll("[ó]", "o")
                .replaceAll("[ú]", "u")
                .replaceAll("[Ñ]", "N")
                .replaceAll("[Á]", "A")
                .replaceAll("[É]", "E")
                .replaceAll("[Í]", "I")
                .replaceAll("[Ó]", "O")
                .replaceAll("[Ú]", "U")
                .replaceAll("[<>:\"/\\|?*]", "")
                .replaceAll("\\s+", "_"); // Reemplaza espacios por guiones bajos

        // Limitar la longitud del nombre del archivo
        if (nombreFichero.length() > 50) {
            nombreFichero = nombreFichero.substring(0, 50);
        }

        // Crear el archivo para escribir la información
        String filePath = Paths.get(directorio, nombreFichero + ".txt").toString();
        PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));

        // Formateador de fechas
        DateTimeFormatter formatoCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Escribir la información de la reserva en el archivo
        salida.println("-------------------------------- Reserva --------------------------------");
        salida.println("\n");
        salida.println("DNI: " + this.particular.getDni());
        salida.println("\n");
        salida.println("Nombre: " + this.particular.getNombre());
        salida.println("\n");
        salida.println("Fecha en que se hizo la reserva: " + fechaReserva.format(formatoCorto));
        salida.println("\n");
        salida.println("Dirección del inmueble: " + this.inmueble.getDireccion().toString());
        salida.println("\n");
        salida.println("Tipo de inmueble: " + this.inmueble.getTipo());
        salida.println("\n");
        salida.println("Título del inmueble: " + this.inmueble.getTitulo());
        salida.println("\n");
        salida.println("Fecha de llegada: " + fechaInicio.format(formatoCorto));
        salida.println("\n");
        salida.println("Fecha de salida: " + fechaFin.format(formatoCorto));
        salida.println("\n");
        salida.println("Precio: " + calcularPrecioTotal());
        salida.println("\n");
        salida.println("-------------------------------------------------------------------------------");
        salida.close();
    }

    @Override
    public String toString() {
        return "Reserva{" + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaReserva=" + fechaReserva + '}';
    }
}
