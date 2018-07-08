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
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbapphamba";



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(_id interger primary key  autoincrement," +
                "senha text not null, email text not null); ");
        db.execSQL("create table pessoa (_id interger primary key autoincrement, " +
                "nome text not null, id_usuario interger not null);");
        db.execSQL("create table titulo(_id interger primary key autoincrement," +
                "nome text not null, sinopse text, avaliacao int, generos text, criadores text);");
        db.execSQL("create table serie(_id interger primary key autoincrement," +
                "id_titulo interger, distribuidor text, quantidade_temporadas int);");
        db.execSQL("create table temporada(_id interger primary key autoincrement," +
                "id_serie interger, nome text, numero_temporada int, quantidade_episodios int, data_lancamento text ); ");
        db.execSQL("create table episodio(_id interger primary key autoincrement," +
                "id_temporada interger, nome text, numero_episodio int);");
        db.execSQL("create table filme(_id interger primary key autoincrement," +
                "id_titulo interger, duracao int);");
    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table usuario;");
        this.onCreate(db);
    }
}