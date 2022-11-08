package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.enums.Ingrediente;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.kotlin.Usuario;

import java.util.ArrayList;

public class DAOPizzas {

    private static DAOPizzas dao;
    private static ArrayList<Usuario> listaUsuarios;
    private static ArrayList<Pizza> listaPizzas;
    private static ArrayList<Pizza> listaPizzasPredeterminadas;

    public static DAOPizzas getInstance() {

        if (dao == null) {
            dao = new DAOPizzas();
        }

        if(listaUsuarios == null){
            listaUsuarios = new ArrayList<Usuario>();
        }

        if(listaPizzas == null){
            listaPizzas = new ArrayList<Pizza>();
        }

        if(listaPizzasPredeterminadas == null){
            listaPizzasPredeterminadas = new ArrayList<Pizza>();
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

    public ArrayList<Pizza> getPizzas(){

        return listaPizzas;

    }

    public ArrayList<Pizza> getPizzasPredeterminadas(){

        if(listaPizzasPredeterminadas.isEmpty()){

            ArrayList<Ingrediente> ingredientesPecadoCarnal = new ArrayList<Ingrediente>();
            ingredientesPecadoCarnal.add(Ingrediente.POLLO_BUFFALO);
            ingredientesPecadoCarnal.add(Ingrediente.BACON);
            ingredientesPecadoCarnal.add(Ingrediente.PEPPERONI);
            ingredientesPecadoCarnal.add(Ingrediente.YORK);

            ArrayList<Ingrediente> ingredientesCarbonara = new ArrayList<Ingrediente>();
            ingredientesCarbonara.add(Ingrediente.BACON);
            ingredientesCarbonara.add(Ingrediente.CHAMPINON);
            ingredientesCarbonara.add(Ingrediente.CEBOLLA);

            ArrayList<Ingrediente> ingredientesExtravaganzza = new ArrayList<Ingrediente>();
            ingredientesExtravaganzza.add(Ingrediente.POLLO_BUFFALO);
            ingredientesExtravaganzza.add(Ingrediente.BACON);
            ingredientesExtravaganzza.add(Ingrediente.PEPPERONI);
            ingredientesExtravaganzza.add(Ingrediente.YORK);
            ingredientesExtravaganzza.add(Ingrediente.CEBOLLA);
            ingredientesExtravaganzza.add(Ingrediente.PIMIENTO_VERDE);
            ingredientesExtravaganzza.add(Ingrediente.CHAMPINON);
            ingredientesExtravaganzza.add(Ingrediente.ACEITUNAS_NEGRAS);

            ArrayList<Ingrediente> ingredientesBarbacoa = new ArrayList<Ingrediente>();
            ingredientesBarbacoa.add(Ingrediente.POLLO_BUFFALO);
            ingredientesBarbacoa.add(Ingrediente.BACON);
            ingredientesBarbacoa.add(Ingrediente.CEBOLLA);
            ingredientesBarbacoa.add(Ingrediente.MAIZ);

            listaPizzasPredeterminadas.add(new Pizza(Salsa.SALSA_DE_TOMATE, Queso.MOZZARELLA,
                    ingredientesPecadoCarnal, "Pecado Carnal"));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.CREMA_FRESCA, Queso.MOZZARELLA,
                    ingredientesCarbonara, "Carbonara"));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.SALSA_DE_TOMATE, Queso.MOZZARELLA,
                    ingredientesExtravaganzza, "Extravaganzza"));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.BBQ_ORIGINAL, Queso.MOZZARELLA,
                    ingredientesBarbacoa, "Barbacoa"));

        }

        return listaPizzasPredeterminadas;

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

    public int insertarPizza(Pizza pizza) {

        if(DAOPizzas.getInstance().getPizza(pizza) == null){
            if(listaPizzas.add(pizza)){
                return 0;
            }
        }

        return -1;

    }

    public Pizza getPizza(Pizza pizza) {
        if(DAOPizzas.getInstance().getPizzas().contains(pizza)){
            for (Pizza piz : DAOPizzas.getInstance().getPizzas()) {
                if(pizza.equals(piz)){
                    return piz;
                }
            }
        }

        return null;
    }

}
