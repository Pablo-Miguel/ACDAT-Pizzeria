package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPredeterminadaBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Tamano;
import com.example.acdat_pizzeria.servicio.Servicio;
import com.example.acdat_pizzeria.vista.PizzasAdapter;

import java.util.ArrayList;

public class PizzaPredeterminada extends AppCompatActivity {

    private Usuario usuario;
    private ActivityPizzaPredeterminadaBinding binding;
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

        binding = ActivityPizzaPredeterminadaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        PizzasAdapter pizzasAdapter = new PizzasAdapter(Servicio.getInstance().getPizzasPredeterminadas());

        pizzasAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rgTamano.getCheckedRadioButtonId() == -1) {
                    AlertDialog.Builder dialogo1 = crearDialogo(
                            "No hay tama??o seleccionado",
                            "Porfavor, seleccione un tama??o");

                    dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialogo1.show();
                } else{
                    Pizza pizza = new Pizza(Servicio.getInstance().getPizzasPredeterminadas().get(binding.recicler.getChildAdapterPosition(view)));

                    Tamano tamano = null;
                    if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbIndividual.getId()) {
                        tamano = Tamano.INDIVIDUAL;
                    } else if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbMediana.getId()) {
                        tamano = Tamano.MEDIANA;
                    } else if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbFamiliar.getId()) {
                        tamano = Tamano.FAMILIAR;
                    }
                    pizza.setTamano(tamano);

                    pizza.setUsuario(usuario);

                    Intent intentPizzaPredeterminada = new Intent(PizzaPredeterminada.this,
                            ConfirmarPizza.class);

                    intentPizzaPredeterminada.putExtra("usuario", usuario);
                    intentPizzaPredeterminada.putExtra("pizza", pizza);

                    startActivity(intentPizzaPredeterminada);

                }
            }
        });

        binding.recicler.setAdapter(pizzasAdapter);
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "??Est?? seguro que desea volver al inicio?\nSe perder??n los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(PizzaPredeterminada.this, MenuOpciones.class);
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