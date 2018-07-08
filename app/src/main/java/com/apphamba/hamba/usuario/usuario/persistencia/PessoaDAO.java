package com.apphamba.hamba.usuario.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.usuario.infra.DataBase;
import com.apphamba.hamba.usuario.usuario.dominio.Pessoa;

public class PessoaDAO {
    private SQLiteDatabase liteDatabase;

    public SQLiteDatabase getBancoParaLeitura(Context context){
        DataBase auxDataBase = new DataBase(context);
        liteDatabase = auxDataBase.getReadableDatabase();
        return liteDatabase;
    }

    public SQLiteDatabase getBancoParaEscrita(Context context) {
        DataBase auxDataBase = new DataBase(context);
        liteDatabase = auxDataBase.getWritableDatabase();
        return liteDatabase;

    }

    public void inserirPessoa(Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome",pessoa.getNome());
        valores.put("id_usuario",pessoa.getIdUsuario());
        liteDatabase.insert("pessoa",null,valores);
    }
}
