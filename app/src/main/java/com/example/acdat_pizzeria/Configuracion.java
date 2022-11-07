package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.acdat_pizzeria.databinding.ActivityConfiguracionBinding;
import com.example.acdat_pizzeria.databinding.ActivityMenuOpcionesBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{

    private ActivityConfiguracionBinding binding;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    binding.lblModoOscuro.setText("Activar modo claro");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    binding.lblModoOscuro.setText("Activar modo oscuro");
                }
            }
        });

        binding.btnBorrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(Servicio.getInstance().getPizzaFav(usuario) != null){
            Servicio.getInstance().getPizzaFav(usuario).setFavorita(false);

            AlertDialog.Builder dialogo1 = crearDialogo("Pizza favorita eliminada", "Su pizza ha sido desmarcada como favorita correctamente");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            dialogo1.show();

        }
        else {
            AlertDialog.Builder dialogo1 = crearDialogo("No tiene pizza favorita", "Usted no tiene una pizza favorita aún");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            dialogo1.show();
        }
    }

    private AlertDialog.Builder crearDialogo(String titulo, String mensaje){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);

        return dialogo1;
    }

}