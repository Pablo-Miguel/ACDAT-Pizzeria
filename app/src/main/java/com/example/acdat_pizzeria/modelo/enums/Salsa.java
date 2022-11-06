package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Salsa implements Serializable {
    SIN_SALSA(0.0, "Sin salsa"), BBQ_ORIGINAL(1.5, "BBQ Original"),
    RANCHERA_BBQ(2.0, "Ranchera BBQ"), SALSA_DE_TOMATE(1.0, "Salsa de tomate"),
    CREMA_FRESCA(1.5, "Crema fresca"), CREMA_FRESCA_Y_BARBACOA(2.5, "Crema fresca y barbacoa"),
    SALSA_BOURBON(1.5, "Salsa Bourbon"), BARBACOA_TEXAS(2.0, "Barbacoa Texas"),
    CARBONARA_MORNAY(1.5, "Carbonara Mornay");

    private Double precioInicial;
    private String nombre;

    Salsa(Double precioInicial, String nombre) {
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
