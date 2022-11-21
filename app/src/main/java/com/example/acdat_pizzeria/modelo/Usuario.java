package com.example.acdat_pizzeria.modelo;

import android.content.ContentValues;

import com.example.acdat_pizzeria.daos.data.UsuarioEntry;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private static Integer contId = 0;
    private Integer id;
    private String email, usuario, password;

    public Usuario(String email, String usuario, String password) {
        this.id = incrmentId();
        this.email = email;
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(Integer id, String email, String usuario, String password) {
        this.id = id;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    private Integer incrmentId(){
        return Usuario.contId++;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario1 = (Usuario) o;
        return getUsuario().equals(usuario1.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(UsuarioEntry.EMAIL, getEmail());
        values.put(UsuarioEntry.USUARIO, getUsuario());
        values.put(UsuarioEntry.PASSWORD, getPassword());

        return values;
    }

}
