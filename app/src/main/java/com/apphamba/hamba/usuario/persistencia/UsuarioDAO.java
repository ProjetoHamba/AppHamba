package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumUsuarioPessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.infra.DataBase;

public class UsuarioDAO {
    private DataBase bancoDados;

    public UsuarioDAO() {
        bancoDados = new DataBase();
    }

    private Usuario criarUsuario(Cursor cursor) {
        int indexId = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.ID));
        long id = cursor.getLong(indexId);

        int indexEmail = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.EMAIL));
        String email = cursor.getString(indexEmail);

        int indexSenha = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.SENHA));
        String senha = cursor.getString(indexSenha);

        int indexAtivo = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.ATIVO));
        String ativo = cursor.getString(indexAtivo);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAtivo(ativo);

        return usuario;

    }

    private Usuario load(String query, String[] args) {
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
        return this.load(query, args);
    }

    public Usuario getByID(String id) {
        String query =  "SELECT * FROM usuario " +
                "WHERE id = ?";
        String[] args = {id};
        return this.load(query, args);
    }

    public Usuario getByEmailSenha(String email, String senha) {
        String query =  "SELECT * FROM usuario " +
                "WHERE email = ? AND senha = ?";
        String[] args = {email, senha};
        return this.load(query, args);
    }

    public long inserir(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(String.valueOf(EnumUsuarioPessoa.EMAIL), usuario.getEmail());
        valores.put(String.valueOf(EnumUsuarioPessoa.SENHA), usuario.getSenha());
        valores.put(String.valueOf(EnumUsuarioPessoa.ATIVO), String.valueOf(EnumUsuarioPessoa.ATIVO));
        long id = escritorBanco.insert(String.valueOf(EnumUsuarioPessoa.TABELA_USUARIO), null, valores);
        escritorBanco.close();
        return id;
    }

    public void update(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id =  '" + usuario.getId() + "'";
        ContentValues values = new ContentValues();
        values.put(String.valueOf(EnumUsuarioPessoa.EMAIL), usuario.getEmail());
        values.put(String.valueOf(EnumUsuarioPessoa.SENHA), usuario.getSenha());
        escritorBanco.update(String.valueOf(EnumUsuarioPessoa.TABELA_USUARIO), values, query, null);
        escritorBanco.close();
    }

    public void desativarUsuario(Usuario usuario){
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id =  '" + usuario.getId() + "'";
        ContentValues values = new ContentValues();
        values.put(String.valueOf(EnumUsuarioPessoa.ATIVO), String.valueOf(EnumUsuarioPessoa.INATIVO));
        escritorBanco.update(String.valueOf(EnumUsuarioPessoa.TABELA_USUARIO), values, query, null);
        escritorBanco.close();
    }

    public void ativarUsuario(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id =  '" + usuario.getId() + "'";
        ContentValues values = new ContentValues();
        values.put(String.valueOf(EnumUsuarioPessoa.ATIVO), String.valueOf(EnumUsuarioPessoa.ATIVO));
        escritorBanco.update(String.valueOf(EnumUsuarioPessoa.TABELA_USUARIO), values, query, null);
        escritorBanco.close();
    }
}
