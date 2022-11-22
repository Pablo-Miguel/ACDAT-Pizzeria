package com.example.acdat_pizzeria.servicio;

import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.modelo.Pizza;
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

    public ArrayList<Pizza> getPizzas(){

        return DAOPizzas.getInstance().getPizzas();

    }

    public ArrayList<Pizza> getPizzasPredeterminadas(){

        return DAOPizzas.getInstance().getPizzasPredeterminadas();

    }

    public Usuario getUsuario(Usuario usuario){

        return DAOPizzas.getInstance().getUsuario(usuario);

    }

    public Pizza getPizza(Pizza pizza) {
        return DAOPizzas.getInstance().getPizza(pizza);
    }

    public int anyadirUsuario(Usuario usuario) {
        return DAOPizzas.getInstance().insertarUsuario(usuario);
    }

    public Pizza anyadirPizza(Pizza pizza) {
        return DAOPizzas.getInstance().insertarPizza(pizza);
    }

    public Pizza getPizzaFav(Usuario usuario){
        ArrayList<Pizza> lista = DAOPizzas.getInstance().getPizzas();
        for(int i = 0; i < lista.size(); i++){

            if(lista.get(i).getUsuario().equals(usuario) && lista.get(i).getFavorita()){
                return lista.get(i);
            }

        }

        return null;
    }

    public void setPizzaFav(Pizza pizza, Boolean valor){
        DAOPizzas.getInstance().setPizzaFav(pizza, valor);
    }

}
