package com.example.acdat_pizzeria.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PizzeriaSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE \"Usuario\" (\n" +
            "\t\"id_usuario\"\tINTEGER,\n" +
            "\t\"email\"\tTEXT NOT NULL,\n" +
            "\t\"usuario\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"password\"\tINTEGER NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_usuario\" AUTOINCREMENT))";

    public PizzeriaSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_nueva) {

    }
}
