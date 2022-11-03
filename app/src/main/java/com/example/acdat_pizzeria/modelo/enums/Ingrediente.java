package com.example.acdat_pizzeria.modelo.enums;

public enum Ingrediente {
    YORK(2.0), BACON(2.0), POLLO_A_LA_PARRILLA(2.0), BACON_CRISPY(2.0),
    TERNERA(2.0), POLLO_BUFFALO(2.0), PEPPERONI(2.0), PULLED_PORK(2.0),
    ANCHOAS(2.0), ATUN(2.0), VEGGICHICKEN(2.0), VEGGERONI(2.0),
    CHAMPINON(2.0), ACEITUNAS_NEGRAS(2.0), CEBOLLA(2.0), CEBOLLA_CARAMELIZADA(2.0),
    TOMATE(2.0), PIMIENTO_VERDE(2.0), PINA(2.0), MAIZ(2.0),
    QUESO_PARMESANO(2.0);

    private Double precioInicial;

    Ingrediente(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }
}
