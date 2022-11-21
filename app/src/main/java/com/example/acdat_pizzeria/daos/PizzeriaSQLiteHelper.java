package com.example.acdat_pizzeria.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.acdat_pizzeria.daos.data.IngredienteEntry;
import com.example.acdat_pizzeria.daos.data.PizzaEntry;
import com.example.acdat_pizzeria.daos.data.UsuarioEntry;
import com.example.acdat_pizzeria.modelo.Pizza;
import com.example.acdat_pizzeria.modelo.Usuario;
import com.example.acdat_pizzeria.modelo.enums.Ingrediente;
import com.example.acdat_pizzeria.modelo.enums.Queso;
import com.example.acdat_pizzeria.modelo.enums.Salsa;
import com.example.acdat_pizzeria.modelo.enums.Tamano;

import java.util.ArrayList;

public class PizzeriaSQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PizzeriaServidor.db";

    public PizzeriaSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UsuarioEntry.TABLE_NAME + " ("
                + UsuarioEntry.ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UsuarioEntry.EMAIL + " TEXT NOT NULL, "
                + UsuarioEntry.USUARIO + " TEXT NOT NULL UNIQUE, "
                + UsuarioEntry.PASSWORD + " INTEGER NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + PizzaEntry.TABLE_NAME + " ("
                + PizzaEntry.ID_PIZZA + " INTEGER, "
                + PizzaEntry.TAMANO + " INTEGER, "
                + PizzaEntry.SALSA + " INTEGER NOT NULL, "
                + PizzaEntry.QUESO + " INTEGER NOT NULL, "
                + PizzaEntry.USUARIO + " TEXT NOT NULL UNIQUE, "
                + PizzaEntry.FAVORITA + " INTEGER NOT NULL, "
                + PizzaEntry.NOMBRE + " TEXT NOT NULL, "
                + "FOREIGN KEY("
                + PizzaEntry.USUARIO + ") REFERENCES "
                + UsuarioEntry.TABLE_NAME + "("
                + UsuarioEntry.USUARIO + "), "
                + "PRIMARY KEY("
                + PizzaEntry.ID_PIZZA + "))");
        sqLiteDatabase.execSQL("CREATE TABLE " + IngredienteEntry.TABLE_NAME + " ("
                + IngredienteEntry.INGREDIENTE + " INTEGER, "
                + IngredienteEntry.ID_PIZZA + " INTEGER, "
                + IngredienteEntry.ID_INGREDIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FOREIGN KEY("
                + IngredienteEntry.ID_PIZZA + ") REFERENCES "
                + PizzaEntry.TABLE_NAME + "("
                + PizzaEntry.ID_PIZZA + "))");

        ContentValues values = new ContentValues();
        values.put(UsuarioEntry.EMAIL, "admin@admin.com");
        values.put(UsuarioEntry.USUARIO, "admin");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(UsuarioEntry.EMAIL, "pablo@pablo.com");
        values.put(UsuarioEntry.USUARIO, "pablo");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(UsuarioEntry.EMAIL, "marta@marta.com");
        values.put(UsuarioEntry.USUARIO, "marta");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {
        sqLiteDatabase.execSQL("DROP TABLE " + IngredienteEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + PizzaEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + UsuarioEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long saveUsuario(Usuario usuario){

        long id = 0;

        SQLiteDatabase db = getWritableDatabase();
        db.insert(UsuarioEntry.TABLE_NAME, null, usuario.toContentValues());

        return id;

    }

    /*
    public Usuario getUsuario(Usuario usuario){
        Usuario usuarioDB = null;

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UsuarioEntry.TABLE_NAME + " WHERE usuario=?", new String[]{usuario.getUsuario()});

        if(cursor.moveToFirst()){
            do{

                usuarioDB = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return usuarioDB;

    }
    */

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UsuarioEntry.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{

                usuarios.add(new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return usuarios;

    }

    public ArrayList<Pizza> getPizzas() {

        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        ArrayList<Ingrediente> ingredientes;

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PizzaEntry.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{

                ingredientes = getIngredientes(cursor.getInt(0));

                pizzas.add(new Pizza(cursor.getInt(0), Tamano.values()[cursor.getInt(1)], Salsa.values()[cursor.getInt(2)],
                        Queso.values()[cursor.getInt(3)], ingredientes, DAOPizzas.getInstance().getUsuario(new Usuario(cursor.getString(4)))));
                pizzas.get(pizzas.size() - 1).setFavorita(cursor.getExtras().getBoolean("favorita"));

            } while(cursor.moveToNext());
        }

        cursor.close();

        return pizzas;

    }

    public ArrayList<Ingrediente> getIngredientes(Integer id_pizza) {

        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + IngredienteEntry.TABLE_NAME + " WHERE id_pizza=?", new String[]{id_pizza.toString()});

        if(cursor.moveToFirst()){
            do{

                ingredientes.add(Ingrediente.values()[cursor.getInt(0)]);

            } while(cursor.moveToNext());
        }

        cursor.close();

        return ingredientes;
    }

}
