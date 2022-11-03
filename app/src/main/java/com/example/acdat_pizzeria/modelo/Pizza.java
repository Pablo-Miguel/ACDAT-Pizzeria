package com.example.acdat_pizzeria.modelo;

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

    public Pizza(Integer idPizza, Tamano tamano, Salsa salsa, Queso queso, ArrayList<Ingrediente> ingredientes) {
        this.idPizza = incrmentId();
        this.tamano = tamano;
        this.salsa = salsa;
        this.queso = queso;
        this.ingredientes = ingredientes;
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
        return "Pizza{" +
                "idPizza=" + idPizza +
                ", tamano=" + tamano +
                ", salsa=" + salsa +
                ", queso=" + queso +
                ", ingredientes=" + ingredientes +
                '}';
    }

}
