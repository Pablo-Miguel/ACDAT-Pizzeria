package com.example.acdat_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaIngredientesBinding;
import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaTamanoBinding;
import com.example.acdat_pizzeria.modelo.Usuario;

public class PizzaPersonalizadaTamano extends AppCompatActivity implements View.OnClickListener {

    private ActivityPizzaPersonalizadaTamanoBinding binding;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaPersonalizadaTamanoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding.btnContinuar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnContinuar:
                Intent intentPersonalizada = new Intent(PizzaPersonalizadaTamano.this, PizzaPersonalizadaIngredientes.class);
                intentPersonalizada.putExtra("usuario", usuario);
                startActivity(intentPersonalizada);
                break;
        }
    }
}