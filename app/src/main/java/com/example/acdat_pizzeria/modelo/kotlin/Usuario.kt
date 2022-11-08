package com.example.acdat_pizzeria.modelo.kotlin

import java.io.Serializable

class Usuario(private var usuario: String) : Serializable {

    companion object{ private var contId = 0 }
    private var id: Int? = null;
    private lateinit var email: String
    private lateinit var password: String

    constructor(email: String, usuario: String, password: String): this(usuario) {
        id = incrmentId()
        this.email = email
        this.password = password
    }

    private fun incrmentId():Int {
        return contId++;
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getUsuario(): String {
        return usuario
    }

    fun setUsuario(usuario: String) {
        this.usuario = usuario
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (usuario != other.usuario) return false

        return true
    }

    override fun hashCode(): Int {
        return usuario.hashCode()
    }

    override fun toString(): String {
        return "Usuario(usuario='$usuario', id=$id, email='$email', password='$password')"
    }

}