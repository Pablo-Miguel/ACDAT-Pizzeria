package com.example.acdat_pizzeria.modelo.enums;

import java.io.Serializable;

public enum Salsa implements Serializable {
    SIN_SALSA(0.0), BBQ_ORIGINAL(1.5), RANCHERA_BBQ(2.0), SALSA_DE_TOMATE(1.0),
    CREMA_FRESCA(1.5), CREMA_FRESCA_Y_BARBACOA(2.5), SALSA_BOURBON(1.5),
    BARBACOA_TEXAS(2.0), CARBONARA_MORNAY(1.5);

    private Double precioInicial;

    Salsa(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }
}
