package com.apphamba.hamba.usuario.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.apphamba.hamba.usuario.persistencia.ConstantePopularBanco.INSERIR_PESSOA;
import static com.apphamba.hamba.usuario.persistencia.ConstantePopularBanco.INSERIR_USUARIO;

/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "dbapphamba";

    //TABELA PESSOA
    public static final String TABELA_PESSOA = "pessoa";
    public static final String ID_PESSOA = "id_pessoa";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_SEXO = "sexo";
    public static final String PESSOA_DATANASC = "data_nasc";
    public static final String CPF = "cpf";
    public static final String ID_EST_USUARIO_PE = "id_est_usuario";

    // TABELA USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " (" +
                ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USUARIO_EMAIL + " TEXT NOT NULL UNIQUE, " +
                USUARIO_SENHA + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_PESSOA + " (" +
                ID_PESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PESSOA_NOME + " TEXT NOT NULL, " +
                PESSOA_SEXO + " TEXT NOT NULL, " +
                PESSOA_DATANASC + " TEXT NOT NULL, " +
                CPF + " TEXT NOT NULL, " +
                ID_EST_USUARIO_PE + " INTEGER);");

        db.execSQL(INSERIR_USUARIO);
        db.execSQL(INSERIR_PESSOA);

    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query1 = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABELA_PESSOA;
        db.execSQL(query2);


        this.onCreate(db);
    }
}
