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

import com.example.acdat_pizzeria.databinding.ActivityConfirmarPizzaBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Tamano;
import com.example.acdat_pizzeria.servicio.Servicio;

public class ConfirmarPizza extends AppCompatActivity implements View.OnClickListener {

    private ActivityConfirmarPizzaBinding binding;
    private Usuario usuario;
    private Pizza pizza;
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

        binding = ActivityConfirmarPizzaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        pizza = (Pizza) getIntent().getSerializableExtra("pizza");

        binding.lblPizza.setText(pizza.toString());
        binding.lblPrecio.setText(pizza.getPrecio() + "€");

        binding.btnAceptar.setOnClickListener(this);
        binding.btnCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAceptar:
                pizza = Servicio.getInstance().anyadirPizza(pizza);
                if(pizza != null){
                    Intent intentPedirPizza = new Intent(ConfirmarPizza.this, PedirPizza.class);
                    intentPedirPizza.putExtra("usuario", usuario);
                    intentPedirPizza.putExtra("pizza", pizza);
                    startActivity(intentPedirPizza);
                }
                else{
                    AlertDialog.Builder dialogo1 = crearDialogo(
                            "Vaya ha ocurrido un error",
                            "Se ha priducido un error con su pizza, por favor, intentelo de nuevo");

                    dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    dialogo1.show();
                }
                break;
            case R.id.btnCancelar:
                AlertDialog.Builder dialogo1 = crearDialogo(
                        "Cancelar pedido",
                        "¿Está seguro que desea cancelar el pedido?");

                dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentVolverInicio = new Intent(ConfirmarPizza.this,
                                Inicio.class);
                        intentVolverInicio.putExtra("usuario", usuario);
                        startActivity(intentVolverInicio);
                    }
                });

                dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialogo1.show();

                break;
        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "¿Está seguro que desea volver al inicio?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(ConfirmarPizza.this, MenuOpciones.class);
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