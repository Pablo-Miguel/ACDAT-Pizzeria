package com.example.acdat_pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaIngredientesBinding;
import com.example.acdat_pizzeria.databinding.ActivityPizzaPersonalizadaTamanoBinding;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.enums.Tamano;

public class PizzaPersonalizadaTamano extends AppCompatActivity implements View.OnClickListener {

    private ActivityPizzaPersonalizadaTamanoBinding binding;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPizzaPersonalizadaTamanoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding.btnContinuar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (binding.rgTamano.getCheckedRadioButtonId() == -1) {
            AlertDialog.Builder dialogo1 = crearDialogo(
                    "No hay tamaño seleccionado",
                    "Porfavor, seleccione un tamaño");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialogo1.show();
        } else if (binding.rgSalsa.getCheckedRadioButtonId() == -1) {
            AlertDialog.Builder dialogo1 = crearDialogo(
                    "No hay salsa seleccionado",
                    "Porfavor, seleccione una salsa");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialogo1.show();
        } else if (binding.rgQueso.getCheckedRadioButtonId() == -1) {
            AlertDialog.Builder dialogo1 = crearDialogo(
                    "No hay queso seleccionado",
                    "Porfavor, seleccione un queso");

            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialogo1.show();
        } else {
            Intent intentPersonalizada = new Intent(PizzaPersonalizadaTamano.this,
                    PizzaPersonalizadaIngredientes.class);

            intentPersonalizada.putExtra("usuario", usuario);

            Tamano tamano = null;
            if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbIndividual.getId()) {
                tamano = Tamano.INDIVIDUAL;
            } else if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbMediana.getId()) {
                tamano = Tamano.MEDIANA;
            } else if (binding.rgTamano.getCheckedRadioButtonId() == binding.rbFamiliar.getId()) {
                tamano = Tamano.FAMILIAR;
            }

            Salsa salsa = null;
            if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbSinSalsa.getId()) {
                salsa = Salsa.SIN_SALSA;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbBBQOriginal.getId()) {
                salsa = Salsa.BBQ_ORIGINAL;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbRancheraBBQ.getId()) {
                salsa = Salsa.RANCHERA_BBQ;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbSalsaTomate.getId()) {
                salsa = Salsa.SALSA_DE_TOMATE;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbCremaFresca.getId()) {
                salsa = Salsa.CREMA_FRESCA;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbCremaFrescaBarbacoa.getId()) {
                salsa = Salsa.CREMA_FRESCA_Y_BARBACOA;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbSalsaBourbon.getId()) {
                salsa = Salsa.SALSA_BOURBON;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbBarbacoaTexas.getId()) {
                salsa = Salsa.BARBACOA_TEXAS;
            } else if (binding.rgSalsa.getCheckedRadioButtonId() == binding.rbCarbonaraMornay.getId()) {
                salsa = Salsa.CARBONARA_MORNAY;
            }

            Queso queso = null;
            if (binding.rgQueso.getCheckedRadioButtonId() == binding.rbSinQueso.getId()) {
                queso = Queso.SIN_QUESO;
            } else if (binding.rgQueso.getCheckedRadioButtonId() == binding.rbMozzarella.getId()) {
                queso = Queso.MOZZARELLA;
            } else if (binding.rgQueso.getCheckedRadioButtonId() == binding.rbVegano.getId()) {
                queso = Queso.VEGANO;
            }

            intentPersonalizada.putExtra("tamano", tamano);
            intentPersonalizada.putExtra("salsa", salsa);
            intentPersonalizada.putExtra("queso", queso);

            startActivity(intentPersonalizada);
        }
    }

    public void onBackPressed() {

        AlertDialog.Builder dialogo1 = crearDialogo("Volver al inicio", "¿Está seguro que desea volver al inicio?\nSe perderán los cambios");

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentInicio = new Intent(PizzaPersonalizadaTamano.this, MenuOpciones.class);
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