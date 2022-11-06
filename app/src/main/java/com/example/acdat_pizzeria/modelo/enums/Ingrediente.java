package com.example.acdat_pizzeria.modelo.enums;

public enum Ingrediente {
    YORK(2.0, "York"), BACON(2.0, "Bacon"),
    POLLO_A_LA_PARRILLA(2.0, "Pollo a la parrilla"), BACON_CRISPY(2.0, "Bacon Crispy"),
    TERNERA(2.0, "Ternera"), POLLO_BUFFALO(2.0, "Pollo buffalo"),
    PEPPERONI(2.0, "Pepperoni"), PULLED_PORK(2.0, "Pulled pork"),
    ANCHOAS(2.0, "Anchoas"), ATUN(2.0, "Atun"),
    VEGGICHICKEN(2.0, "VeggiChicken"), VEGGERONI(2.0, "Veggeroni"),
    CHAMPINON(2.0, "Champiñon"), ACEITUNAS_NEGRAS(2.0, "Aceitunas negras"),
    CEBOLLA(2.0, "Cebolla"), CEBOLLA_CARAMELIZADA(2.0, "Cebolla caramelizada"),
    TOMATE(2.0, "Tomate"), PIMIENTO_VERDE(2.0, "Pimiento verde"),
    PINA(2.0, "Piña"), MAIZ(2.0, "Maiz"),
    QUESO_PARMESANO(2.0, "Queso Parmesano");

    private Double precioInicial;
    private String nombre;

    Ingrediente(Double precioInicial, String nombre) {
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
