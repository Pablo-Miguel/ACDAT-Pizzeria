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

public class Inicio extends AppCompatActivity implements View.OnClickListener {

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

        binding.btnFavorita.setOnClickListener(this);
        binding.btnPersonalizada.setOnClickListener(this);
        binding.btnPredeterminada.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFavorita:
                Intent intentPizzaFavorita = new Intent(Inicio.this, PizzaFavorita.class);
                intentPizzaFavorita.putExtra("usuario", usuario);
                startActivity(intentPizzaFavorita);
                break;
            case R.id.btnPersonalizada:
                Intent intentPizzaPersonalizada = new Intent(Inicio.this, PizzaPersonalizada.class);
                intentPizzaPersonalizada.putExtra("usuario", usuario);
                startActivity(intentPizzaPersonalizada);
                break;
            case R.id.btnPredeterminada:
                Intent intentPredeterminada = new Intent(Inicio.this, PizzaPredeterminada.class);
                intentPredeterminada.putExtra("usuario", usuario);
                startActivity(intentPredeterminada);
                break;

        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Cerrar sesión", "¿Está seguro que desea cerrar sesión?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentMainActivity = new Intent(Inicio.this, MainActivity.class);
                startActivity(intentMainActivity);
            }
        });

        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialogo1.show();

    }

    private AlertDialog.Builder crearDialogo(String titulo, String mensaje){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);

        return dialogo1;
    }

}