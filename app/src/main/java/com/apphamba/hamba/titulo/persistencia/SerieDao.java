package com.apphamba.hamba.titulo.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class SerieDao {
    private DataBase bancoDados;

    private SerieDao() { bancoDados = new DataBase(); }

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
        serie.setTemporadas(loadTemporadas(id));
        return serie;
    }

    private ArrayList<Temporada> loadTemporadas(int idTemporada) {
        ArrayList<Temporada> temporadas = new ArrayList<>();
        String query = "SELECT * FROM temporada " +
                        "WHERE id_serie = ?";
        String[] args = {String.valueOf(idTemporada)};
        return this.loadTemporadas(query, args);
    }

    private ArrayList<Temporada> loadTemporadas(String query, String[] args) {
        ArrayList<Temporada> temporadas = new ArrayList<>();
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                temporadas.add(this.criarTemporada(cursor));
            } while (cursor.moveToNext());
        }
        return temporadas;
    }

    private Temporada criarTemporada(Cursor cursor) {
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);

        int indexIdSerie = cursor.getColumnIndex(EnumTitulos.ID_SERIE.getDescricao());
        int idSerie = cursor.getInt(indexIdSerie);

        int indexNome = cursor.getColumnIndex(EnumTitulos.NOME.getDescricao());
        String nome = cursor.getString(indexNome);

        int indexDataLancamento = cursor.getColumnIndex(EnumTitulos.DATA_LANCAMENTO.getDescricao());
        String dataLancamento = cursor.getString(indexDataLancamento);

        int indexNumTemp = cursor.getColumnIndex(EnumTitulos.NUMERO_TEMPORADA.getDescricao());
        int numeroTemp = cursor.getInt(indexNumTemp);

        int indexQtdEp = cursor.getColumnIndex(EnumTitulos.QUANTIDADE_EPISODIOS.getDescricao());
        int qtdEpisodios = cursor.getInt(indexQtdEp);

    }

}
