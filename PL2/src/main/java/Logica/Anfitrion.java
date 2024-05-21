package Logica;

import java.time.LocalDate;
import java.util.ArrayList;
import Logica.JavaBNB;

public class Anfitrion extends Cliente {

    private LocalDate fechaRegistro;
    private boolean superAnfitrion;

    public Anfitrion(String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.fechaRegistro = LocalDate.now();
        this.superAnfitrion = false;
    }

    /**
     * Get the value of superanfitrion
     *
     * @return the value of superanfitrion
     */
    public boolean isSuperAnfitrion() {
        return superAnfitrion;
    }

    /**
     * Set the value of superanfitrion
     */
    public void setSuperAnfitrion() {
        int calificacion = 0;
        int cantidad = 0;
        for (Inmueble i : JavaBNB.getInmuebles()) {
            if (i.getCliente().getDni().equals(i.getCliente().getDni())) {
                calificacion += i.getCalificacion();
                cantidad++;
            }
            int notamedia = calificacion / cantidad;
            if (notamedia >= 4) {
                this.superAnfitrion = true;
            } else {
                this.superAnfitrion = false;
            }
        }
    }

    public void añadirInmueble(Inmueble inmueble) {
        JavaBNB.añadirInmueble(inmueble);
        JavaBNB.guardarDatos();
    }

    /**
     * Get the value of fechaRegistro
     *
     * @return the value of fechaRegistro
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Set the value of fechaRegistro
     *
     * @param fechaRegistro new value of fechaRegistro
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return super.toString() + ", fecha de registro:" + fechaRegistro + ", Super anfitrión:" + superAnfitrion;
    }

}
