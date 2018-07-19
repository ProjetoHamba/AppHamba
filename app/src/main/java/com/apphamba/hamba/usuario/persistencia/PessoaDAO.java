package com.apphamba.hamba.usuario.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.DataBase;
import com.apphamba.hamba.infra.EnumUsuarioPessoa;
import com.apphamba.hamba.usuario.dominio.Pessoa;

public class PessoaDAO {
    private DataBase bancoDados;
    private UsuarioDAO usuarioDAO;


    public PessoaDAO() {
        bancoDados = new DataBase();
        usuarioDAO = new UsuarioDAO();

    }

    public void inserirPessoa(Pessoa pessoa) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(String.valueOf(EnumUsuarioPessoa.NOME), pessoa.getNome());
        valores.put(String.valueOf(EnumUsuarioPessoa.ID_USUARIO), pessoa.getUsuario().getId());
        escritorBanco.insert(String.valueOf(EnumUsuarioPessoa.TABELA_PESSOA), null, valores);
        escritorBanco.close();
    }

    public Pessoa getByIdUsuario(long id) {
        String query = "SELECT * FROM pessoa " +
                       "WHERE id_usuario = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }

    private Pessoa load(String query, String[] args) {
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
        int indexId = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.ID));
        long id = cursor.getInt(indexId);

        int indexNome = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.NOME));
        String nome = cursor.getString(indexNome);

        int indexUsuario = cursor.getColumnIndex(String.valueOf(EnumUsuarioPessoa.ID_USUARIO));
        String idUsuario = cursor.getString(indexUsuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setUsuario(usuarioDAO.getByID(idUsuario));
        pessoa.setNome(nome);

        return pessoa;
    }
}
