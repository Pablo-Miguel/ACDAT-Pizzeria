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
import android.widget.Toast;

import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.databinding.ActivityPedirPizzaBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class PedirPizza extends AppCompatActivity implements View.OnClickListener {

    private ActivityPedirPizzaBinding binding;
    private Usuario usuario;
    private Pizza pizza, pizzaPreferida;
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

        binding = ActivityPedirPizzaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        pizza = (Pizza) getIntent().getSerializableExtra("pizza");

        binding.btnInicio.setOnClickListener(this);
        binding.btnCerrarSesion.setOnClickListener(this);
        binding.btnPizzaFav.setOnClickListener(this);

        pizzaPreferida = Servicio.getInstance().getPizzaFav(usuario);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInicio:
                Intent inntentInicio = new Intent(PedirPizza.this, MenuOpciones.class);
                inntentInicio.putExtra("usuario", usuario);
                startActivity(inntentInicio);
                break;
            case R.id.btnCerrarSesion:
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putBoolean( "recordar", false);
                editor.putString("usuario", "");
                editor.putString("password", "");
                editor.commit();
                Intent intentPedirPizza = new Intent(PedirPizza.this, MainActivity.class);
                startActivity(intentPedirPizza);
                break;
            case R.id.btnPizzaFav:
                if (Servicio.getInstance().getPizza(pizza) != null) {
                    if(!Servicio.getInstance().getPizza(pizza).getFavorita()){
                        Servicio.getInstance().setPizzaFav(Servicio.getInstance().getPizza(pizza), true);

                        AlertDialog.Builder dialogo1 = crearDialogo(
                                "Pizza favorita",
                                "Se ha establecido la pizza como favorita");

                        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                        dialogo1.show();

                        if (pizzaPreferida != null) {
                            Servicio.getInstance().setPizzaFav(pizzaPreferida, false);
                        }

                    } else {

                        AlertDialog.Builder dialogo1 = crearDialogo(
                                "Pizza favorita",
                                "La pizza ya está en favoritos");

                        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                        dialogo1.show();
                    }
                }
                break;
        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "¿Está seguro que desea volver al inicio?");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(PedirPizza.this, MenuOpciones.class);
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