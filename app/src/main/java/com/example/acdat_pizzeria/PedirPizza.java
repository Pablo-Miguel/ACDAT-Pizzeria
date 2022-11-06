package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acdat_pizzeria.databinding.ActivityPedirPizzaBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.servicio.Servicio;

public class PedirPizza extends AppCompatActivity implements View.OnClickListener {

    private ActivityPedirPizzaBinding binding;
    private Usuario usuario;
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedirPizzaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        pizza = (Pizza) getIntent().getSerializableExtra("pizza");

        Servicio.getInstance();

        binding.btnInicio.setOnClickListener(this);
        binding.btnCerrarSesion.setOnClickListener(this);
        binding.btnPizzaFav.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnInicio:
                Intent inntentInicio = new Intent(PedirPizza.this, Inicio.class);
                inntentInicio.putExtra("usuario", usuario);
                startActivity(inntentInicio);
                break;
            case R.id.btnCerrarSesion:
                Intent intentPedirPizza = new Intent(PedirPizza.this, MainActivity.class);
                startActivity(intentPedirPizza);
                break;
            case R.id.btnPizzaFav:
                if(!pizza.getFavorita()) {
                    pizza.setFavorita(true);
                    Toast t = Toast.makeText(this, "La pizza se ha establecido como favorita",
                            Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    Toast t = Toast.makeText(this, "La pizza ya está en favoritos",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
                break;
        }
    }
}