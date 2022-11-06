package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaIngredientesBinding;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Ingrediente;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.enums.Tamano;

import java.util.ArrayList;

public class PizzaPersonalizadaIngredientes extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private Tamano tamano;
    private Salsa salsa;
    private Queso queso;
    private ArrayList<Ingrediente> ingredientes;
    private ActivityPizzaPersonalizadaIngredientesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaPersonalizadaIngredientesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        tamano = (Tamano) getIntent().getSerializableExtra("tamano");
        salsa = (Salsa) getIntent().getSerializableExtra("salsa");
        queso = (Queso) getIntent().getSerializableExtra("queso");

        ingredientes = new ArrayList<Ingrediente>();

        binding.btnContinuar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        ingredientes = new ArrayList<Ingrediente>();

        if (binding.cbYork.isChecked()) {
            ingredientes.add(Ingrediente.YORK);
        }
        if (binding.cbBacon.isChecked()) {
            ingredientes.add(Ingrediente.BACON);
        }
        if (binding.cbPolloParrilla.isChecked()){
            ingredientes.add(Ingrediente.POLLO_A_LA_PARRILLA);
        }
        if (binding.cbBaconCrispy.isChecked()){
            ingredientes.add(Ingrediente.BACON_CRISPY);
        }
        if (binding.cbTernera.isChecked()){
            ingredientes.add(Ingrediente.TERNERA);
        }
        if (binding.cbPolloBuffalo.isChecked()){
            ingredientes.add(Ingrediente.POLLO_BUFFALO);
        }
        if (binding.cbPepperoni.isChecked()){
            ingredientes.add(Ingrediente.PEPPERONI);
        }
        if (binding.cbPulledPork.isChecked()){
            ingredientes.add(Ingrediente.PULLED_PORK);
        }
        if (binding.cbAnchoas.isChecked()){
            ingredientes.add(Ingrediente.ANCHOAS);
        }
        if (binding.cbAtun.isChecked()){
            ingredientes.add(Ingrediente.ATUN);
        }
        if (binding.cbVeggiChicken.isChecked()){
            ingredientes.add(Ingrediente.VEGGICHICKEN);
        }
        if (binding.cbVeggeroni.isChecked()){
            ingredientes.add(Ingrediente.VEGGERONI);
        }
        if (binding.cbChampinon.isChecked()){
            ingredientes.add(Ingrediente.CHAMPINON);
        }
        if (binding.cbAceitunasNegras.isChecked()){
            ingredientes.add(Ingrediente.ACEITUNAS_NEGRAS);
        }
        if (binding.cbCebolla.isChecked()){
            ingredientes.add(Ingrediente.CEBOLLA);
        }
        if (binding.cbCebollaCaramelizada.isChecked()){
            ingredientes.add(Ingrediente.CEBOLLA_CARAMELIZADA);
        }
        if (binding.cbTomate.isChecked()){
            ingredientes.add(Ingrediente.TOMATE);
        }
        if (binding.cbPimientoVerde.isChecked()){
            ingredientes.add(Ingrediente.PIMIENTO_VERDE);
        }
        if (binding.cbPina.isChecked()){
            ingredientes.add(Ingrediente.PINA);
        }
        if (binding.cbMaiz.isChecked()){
            ingredientes.add(Ingrediente.MAIZ);
        }
        if (binding.cbQuesoParmesano.isChecked()){
            ingredientes.add(Ingrediente.QUESO_PARMESANO);
        }

        if (ingredientes.size() < 3) {
            AlertDialog.Builder dialogo1 = crearDialogo(
                    "No hay suficientes ingredientes seleccionados",
                    "Porfavor, seleccione mínimo 3 ingredintes");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialogo1.show();

        } else {
            Intent intentPersonalizada = new Intent(PizzaPersonalizadaIngredientes.this,
                    ConfirmarPizza.class);

            intentPersonalizada.putExtra("usuario", usuario);
            intentPersonalizada.putExtra("pizza", new Pizza(tamano, salsa, queso, ingredientes, usuario));

            startActivity(intentPersonalizada);
        }

    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "¿Está seguro que desea volver al inicio?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(PizzaPersonalizadaIngredientes.this, MenuOpciones.class);
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