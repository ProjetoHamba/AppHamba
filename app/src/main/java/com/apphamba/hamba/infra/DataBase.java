package com.apphamba.hamba.infra;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbapphamba";

    public DataBase() {
        super(HambaApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "senha TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "excluido TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE pessoa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "id_usuario INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE titulo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL, " +
                "sinopse TEXT, " +
                "avaliacao INTEGER, " +
                "generos TEXT, " +
                "criadores TEXT, " +
                "imagem BLOB);");

        db.execSQL("CREATE TABLE favorito (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_titulo INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE serie(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo INTEGER, " +
                "distribuidor TEXT, " +
                "quantidade_temporada INTEGER);");

        db.execSQL("CREATE TABLE temporada(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_serie INTEGER, " +
                "nome TEXT, " +
                "numero_temporada INTEGER, " +
                "quantidade_episodio INTEGER, " +
                "data_lancamento TEXT ); ");

        db.execSQL("CREATE TABLE episodio(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_temporada INTEGER, " +
                "nome TEXT, " +
                "numero_episodio INTEGER);");

        db.execSQL("CREATE TABLE filme(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo INTEGER, " +
                "duracao INTEGER);");

        db.execSQL("INSERT INTO favorito (`id_usuario`, `id_titulo`) VALUES (1,1)");
        db.execSQL("INSERT INTO favorito (`id_usuario`, `id_titulo`) VALUES (1,2)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario;");
        db.execSQL("DROP TABLE pessoa;");
        db.execSQL("DROP TABLE titulo;");
        db.execSQL("DROP TABLE serie;");
        db.execSQL("DROP TABLE temporada;");
        db.execSQL("DROP TABLE episodio;");
        db.execSQL("DROP TABLE filme;");
        this.onCreate(db);
    }
}

