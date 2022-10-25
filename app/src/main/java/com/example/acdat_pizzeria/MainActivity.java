package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityMainBinding;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        binding.lblRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Registrar.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Salir de la aplicación");
        dialogo1.setMessage("¿Desea salir de la aplicación?");
        dialogo1.setCancelable(false);

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
}