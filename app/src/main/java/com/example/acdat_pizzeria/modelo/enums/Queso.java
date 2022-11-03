package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Queso implements Serializable {
    SIN_QUESO(-1.0), MOZZARELLA(2.0), VEGANO(2.5);

    private Double precioInicial;

    Queso(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }
}
