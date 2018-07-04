package com.apphamba.hamba.usuario.infra;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.apphamba.hamba.usuario.usuario.persistencia.ConstantePopularBanco.INSERIR_PESSOA;
import static com.apphamba.hamba.usuario.usuario.persistencia.ConstantePopularBanco.INSERIR_USUARIO;



/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbapphamba";



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(_id integer primary key  autoincrement," +
                "senha text not null, email text not null); ");


    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table usuario;");
        this.onCreate(db);
    }
}