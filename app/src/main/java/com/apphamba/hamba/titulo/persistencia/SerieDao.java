package com.apphamba.hamba.titulo.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;


import java.util.ArrayList;


public class SerieDao {
    private DataBase bancoDados;

    public SerieDao() { bancoDados = new DataBase(); }

    public Serie getById(int idSerie) {
        String query =  "SELECT * FROM serie " +
                "WHERE id = ? ";
        String[] args = {String.valueOf(idSerie)};
        return this.load(query, args);
    }

    private Serie load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Serie serie = null;

        if (cursor.moveToNext()) {
            serie = this.criarSerie(cursor);
        }

        cursor.close();
        leitorBanco.close();
        return serie;
    }

    private Serie criarSerie (Cursor cursor) {
        TituloDao tituloDao = new TituloDao();
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);

        int indexDistribuidores = cursor.getColumnIndex(EnumTitulos.DISTRIBUIDORES.getDescricao());
        String distribuidores = cursor.getString(indexDistribuidores);

        int indexTitulo = cursor.getColumnIndex(EnumTitulos.ID_TITULO.getDescricao());
        int idTitulo = cursor.getInt(indexTitulo);

        Titulo titulo = tituloDao.getByID(idTitulo);
        Serie serie = new Serie();
        serie.setId(id);
        serie.setDistribuidor(distribuidores);
        serie.setTitulo(titulo);
        TemporadaDao temporadaDao = new TemporadaDao();
        serie.setTemporadas(temporadaDao.loadTemporadas(id));
        return serie;
    }

    private void inserirSerie(Serie serie) {

    }

}
