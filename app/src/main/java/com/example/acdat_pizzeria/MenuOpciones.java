package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityMenuOpcionesBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class MenuOpciones extends AppCompatActivity implements View.OnClickListener {

    private ActivityMenuOpcionesBinding binding;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuOpcionesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding.lblUsuario.setText(usuario.getUsuario());

        binding.btnWeb.setOnClickListener(this);
        binding.btnPizza.setOnClickListener(this);
        binding.btnConfiguracion.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnWeb:

                break;
            case R.id.btnPizza:
                Intent intentInicio = new Intent(MenuOpciones.this, Inicio.class);
                intentInicio.putExtra("usuario", usuario);
                startActivity(intentInicio);
                break;
            case R.id.btnConfiguracion:

                break;

        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Cerrar sesión", "¿Está seguro que desea cerrar sesión?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentMainActivity = new Intent(MenuOpciones.this, MainActivity.class);
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