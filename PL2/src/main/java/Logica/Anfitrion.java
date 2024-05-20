package Logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Anfitrion extends Cliente {
   
    private LocalDate fechaRegistro;
    private boolean superAnfitrion;
    private ArrayList<Inmueble> inmuebles = new ArrayList<>();

    
    public Anfitrion(String dni, String nombre, String correo, String clave, String telefono) {
        super(dni, nombre, correo, clave, telefono);
        this.fechaRegistro = LocalDate.now();
    }
    
    

    /**
     * Getters & Setters
     *
     * Get the value of inmuebles
     */
    public ArrayList<Inmueble> getInmuebles() {
        return this.inmuebles;
    }

    public void addInmuebles(Inmueble inmueble) {
        this.inmuebles.add(inmueble);
        this.setSuperAnfitrion();
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
        int calificacion=0;
        int cantidad=0;
        for (Inmueble i : this.inmuebles) {
           calificacion+=i.getCalificacion();
           cantidad++;
        }
        int notamedia = calificacion/cantidad;
        if (notamedia>=4)
            this.superAnfitrion = true;
        else
            this.superAnfitrion = false;
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
