package com.example.acdat_pizzeria.modelo;

import android.content.ContentValues;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.daos.data.PizzaEntry;
import com.example.acdat_pizzeria.daos.data.UsuarioEntry;
import com.example.acdat_pizzeria.modelo.enums.Ingrediente;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.enums.Tamano;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Pizza implements Serializable {

    private static Integer contId = 0;
    private Integer idPizza;
    private Tamano tamano;
    private Salsa salsa;
    private Queso queso;
    private ArrayList<Ingrediente> ingredientes;
    private Usuario usuario;
    private Boolean favorita;
    private String nombre;
    private int ref_img;

    public Pizza(Integer idPizza, Tamano tamano, Salsa salsa, Queso queso, ArrayList<Ingrediente> ingredientes, Usuario usuario, String nombre) {
        this.idPizza = idPizza;
        this.tamano = tamano;
        this.salsa = salsa;
        this.queso = queso;
        this.ingredientes = ingredientes;
        this.usuario = usuario;
        this.favorita = false;
        this.nombre = nombre;
        this.ref_img = R.drawable.pizza;
    }

    public Pizza(Tamano tamano, Salsa salsa, Queso queso, ArrayList<Ingrediente> ingredientes, Usuario usuario) {
        this.idPizza = incrmentId();
        this.tamano = tamano;
        this.salsa = salsa;
        this.queso = queso;
        this.ingredientes = ingredientes;
        this.usuario = usuario;
        this.favorita = false;
        this.nombre = "Predeterminada";
        this.ref_img = R.drawable.pizza;
    }

    public Pizza(Salsa salsa, Queso queso, ArrayList<Ingrediente> ingredientes, String nombre, int ref_img) {
        this.idPizza = incrmentId();
        this.salsa = salsa;
        this.queso = queso;
        this.ingredientes = ingredientes;
        this.favorita = false;
        this.nombre = nombre;
        this.ref_img = ref_img;
    }

    /*
    public Pizza(Tamano tamano, Salsa salsa, Queso queso, ArrayList<Ingrediente> ingredientes, Usuario usuario, String nombre) {
        this.idPizza = incrmentId();
        this.tamano = tamano;
        this.salsa = salsa;
        this.queso = queso;
        this.ingredientes = ingredientes;
        this.usuario = usuario;
        this.favorita = false;
        this.nombre = nombre;
        this.ref_img = R.drawable.pizza;
    }
    */

    public Pizza(Pizza pizza) {
        this.idPizza = pizza.getIdPizza();
        this.tamano = pizza.getTamano();
        this.salsa = pizza.getSalsa();
        this.queso = pizza.getQueso();
        this.ingredientes = pizza.getIngredientes();
        this.usuario = pizza.getUsuario();
        this.favorita = pizza.getFavorita();
        this.nombre = pizza.getNombre();
        DAOPizzas.getInstance().setPizzaFav(pizza, false);
        this.ref_img = pizza.getRef_img();
    }

    public Pizza(Integer idPizza) {
        this.idPizza = idPizza;
    }

    private Integer incrmentId(){
        return Pizza.contId++;
    }

    public Integer getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(Integer idPizza) {
        this.idPizza = idPizza;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public Salsa getSalsa() {
        return salsa;
    }

    public void setSalsa(Salsa salsa) {
        this.salsa = salsa;
    }

    public Queso getQueso() {
        return queso;
    }

    public void setQueso(Queso queso) {
        this.queso = queso;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRef_img() {
        return ref_img;
    }

    public void setRef_img(int ref_img) {
        this.ref_img = ref_img;
    }

    public Double getPrecio() {
        Double precio = 0.0;

        precio += getTamano().getPrecioInicial();
        precio += getSalsa().getPrecioInicial();
        precio += getQueso().getPrecioInicial();

        for(int i = 0; i < getIngredientes().size(); i++){
            precio += getIngredientes().get(i).getPrecioInicial();
        }

        return precio;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return idPizza.equals(pizza.idPizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza);
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();

        cadena.append("TAMA??O:\n\t - " + getTamano().getNombre() + "\n");
        cadena.append("SALSA:\n\t - " + getSalsa().getNombre() + "\n");
        cadena.append("QUESO:\n\t - " + getQueso().getNombre() + "\n");
        cadena.append("INGREDIENTES:\n");

        for(int i = 0; i < getIngredientes().size(); i++){
            cadena.append("\t - " + getIngredientes().get(i).getNombre() + "\n");
        }

        return cadena.toString();
    }

    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(PizzaEntry.TAMANO, getTamano().ordinal());
        values.put(PizzaEntry.SALSA, getSalsa().ordinal());
        values.put(PizzaEntry.QUESO, getQueso().ordinal());
        values.put(UsuarioEntry.USUARIO, getUsuario().getUsuario());
        values.put(PizzaEntry.FAVORITA, getFavorita());
        values.put(PizzaEntry.NOMBRE, getNombre());

        return values;
    }

}
