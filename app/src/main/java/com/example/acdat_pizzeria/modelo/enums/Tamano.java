package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Tamano implements Serializable {
    INDIVIDUAL(5.5), MEDIANA(7.5), FAMILIAR(10.0);

    private Double precioInicial;

    Tamano(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }
}
