package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;

public class MeuHambaDao {
    private DataBase bancoDados;

    public MeuHambaDao() {
        this.bancoDados = new DataBase();
    }

    public ArrayList<Titulo> loadMeuHamba(Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String query = "SELECT * FROM meu_hamba AS mh " +
                       "JOIN titulo AS t " +
                       "ON mh.id_titulo = t.id " +
                       "WHERE mh.id_usuario = ?;";
        String[] args = {idUsuario};
        TituloDao tituloDao = new TituloDao();
        ArrayList<Titulo> meuHamba = tituloDao.loadTitulos(query, args);
        return meuHamba;
    }

    public void inserir(Titulo titulo, Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String idUsuario = String.valueOf(usuario.getId());
        String idTitulo = String.valueOf(titulo.getId());
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.ID_USUARIO.getDescricao(), idUsuario);
        valores.put(EnumTitulos.ID_TITULO.getDescricao(), idTitulo);
        valores.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.NAO_EXCLUIDO.getDescricao());
        escritorBanco.insert(EnumTitulos.TABELA_MEU_HAMBA.getDescricao(), null, valores);
        escritorBanco.close();
    }

    public void remover(Titulo titulo, Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id_usuario = ? AND id_titulo = ?";
        ContentValues values = new ContentValues();
        values.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.SIM_EXCLUIDO.getDescricao());
        String[] args = {String.valueOf(usuario.getId()), String.valueOf(titulo.getId())};
        escritorBanco.update(EnumTitulos.TABELA_MEU_HAMBA.getDescricao(), values, query, args);
        escritorBanco.close();
    }

    public boolean existeNoMeuHamba(String idUsuario, String idTitulo) {
        String query =  "SELECT * FROM meu_hamba " +
                        "WHERE id_usuario = ? AND id_titulo = ?" +
                        "AND excluido = 'nao'";
        String[] args = {idUsuario, idTitulo};
        return this.load(query, args);
    }

    private boolean load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Boolean existe = false;
        if (cursor.moveToNext()) {
            existe = true;
        }
        cursor.close();
        leitorBanco.close();
        return existe;
    }

}
