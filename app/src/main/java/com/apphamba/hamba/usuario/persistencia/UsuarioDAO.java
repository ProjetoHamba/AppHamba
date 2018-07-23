package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
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
        int indexId = cursor.getColumnIndex(EnumUsuarioPessoa.ID.getDescricao());
        long id = cursor.getLong(indexId);

        int indexEmail = cursor.getColumnIndex(EnumUsuarioPessoa.EMAIL.getDescricao());
        String email = cursor.getString(indexEmail);

        int indexSenha = cursor.getColumnIndex(EnumUsuarioPessoa.SENHA.getDescricao());
        String senha = cursor.getString(indexSenha);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);

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
                        "WHERE email = ?" +
                        "AND excluido = 'nao'";
        String[] args = {email};
        return this.load(query, args);
    }

    public Usuario getByID(String id) {
        String query =  "SELECT * FROM usuario " +
                        "WHERE id = ?" +
                        "AND excluido = 'nao'";
        String[] args = {id};
        return this.load(query, args);
    }

    public Usuario getByEmailSenha(String email, String senha) {
        String query =  "SELECT * FROM usuario " +
                        "WHERE email = ? AND senha = ?" +
                        "AND excluido = 'nao'";
        String[] args = {email, senha};
        return this.load(query, args);
    }

    public long inserir(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumUsuarioPessoa.EMAIL.getDescricao(), usuario.getEmail());
        valores.put(EnumUsuarioPessoa.SENHA.getDescricao(), usuario.getSenha());
        valores.put(EnumUsuarioPessoa.EXCLUIDO.getDescricao(), String.valueOf(EnumUsuarioPessoa.NAO_EXCLUIDO));
        long id = escritorBanco.insert(EnumUsuarioPessoa.TABELA_USUARIO.getDescricao(), null, valores);
        escritorBanco.close();
        return id;
    }

    public void update(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(EnumUsuarioPessoa.EMAIL.getDescricao(), usuario.getEmail());
        values.put(EnumUsuarioPessoa.SENHA.getDescricao(), usuario.getSenha());
        String[] args = {String.valueOf(usuario.getId())};
        escritorBanco.update(EnumUsuarioPessoa.TABELA_USUARIO.getDescricao(), values, query, args);
        escritorBanco.close();
    }

    public void desativarUsuario(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(EnumUsuarioPessoa.EXCLUIDO.getDescricao(), EnumUsuarioPessoa.SIM_EXCLUIDO.getDescricao());
        String[] args = {String.valueOf(usuario.getId())};
        escritorBanco.update(EnumUsuarioPessoa.TABELA_USUARIO.getDescricao(), values, query, args);
        escritorBanco.close();
    }

    public void ativarUsuario(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id =  ?";
        ContentValues values = new ContentValues();
        values.put(EnumUsuarioPessoa.EXCLUIDO.getDescricao(), EnumUsuarioPessoa.NAO_EXCLUIDO.getDescricao());
        String[] args = {String.valueOf(usuario.getId())};
        escritorBanco.update(EnumUsuarioPessoa.TABELA_USUARIO.getDescricao(), values, query, args);
        escritorBanco.close();
    }

}
