package com.example.acdat_pizzeria.modelo.enums.kotlin

import java.io.Serializable

enum class Tamano(private var precioInicial: Double, private val nombre: String) : Serializable {
    INDIVIDUAL(5.5, "Individual"), MEDIANA(7.5, "Mediana"),
    FAMILIAR(10.0, "Familiar");

    open fun getPrecioInicial(): Double {
        return precioInicial
    }

    open fun setPrecioInicial(precioInicial: Double) {
        this.precioInicial = precioInicial
    }

    open fun getNombre(): String {
        return nombre
    }
}