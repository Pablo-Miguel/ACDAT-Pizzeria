package com.example.acdat_pizzeria.modelo;

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

    public Usuario(Integer id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
