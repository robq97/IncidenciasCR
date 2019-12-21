package com.project.incidenciascr;

public class IncidenceElement {

    int image;

    String nombreIncidente, estado, detalle;

    public IncidenceElement(int image, String nombreIncidente, String estado, String detalle){
        this.image = image;
        this.nombreIncidente = nombreIncidente;
        this.estado = estado;
        this.detalle = detalle;
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

    public String getDetalle() { return detalle; }

    public void setDetalle(String detalle) { this.detalle = detalle; }
}
