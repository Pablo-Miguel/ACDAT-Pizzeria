package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityMainBinding;
import com.example.acdat_pizzeria.databinding.ActivityRegistrarBinding;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Registrar extends AppCompatActivity implements View.OnClickListener{

    private ActivityRegistrarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        binding.btnRegistrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(binding.edTextUsuario.getText().toString().equals("") || binding.edTextPassword.getText().toString().equals("") || binding.editTextEmail.getText().toString().equals("")){

            AlertDialog.Builder dialogo1 = crearDialogo("Hay algún campo vacío", "Por favor, introduzca email, usuario y contraseña");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            dialogo1.show();

        } else{

            Usuario usuario = new Usuario(binding.editTextEmail.getText().toString(),
                    binding.edTextUsuario.getText().toString().toLowerCase(),
                    binding.edTextPassword.getText().toString());

            int comp = Servicio.getInstance().anyadirUsuario(usuario);

            if(comp == 0){
                Intent intentInicio = new Intent(Registrar.this, Inicio.class);
                intentInicio.putExtra("usuario", usuario);
                startActivity(intentInicio);
            }
            else{
                AlertDialog.Builder dialogo1 = crearDialogo("Usuario existente", "Ya existe un usuario con ese nombre");

                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                dialogo1.show();
            }

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