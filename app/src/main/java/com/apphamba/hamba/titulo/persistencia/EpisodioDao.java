package com.apphamba.hamba.titulo.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Episodio;

import java.util.ArrayList;

public class EpisodioDao {
    private DataBase bancoDados;

    public EpisodioDao() { bancoDados = new DataBase(); }

    public ArrayList<Episodio> loadEpisodios(int idTemporada) {
        String query =  "SELECT * FROM episodio " +
                        "WHERE id_temporada = ?";
        String[] args = {String.valueOf(idTemporada)};
        return this.loadEpisodios(query, args);
    }

    public ArrayList<Episodio> loadEpisodios(String query, String[] args) {
        ArrayList<Episodio> episodios = new ArrayList<>();
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                episodios.add(this.criarEpisodio(cursor));
            } while (cursor.moveToNext());
        }
        return episodios;
    }

    private Episodio criarEpisodio(Cursor cursor) {
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);

        int indexIdTemp = cursor.getColumnIndex(EnumTitulos.ID_TEMPORADA.getDescricao());
        int idTemp = cursor.getInt(indexIdTemp);

        int indexNome = cursor.getColumnIndex(EnumTitulos.NOME.getDescricao());
        String nome = cursor.getString(indexNome);

        int indexNumEp = cursor.getColumnIndex(EnumTitulos.NUMERO_EPISODIO.getDescricao());
        int numEp = cursor.getInt(indexNumEp);

        Episodio episodio = new Episodio();
        episodio.setId(id);
        episodio.setIdTemoporada(idTemp);
        episodio.setNumeroEpisodio(numEp);
        return episodio;
    }

}
