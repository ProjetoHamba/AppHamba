package com.apphamba.hamba.titulo.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Temporada;

import java.util.ArrayList;

public class TemporadaDao {
    private DataBase bancoDados;

    public TemporadaDao() { bancoDados = new DataBase(); }

    public ArrayList<Temporada> loadTemporadas(int idSerie) {
        String query =  "SELECT * FROM temporada " +
                        "WHERE id_serie = ?";
        String[] args = {String.valueOf(idSerie)};
        return this.loadTemporadas(query, args);
    }

    public ArrayList<Temporada> loadTemporadas(String query, String[] args) {
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

        Temporada temporada = new Temporada();
        temporada.setId(id);
        temporada.setIdSerie(idSerie);
        temporada.setNome(nome);
        temporada.setDataLancamento(dataLancamento);
        temporada.setNumeroTemporada(numeroTemp);
        temporada.setQuantidadeEdpisodios(qtdEpisodios);
        EpisodioDao episodioDao = new EpisodioDao();
        temporada.setEpisodios(episodioDao.loadEpisodios(id));
        return temporada;
    }

}
