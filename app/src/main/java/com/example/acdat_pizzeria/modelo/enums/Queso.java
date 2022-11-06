package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Queso implements Serializable {
    SIN_QUESO(-1.0, "Sin queso"), MOZZARELLA(2.0, "Mozzarella"), VEGANO(2.5, "Vegano");

    private Double precioInicial;
    private String nombre;

    Queso(Double precioInicial, String nombre) {
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
