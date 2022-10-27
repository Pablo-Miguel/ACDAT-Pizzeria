package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityInicioBinding;
import com.example.acdat_pizzeria.databinding.ActivityRegistrarBinding;
import com.example.acdat_pizzeria.modelo.Usuario;

public class Inicio extends AppCompatActivity {

    private Usuario usuario;
    private ActivityInicioBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding.lblUsuario.setText(usuario.getUsuario());

    }

    public void onBackPressed() {
        Intent intentMainActivity = new Intent(Inicio.this, MainActivity.class);
        startActivity(intentMainActivity);
    }

}