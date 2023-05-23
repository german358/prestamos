package com.example.prestamos.entities;

import javax.persistence.*;

@Entity
@Table(name = "tipoprestamo")
public class TipoPrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edadmaxima")
    private int edadaMaxima;
    @Column(name = "montomaximo")
    private int montoMaximo;
    @Column(name ="cantidadmaximameses")
    private int cantidadMaximaMeses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadaMaxima() {
        return edadaMaxima;
    }

    public void setEdadaMaxima(int edadaMaxima) {
        this.edadaMaxima = edadaMaxima;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public int getCantidadMaximaMeses() {
        return cantidadMaximaMeses;
    }

    public void setCantidadMaximaMeses(int cantidadMaximaMeses) {
        this.cantidadMaximaMeses = cantidadMaximaMeses;
    }
}
