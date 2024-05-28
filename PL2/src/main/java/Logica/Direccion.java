package Logica;

import java.io.Serializable;
import java.util.Objects;

public class Direccion implements Serializable {

    private String calle;
    private String numero;
    private String cp;
    private String ciudad;

    /**
     * Constructor de la clase Direccion
     *
     * @param calle
     * @param numero
     * @param cp
     * @param ciudad
     */
    public Direccion(String calle, String numero, String cp, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
    }

    //Método que iguala direcciones por su contenido, no por su dirección de memoria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Direccion otraDireccion = (Direccion) obj;
        return Objects.equals(calle, otraDireccion.calle)
                && Objects.equals(numero, otraDireccion.numero)
                && Objects.equals(cp, otraDireccion.cp)
                && Objects.equals(ciudad, otraDireccion.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calle, numero, cp, ciudad);
    }

    //G&S
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return "Calle " + calle + ", nº" + numero + ", " + ciudad + ". CP:" + cp;
    }

}
