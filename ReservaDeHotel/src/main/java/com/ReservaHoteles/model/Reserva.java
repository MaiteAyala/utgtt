package com.ReservaHoteles.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservaId;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;


    @Column(name = "estado", nullable = false)
    private String estado; // Valor por defecto

    @Column(name = "total", nullable = false)
    private double total;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // Corregido
    @JoinColumn(name = "habitacionId")
    private Habitacion habitacion;


    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    /*
    // Relación uno a uno con DetallePago
    @OneToOne(mappedBy = "reserva")
    private DetallePago detallepago;*/


    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Reserva(int reservaId, LocalDate fechaInicio, LocalDate fechaFin, String estado, double total, Habitacion habitacion) {
        this.reservaId = reservaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.total = total;
        this.habitacion = habitacion;
    }


    public Reserva() {
    }
}
