package com.example.acdat_pizzeria.servicio;

import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.modelo.Usuario;

import java.util.ArrayList;

public class Servicio {

    private static Servicio serv;
    private static ArrayList<Usuario> listaUsuarios;

    public Servicio() {
        listaUsuarios = DAOPizzas.getInstance().getUsuarios();
    }

    public static Servicio getInstance() {

        if (serv == null) {
            serv = new Servicio();
        }

        if(listaUsuarios == null){
            listaUsuarios = new ArrayList<Usuario>();
        }

        return serv;
    }

    public ArrayList<Usuario> getUsuarios(){

        return DAOPizzas.getInstance().getUsuarios();

    }

    public Usuario getUsuario(Usuario usuario){

        return DAOPizzas.getInstance().getUsuario(usuario);

    }

    public int anyadirUsuario(Usuario usuario) {
        return DAOPizzas.getInstance().insertarUsuario(usuario);
    }
}
