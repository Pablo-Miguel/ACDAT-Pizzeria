package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.daos.PizzeriaSQLiteHelper;
import com.example.acdat_pizzeria.databinding.ActivityMainBinding;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private ActivityMainBinding binding;
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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        DAOPizzas.establecerConexion(new PizzeriaSQLiteHelper(MainActivity.this));

        if(preferencias.getBoolean("recordar", false)){
            Intent intentMenuOpciones = new Intent(MainActivity.this, MenuOpciones.class);
            intentMenuOpciones.putExtra("usuario", Servicio.getInstance().getUsuario(new Usuario(preferencias.getString("usuario", ""))));
            intentMenuOpciones.putExtra("recordar", true);
            startActivity(intentMenuOpciones);
        }

        binding.lblRegistrate.setOnClickListener(this);
        binding.btnIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lblRegistrate:
                Intent intentRegistro = new Intent(MainActivity.this, Registrar.class);
                startActivity(intentRegistro);
                break;
            case R.id.btnIniciarSesion:

                iniciarSesion();

                break;
        }

    }

    private void iniciarSesion() {
        if(binding.edTextUsuario.getText().toString().equals("") || binding.edTextPassword.getText().toString().equals("")){

            AlertDialog.Builder dialogo1 = crearDialogo("Usuario o contraseña vacío", "Por favor, introduzca usuario y contraseña");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            dialogo1.show();

        } else{

            Usuario usuario = new Usuario(binding.edTextUsuario.getText().toString().toLowerCase());

            if(Servicio.getInstance().getUsuario(usuario) != null){
                Boolean iniciar = Servicio.getInstance().getUsuario(usuario).getPassword().equals(binding.edTextPassword.getText().toString());

                if(iniciar){

                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putBoolean( "recordar", binding.cbRecordar.isChecked());
                    editor.commit();

                    Intent intentMenuOpciones = new Intent(MainActivity.this, MenuOpciones.class);
                    intentMenuOpciones.putExtra("usuario", Servicio.getInstance().getUsuario(usuario));
                    startActivity(intentMenuOpciones);

                }
                else{
                    AlertDialog.Builder dialogo1 = crearDialogo("Error al iniciar sesión", "El usuario o la constraseña es incorrecta");

                    dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialogo1.show();
                }

            } else {
                AlertDialog.Builder dialogo1 = crearDialogo("El usuario no existe", "Registrese para poder acceder");

                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentRegistro = new Intent(MainActivity.this, Registrar.class);
                        startActivity(intentRegistro);
                    }
                });

                dialogo1.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo1 = crearDialogo("Salir de la aplicación", "¿Desea salir de la aplicación?");

        dialogo1.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
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