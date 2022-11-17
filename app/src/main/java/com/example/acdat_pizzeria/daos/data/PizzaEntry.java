package com.example.acdat_pizzeria.daos.data;

import android.provider.BaseColumns;

public abstract class PizzaEntry implements BaseColumns {
    public static final String TABLE_NAME ="Pizza";

    public static final String ID_PIZZA = "id_pizza";
    public static final String TAMANO = "tamano";
    public static final String SALSA = "salsa";
    public static final String QUESO = "queso";
    public static final String USUARIO = "usuario";
    public static final String FAVORITA = "favorita";
    public static final String NOMBRE = "nombre";
}
