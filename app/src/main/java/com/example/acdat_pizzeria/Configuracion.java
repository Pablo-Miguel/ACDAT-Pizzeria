package com.example.acdat_pizzeria;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.acdat_pizzeria.databinding.ActivityConfiguracionBinding;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{

    private ActivityConfiguracionBinding binding;
    private Usuario usuario;
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

        binding = ActivityConfiguracionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        if(preferencias.getBoolean("modoOscuro", false)){
            binding.btnClaroOscuro.setText("Oscuro");
            binding.lblModoOscuro.setText("Desactivar modo oscuro");
        }
        else {
            binding.btnClaroOscuro.setText("Claro");
            binding.lblModoOscuro.setText("Activar modo oscuro");
        }

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        binding.btnClaroOscuro.setOnClickListener(this);
        binding.btnBorrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnClaroOscuro:
                if(getDelegate().getLocalNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    binding.btnClaroOscuro.setText("Claro");
                    binding.lblModoOscuro.setText("Activar modo oscuro");
                    this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    preferencias = getSharedPreferences("misDatos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putBoolean("modoOscuro", false);
                    editor.commit();
                } else {
                    binding.btnClaroOscuro.setText("Oscuro");
                    binding.lblModoOscuro.setText("Desactivar modo oscuro");
                    this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    preferencias = getSharedPreferences("misDatos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putBoolean("modoOscuro", true);
                    editor.commit();
                }
                break;
            case R.id.btnBorrar:
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
                break;
        }
    }

    public void onBackPressed() {

        Intent intentMenuOpciones = new Intent(Configuracion.this, MenuOpciones.class);
        intentMenuOpciones.putExtra("usuario", usuario);
        startActivity(intentMenuOpciones);

    }

    private AlertDialog.Builder crearDialogo(String titulo, String mensaje){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);

        return dialogo1;
    }

}