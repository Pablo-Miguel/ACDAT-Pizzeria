package com.example.acdat_pizzeria.daos.data;

import android.provider.BaseColumns;

public abstract class UsuarioEntry implements BaseColumns {
    public static final String TABLE_NAME ="Usuario";

    public static final String ID_USUARIO = "id_usuario";
    public static final String EMAIL = "email";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
}
