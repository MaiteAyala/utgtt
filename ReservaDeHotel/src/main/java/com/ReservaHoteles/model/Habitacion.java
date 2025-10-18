package com.ReservaHoteles.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Habitacion{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int habitacionId;

@Column(nullable = false)
private String numeroHabitacion;

@Column(nullable = false)
private String tipo;

@Column(nullable = false)
private double precioPorNoche;

@Column(nullable = false)
private String estado;

@Column(nullable = false, length = 100)
private String descripcion;

@Column
private String imagen; // Ruta o nombre del archivo de imagen{


    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas = new ArrayList<>();



    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Habitacion(int habitacionId, String numeroHabitacion, String tipo, double precioPorNoche, String estado, String descripcion, String imagen) {
        this.habitacionId = habitacionId;
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.estado = estado;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Habitacion() {
    }
}
