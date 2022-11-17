package com.example.acdat_pizzeria.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                + UsuarioEntry.ID_USUARIO + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsuarioEntry.EMAIL + "TEXT NOT NULL,"
                + UsuarioEntry.USUARIO + "TEXT NOT NULL UNIQUE,"
                + UsuarioEntry.PASSWORD + "INTEGER NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + PizzaEntry.TABLE_NAME + " ("
                + PizzaEntry.ID_PIZZA + "INTEGER,"
                + PizzaEntry.TAMANO + "INTEGER,"
                + PizzaEntry.SALSA + "INTEGER NOT NULL,"
                + PizzaEntry.QUESO + "INTEGER NOT NULL,"
                + PizzaEntry.USUARIO + "INTEGER,"
                + PizzaEntry.FAVORITA + "INTEGER NOT NULL,"
                + PizzaEntry.NOMBRE + "TEXT NOT NULL,"
                + "FOREIGN KEY("
                + PizzaEntry.USUARIO + ") REFERENCES "
                + UsuarioEntry.TABLE_NAME + "("
                + UsuarioEntry.USUARIO + "),"
                + "PRIMARY KEY("
                + PizzaEntry.ID_PIZZA + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {

    }
}
