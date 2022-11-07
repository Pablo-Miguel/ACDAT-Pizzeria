package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityInicioBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Inicio extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private ActivityInicioBinding binding;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferencias = getSharedPreferences("misDatos", Context.MODE_PRIVATE);

        if(preferencias.getBoolean("modoOscuro", false)){
            this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

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
                if(Servicio.getInstance().getPizzaFav(usuario) != null){
                    Intent intentPizzaFavorita = new Intent(Inicio.this, ConfirmarPizza.class);
                    intentPizzaFavorita.putExtra("usuario", usuario);
                    intentPizzaFavorita.putExtra("pizza", new Pizza(Servicio.getInstance().getPizzaFav(usuario)));
                    startActivity(intentPizzaFavorita);
                }
                else {
                    AlertDialog.Builder dialogo1 = crearDialogo("No tiene pizza favorita", "Usted no tiene una pizza favorita aún, por favor, pida una y establecela como favorita");

                    dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    dialogo1.show();
                }
                break;
            case R.id.btnPersonalizada:
                Intent intentPizzaPersonalizada = new Intent(Inicio.this, PizzaPersonalizadaTamano.class);
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

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "¿Está seguro que desea volver al inicio?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(Inicio.this, MenuOpciones.class);
                intentInicio.putExtra("usuario", usuario);
                startActivity(intentInicio);
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