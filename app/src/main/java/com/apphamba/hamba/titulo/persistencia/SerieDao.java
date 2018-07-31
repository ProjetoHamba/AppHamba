package com.apphamba.hamba.titulo.persistencia;

import android.database.Cursor;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Titulo;

public class SerieDao {
    private DataBase bancoDados;

    private SerieDao() { bancoDados = new DataBase(); }

    private Serie criarSerie (Cursor cursor) {
        TituloDao tituloDao = new TituloDao();
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);

        int indexDistribuidores = cursor.getColumnIndex(EnumTitulos.DISTRIBUIDORES.getDescricao());
        String distribuidores = cursor.getString(indexDistribuidores);

        int indexQtdTemporada = cursor.getColumnIndex(EnumTitulos.QUANTIDADE_TEMPORADAS.getDescricao());
        int quantidadeTemporadas = cursor.getInt(indexQtdTemporada);

        int indexTitulo = cursor.getColumnIndex(EnumTitulos.ID_TITULO.getDescricao());
        int idTitulo = cursor.getInt(indexTitulo);

        Titulo titulo = tituloDao.getByID(idTitulo);

        Serie serie = new Serie();
        serie.setId(id);
        serie.setDistribuidor(distribuidores);
        serie.setQuantidadeTemporada(quantidadeTemporadas);
        serie.setTitulo(titulo);
        return serie;
    }

}
