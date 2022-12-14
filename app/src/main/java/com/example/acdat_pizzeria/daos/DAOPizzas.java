package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.MainActivity;
import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.enums.Ingrediente;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.Usuario;

import java.util.ArrayList;

public class DAOPizzas {

    private static DAOPizzas dao;
    private static ArrayList<Usuario> listaUsuarios;
    private static ArrayList<Pizza> listaPizzas;
    private static ArrayList<Pizza> listaPizzasPredeterminadas;
    private static PizzeriaSQLiteHelper dbHelper;

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

    public static void establecerConexion(PizzeriaSQLiteHelper dbHelper){
        DAOPizzas.dbHelper = dbHelper;
    }

    public ArrayList<Usuario> getUsuarios(){

        /*
        if(listaUsuarios.isEmpty()){
            listaUsuarios.add(new Usuario("admin@gmail.com", "admin", "1234"));
            listaUsuarios.add(new Usuario("pablo@gmail.com", "pablo", "1234"));
        }
        */

        return dbHelper.getUsuarios();

    }

    public ArrayList<Pizza> getPizzas(){

        return dbHelper.getPizzas();

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
                    ingredientesPecadoCarnal, "Pecado Carnal", R.drawable.pecadocarnal));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.CREMA_FRESCA, Queso.MOZZARELLA,
                    ingredientesCarbonara, "Carbonara", R.drawable.carbonara));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.SALSA_DE_TOMATE, Queso.MOZZARELLA,
                    ingredientesExtravaganzza, "Extravaganzza", R.drawable.extravaganzza));
            listaPizzasPredeterminadas.add(new Pizza(Salsa.BBQ_ORIGINAL, Queso.MOZZARELLA,
                    ingredientesBarbacoa, "Barbacoa", R.drawable.barbacoa));

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
            if(dbHelper.saveUsuario(usuario) >= 0){
                return 0;
            }
        }

        return -1;

    }

    public Pizza insertarPizza(Pizza pizza) {

        Pizza p = dbHelper.savePizza(pizza);

        if(p != null){
            return p;
        }

        return null;

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

    public void setPizzaFav(Pizza pizza, Boolean valor) {
        dbHelper.setPizzaFav(pizza, valor);
    }
}
