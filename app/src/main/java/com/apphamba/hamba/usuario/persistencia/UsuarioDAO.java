package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.infra.DataBase;


public class UsuarioDAO {
    private DataBase bancoDados;

    public UsuarioDAO() {
        bancoDados = new DataBase();
    }

    private Usuario criarUsuario(Cursor cursor) {
        int indexId = cursor.getColumnIndex("id");
        long id = cursor.getLong(indexId);

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

    public Usuario getByID(String id) {
        String query =  "SELECT * FROM usuario " +
                "WHERE id = ?";
        String[] args = {id};
        Usuario usuario = this.load(query, args);
        return  usuario;
    }

    public Usuario getByEmailSenha(String email, String senha) {
        String query =  "SELECT * FROM usuario " +
                "WHERE email = ? AND senha = ?";
        String[] args = {email, senha};
        Usuario usuario = this.load(query, args);
        return  usuario;
    }

    public long inserir(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        long id = escritorBanco.insert("usuario",null,valores);
        escritorBanco.close();
        return id;
    }

    public void update(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id =  '" + usuario.getId() + "'";
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("senha",usuario.getSenha());
        escritorBanco.update("usuario", values, query,null);
        escritorBanco.close();
    }

}
