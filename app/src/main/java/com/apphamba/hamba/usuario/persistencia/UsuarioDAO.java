package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.infra.DataBase;


public class UsuarioDAO {
    private DataBase bancoDados;

    public UsuarioDAO(Context context) {
        bancoDados = new DataBase(context);
    }

    private Usuario criarUsuario(Cursor cursor) {
        int indexId = cursor.getColumnIndex("id");
        int id = cursor.getInt(indexId);

        int indexEmail = cursor.getColumnIndex("email");
        String email = cursor.getString(indexEmail);

        int indexSenha = cursor.getColumnIndex("senha");
        String senha = cursor.getString(indexSenha);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return usuario;

    }

    public Usuario load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Usuario usuario = null;

        if (cursor.moveToNext()) {
            usuario = criarUsuario(cursor);
        }

        cursor.close();
        leitorBanco.close();
        return usuario;
    }

    public Usuario getByEmail(String email) {
        String query =  "SELECT * FROM usuario " +
                        "WHERE email = ?";
        String[] args = {email};
        Usuario usuario = this.load(query, args);
        return  usuario;
    }

    public void inserir(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        escritorBanco.insert("usuario",null,valores);
        escritorBanco.close();
    }
}
