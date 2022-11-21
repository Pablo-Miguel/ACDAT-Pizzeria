package com.example.acdat_pizzeria.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.acdat_pizzeria.daos.data.IngredienteEntry;
import com.example.acdat_pizzeria.daos.data.PizzaEntry;
import com.example.acdat_pizzeria.daos.data.UsuarioEntry;
import com.example.acdat_pizzeria.modelo.Usuario;

public class PizzeriaSQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PizzeriaServidor.db";

    public PizzeriaSQLiteHelper(Context context) {
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
                + PizzaEntry.USUARIO + " INTEGER, "
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
        values.put(UsuarioEntry.EMAIL, "Admin@admin.com");
        values.put(UsuarioEntry.USUARIO, "Admin");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(UsuarioEntry.EMAIL, "Pablo@pablo.com");
        values.put(UsuarioEntry.USUARIO, "Pablo");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(UsuarioEntry.EMAIL, "Marta@marta.com");
        values.put(UsuarioEntry.USUARIO, "Marta");
        values.put(UsuarioEntry.PASSWORD, "1234");
        sqLiteDatabase.insert(UsuarioEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {

    }

    /*
    public long saveUsuario(Usuario usuario) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsuarioEntry.TABLE_NAME,
                null,
                usuario.toContentValues());

    }
    */

}
