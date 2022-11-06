package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Tamano implements Serializable {
    INDIVIDUAL(5.5, "Individual"),
    MEDIANA(7.5, "Mediana"),
    FAMILIAR(10.0, "Familiar");

    private Double precioInicial;
    private String nombre;

    Tamano(Double precioInicial, String nombre) {
        this.precioInicial = precioInicial;
        this.nombre = nombre;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public String getNombre() {
        return nombre;
    }
}
