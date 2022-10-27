package com.example.acdat_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaBinding;
import com.example.acdat_pizzeria.modelo.Usuario;

public class PizzaPredeterminada extends AppCompatActivity {

    private Usuario usuario;
    private ActivityPizzaPersonalizadaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaPersonalizadaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

    }
}