package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.modelo.Usuario;

import java.util.ArrayList;

public class DAOPizzas {

    private static DAOPizzas dao;
    private ArrayList<Usuario> listaUsuarios;

    public static DAOPizzas getInstance() {

        if (dao == null) {
            dao = new DAOPizzas();
        }

        return dao;
    }

    public ArrayList<Usuario> getUsuarios(){

        if(listaUsuarios == null){
            listaUsuarios = new ArrayList<Usuario>();
            listaUsuarios.add(new Usuario("admin@gmail.com", "admin", "1234"));
        }

        return listaUsuarios;

    }

    public Usuario getUsuario(Usuario usuario){
        if(listaUsuarios.contains(usuario)){
            for (Usuario usu : listaUsuarios) {
                if(usuario.equals(usu)){
                    return usu;
                }
            }
        }

        return null;
    }

}
