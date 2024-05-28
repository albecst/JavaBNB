package Logica;

import java.io.Serializable;

/**
 * La clase Resenia representa una reseña añadida a un inmueble, con una nota y un comentario.
 */
public class Resenia implements Serializable {
    
    private String comentario;
    private double nota;

    public Resenia(String comentario, double nota) {
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Rese\u00f1a{" + "comentario=" + comentario + ", nota=" + nota + '}';
    }

    
    
}