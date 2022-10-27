package com.example.acdat_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityMainBinding;
import com.example.acdat_pizzeria.databinding.ActivityPizzaFavoritaBinding;
import com.example.acdat_pizzeria.modelo.Usuario;

public class PizzaFavorita extends AppCompatActivity {

    private Usuario usuario;
    private ActivityPizzaFavoritaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaFavoritaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

    }
}