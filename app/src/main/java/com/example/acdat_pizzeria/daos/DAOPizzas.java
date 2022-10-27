package com.example.acdat_pizzeria.daos;

import android.content.SyncStatusObserver;

import com.example.acdat_pizzeria.modelo.Usuario;

import java.util.ArrayList;

public class DAOPizzas {

    private static DAOPizzas dao;
    private static ArrayList<Usuario> listaUsuarios;

    public static DAOPizzas getInstance() {

        if (dao == null) {
            dao = new DAOPizzas();
        }

        if(listaUsuarios == null){
            listaUsuarios = new ArrayList<Usuario>();
        }

        return dao;
    }

    public ArrayList<Usuario> getUsuarios(){

        if(listaUsuarios.isEmpty()){
            listaUsuarios.add(new Usuario("admin@gmail.com", "admin", "1234"));
            listaUsuarios.add(new Usuario("pablo@gmail.com", "pablo", "1234"));
        }

        return listaUsuarios;

    }

    public Usuario getUsuario(Usuario usuario){
        if(DAOPizzas.getInstance().getUsuarios().contains(usuario)){
            for (Usuario usu : DAOPizzas.getInstance().getUsuarios()) {
                if(usuario.equals(usu)){
                    return usu;
                }
            }
        }

        return null;
    }

    public int insertarUsuario(Usuario usuario) {

        if(DAOPizzas.getInstance().getUsuario(usuario) == null){
            if(listaUsuarios.add(usuario)){
                return 0;
            }
        }

        return -1;

    }
}