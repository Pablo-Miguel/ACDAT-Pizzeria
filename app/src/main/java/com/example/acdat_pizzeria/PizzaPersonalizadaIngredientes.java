package com.example.acdat_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaIngredientesBinding;
import com.example.acdat_pizzeria.modelo.Usuario;

public class PizzaPersonalizadaIngredientes extends AppCompatActivity {

    private Usuario usuario;
    private ActivityPizzaPersonalizadaIngredientesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaPersonalizadaIngredientesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

    }
}