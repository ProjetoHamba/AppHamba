package com.apphamba.hamba.infra;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbapphamba";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "senha text NOT NULL, " +
                "email text NOT NULL); ");

        db.execSQL("CREATE TABLE pessoa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome text NOT NULL, " +
                "id_usuario interger NOT NULL);");

        db.execSQL("CREATE TABLE titulo(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome text NOT NULL, " +
                "sinopse text, " +
                "avaliacao int, " +
                "generos text, " +
                "criadores text);");

        db.execSQL("CREATE TABLE serie(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo interger, " +
                "distribuidor text, " +
                "quantidade_temporada int);");

        db.execSQL("CREATE TABLE temporada(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_serie interger, " +
                "nome text, " +
                "numero_temporada int, " +
                "quantidade_episodio int, " +
                "data_lancamento text ); ");

        db.execSQL("CREATE TABLE episodio(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_temporada interger, " +
                "nome text, " +
                "numero_episodio int);");

        db.execSQL("CREATE TABLE filme(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo interger, " +
                "duracao int);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE usuario;");
        db.execSQL("DROP TABLE pessoa;");
        db.execSQL("DROP TABLE titulo;");
        db.execSQL("DROP TABLE serie;");
        db.execSQL("DROP TABLE temporada;");
        db.execSQL("DROP TABLE episodio;");
        db.execSQL("DROP TABLE filme;");
        this.onCreate(db);
    }

    private SQLiteDatabase getBancoLeitura(Context context){
        SQLiteDatabase bancoDados = this.getReadableDatabase();
        return bancoDados;
    }

    private SQLiteDatabase getBancoEscrita(Context context) {
        SQLiteDatabase bancoDados = this.getWritableDatabase();
        return bancoDados;
    }

}