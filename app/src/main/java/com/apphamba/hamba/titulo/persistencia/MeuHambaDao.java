package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.DataBase;
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
        String query = "SELECT * FROM favorito AS f " +
                       "JOIN titulo AS t " +
                       "ON f.id_titulo = t.id " +
                       "WHERE f.id_usuario = ?;";
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

}
