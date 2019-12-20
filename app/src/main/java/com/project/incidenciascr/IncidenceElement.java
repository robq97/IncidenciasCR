package com.project.incidenciascr;

public class IncidenceElement {

    int image;

    String nombreIncidente, estado;

    public IncidenceElement(int image, String nombreIncidente, String estado){
        this.image = image;
        this.nombreIncidente = nombreIncidente;
        this.estado = estado;
    }

    public int getImage() {
        return image;
    }

    public String getNombreIncidente() {
        return nombreIncidente;
    }

    public String getEstado() {
        return estado;
    }
}
