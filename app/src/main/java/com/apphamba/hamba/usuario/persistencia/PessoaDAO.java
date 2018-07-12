package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.DataBase;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;

public class PessoaDAO {
    private DataBase bancoDados;

    public PessoaDAO(Context context) {
        bancoDados = new DataBase(context);
    }


    public void inserirPessoa(Pessoa pessoa) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("id_usuario", pessoa.getIdUsuario());
        escritorBanco.insert("pessoa", null, valores);
        escritorBanco.close();
    }

    public Pessoa getByIdUsuario(int id) {
        String query = "SELECT * FROM pessoa " +
                "WHERE id_usuario = ?";
        String[] args = {String.valueOf(id)};
        Pessoa pessoa = this.load(query, args);
        return pessoa;
    }

    public Pessoa load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Pessoa pessoa = null;

        if (cursor.moveToNext()) {
            pessoa = criarPessoa(cursor);
        }

        cursor.close();
        leitorBanco.close();
        return pessoa;
    }

    private Pessoa criarPessoa(Cursor cursor) {
        int indexId = cursor.getColumnIndex("id");
        int id = cursor.getInt(indexId);

        int indexNome = cursor.getColumnIndex("nome");
        String nome = cursor.getString(indexNome);

        int indexIdUsuario = cursor.getColumnIndex("id_usuario");
        int idUsuario = cursor.getInt(indexIdUsuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setIdUsuario(idUsuario);
        pessoa.setNome(nome);

        return pessoa;
    }
}
