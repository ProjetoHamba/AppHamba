package com.apphamba.hamba.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.apphamba.hamba.infra.HambaApp;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "dbapphamba";

    public DataBase() {
        super(HambaApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "senha text NOT NULL, " +
                "email text NOT NULL, " +
                "excluido text NOT NULL); ");

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
                "criadores text, " +
                "tipo text, " +
                "imagem BLOB);");

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

        db.execSQL("CREATE TABLE favorito (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_titulo INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE meu_hamba (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_titulo INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE titulo_imagem (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "imagem BLOB," +
                "id_titulo INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE episodio_assistido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_episodio INTEGER NOT NULL," +
                "id_temporada INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE filme_assistido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_filme INTEGER NOT NULL," +
                "excluido TEXT NOT NULL);");

        db.execSQL("CREATE TABLE titulo_avaliacao (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER NOT NULL," +
                "id_titulo INTEGER NOT NULL," +
                "nota DOUBLE NOT NULL);");

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
        db.execSQL("DROP TABLE favorito;");
        db.execSQL("DROP TABLE meu_hamba;");
        db.execSQL("DROP TABLE titulo_imagem;");
        db.execSQL("DROP TABLE filme_assistido;");
        db.execSQL("DROP TABLE episodio_assistido;");
        db.execSQL("DROP TABLE titulo_avaliacao;");
        this.onCreate(db);
    }
}