package com.example.acdat_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityConfirmarPizzaBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Tamano;

public class ConfirmarPizza extends AppCompatActivity implements View.OnClickListener {

    private ActivityConfirmarPizzaBinding binding;
    private Usuario usuario;
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmarPizzaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        pizza = (Pizza) getIntent().getSerializableExtra("pizza");

        binding.lblPizza.setText(pizza.toString());

    }

    @Override
    public void onClick(View view) {

    }
}