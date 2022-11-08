package com.example.acdat_pizzeria.modelo.kotlin

import com.example.acdat_pizzeria.modelo.Usuario
import com.example.acdat_pizzeria.modelo.enums.Ingrediente
import com.example.acdat_pizzeria.modelo.enums.Queso
import com.example.acdat_pizzeria.modelo.enums.Salsa
import com.example.acdat_pizzeria.modelo.enums.Tamano
import java.io.Serializable

class Pizza : Serializable {

    companion object {
        private var contId = 0
    }

    private var idPizza: Int
    private lateinit var tamano: Tamano
    private lateinit var salsa: Salsa
    private lateinit var queso: Queso
    private lateinit var ingredientes: ArrayList<Ingrediente>
    private lateinit var usuario: Usuario
    private var favorita: Boolean? = null
    private lateinit var nombre: String

    constructor(
        tamano: Tamano,
        salsa: Salsa,
        queso: Queso,
        ingredientes: ArrayList<Ingrediente>,
        usuario: Usuario
    ) {
        idPizza = incrmentId()
        this.tamano = tamano
        this.salsa = salsa
        this.queso = queso
        this.ingredientes = ingredientes
        this.usuario = usuario
        favorita = false
        nombre = "Predeterminada"
    }

    constructor(
        salsa: Salsa,
        queso: Queso,
        ingredientes: ArrayList<Ingrediente>,
        nombre: String
    ) {
        idPizza = incrmentId()
        this.salsa = salsa
        this.queso = queso
        this.ingredientes = ingredientes
        favorita = false
        this.nombre = nombre
    }

    constructor(
        tamano: Tamano,
        salsa: Salsa,
        queso: Queso,
        ingredientes: ArrayList<Ingrediente>,
        usuario: Usuario,
        nombre: String
    ) {
        idPizza = incrmentId()
        this.tamano = tamano
        this.salsa = salsa
        this.queso = queso
        this.ingredientes = ingredientes
        this.usuario = usuario
        favorita = false
        this.nombre = nombre
    }

    constructor(pizza: Pizza) {
        idPizza = incrmentId()
        tamano = pizza.tamano
        salsa = pizza.salsa
        queso = pizza.queso
        ingredientes = pizza.ingredientes
        usuario = pizza.usuario
        favorita = pizza.favorita
        nombre = pizza.nombre
        pizza.favorita = false
    }

    constructor(idPizza: Int) {
        this.idPizza = idPizza
    }

    private fun incrmentId(): Int {
        return contId++
    }

    fun getIdPizza(): Int {
        return idPizza
    }

    fun setIdPizza(idPizza: Int) {
        this.idPizza = idPizza
    }

    fun getTamano(): Tamano {
        return tamano
    }

    fun setTamano(tamano: Tamano) {
        this.tamano = tamano
    }

    fun getSalsa(): Salsa {
        return salsa
    }

    fun setSalsa(salsa: Salsa) {
        this.salsa = salsa
    }

    fun getQueso(): Queso {
        return queso
    }

    fun setQueso(queso: Queso) {
        this.queso = queso
    }

    fun getIngredientes(): ArrayList<Ingrediente> {
        return ingredientes
    }

    fun setIngredientes(ingredientes: ArrayList<Ingrediente>) {
        this.ingredientes = ingredientes
    }

    fun getUsuario(): Usuario {
        return usuario
    }

    fun setUsuario(usuario: Usuario) {
        this.usuario = usuario
    }

    fun getFavorita(): Boolean? {
        return favorita
    }

    fun setFavorita(favorita: Boolean) {
        this.favorita = favorita
    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    val precio: Double
        get() {
            var precio = 0.0
            precio += tamano.precioInicial
            precio += salsa.precioInicial
            precio += queso.precioInicial
            for (i in ingredientes.indices) {
                precio += ingredientes[i].precioInicial
            }
            return precio
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pizza

        if (idPizza != other.idPizza) return false

        return true
    }

    override fun hashCode(): Int {
        return idPizza
    }

    override fun toString(): String {
        val cadena = StringBuilder()
        cadena.append("TAMAÑO:\n\t - ${tamano.nombre}")
        cadena.append("SALSA:\n\t - ${salsa.nombre}")
        cadena.append("QUESO:\n\t - ${queso.nombre}")
        cadena.append("INGREDIENTES:\n")
        for (i in ingredientes!!.indices) {
            cadena.append("\t - ${ingredientes!![i].nombre}\n"
            )
        }
        return cadena.toString()
    }
}