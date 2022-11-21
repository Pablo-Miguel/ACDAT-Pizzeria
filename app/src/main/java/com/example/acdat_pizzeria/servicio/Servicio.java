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

    public int anyadirPizza(Pizza pizza) {
        return DAOPizzas.getInstance().insertarPizza(pizza);
    }

    public Pizza getPizzaFav(Usuario usuario){
        for(int i = 0; i < DAOPizzas.getInstance().getPizzas().size(); i++){

            if(DAOPizzas.getInstance().getPizzas().get(i).getUsuario().equals(usuario) && DAOPizzas.getInstance().getPizzas().get(i).getFavorita()){
                return DAOPizzas.getInstance().getPizzas().get(i);
            }

        }

        return null;
    }

    public void setPizzaFav(Pizza pizza, Boolean valor){
        DAOPizzas.getInstance().setPizzaFav(pizza, valor);
    }

}
