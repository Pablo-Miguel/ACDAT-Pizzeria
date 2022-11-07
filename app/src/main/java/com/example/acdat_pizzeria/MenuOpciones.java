package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

        binding = ActivityMenuOpcionesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("password", usuario.getPassword());
        editor.commit();

        binding.lblUsuario.setText(usuario.getUsuario());

        binding.btnWeb.setOnClickListener(this);
        binding.btnLlamar.setOnClickListener(this);
        binding.btnPizza.setOnClickListener(this);
        binding.btnConfiguracion.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnWeb:
                String url = "http://www.pizzerialabriciola.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btnLlamar:
                String phone = "+34934321933";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                break;
            case R.id.btnPizza:
                Intent intentInicio = new Intent(MenuOpciones.this, Inicio.class);
                intentInicio.putExtra("usuario", usuario);
                startActivity(intentInicio);
                break;
            case R.id.btnConfiguracion:
                Intent intentConfiguracion = new Intent(MenuOpciones.this, Configuracion.class);
                intentConfiguracion.putExtra("usuario", usuario);
                startActivity(intentConfiguracion);
                break;

        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Cerrar sesión", "¿Está seguro que desea cerrar sesión?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putBoolean( "recordar", false);
                editor.putString("usuario", "");
                editor.putString("password", "");
                editor.commit();
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