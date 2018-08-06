package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.usuario.dominio.Usuario;

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
    public void inserirAssistido(Episodio episodio, Temporada temporada, Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.ID_EPISODIO.getDescricao(), episodio.getId());
        valores.put(EnumTitulos.ID_USUARIO.getDescricao(), usuario.getId());
        valores.put(EnumTitulos.ID_TEMPORADA.getDescricao(), temporada.getId());
        valores.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.NAO_EXCLUIDO.getDescricao());
        escritorBanco.insert(EnumTitulos.TABELA_EP_ASSISTIDO.getDescricao(), null, valores);
        escritorBanco.close();
    }
    public void removerAssistido(Episodio episodio, Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String idEpisodio = String.valueOf(episodio.getId());
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id_usuario = ? AND id_episodio = ?";
        String[] args = {idUsuario, idEpisodio};
        ContentValues values = new ContentValues();
        values.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.SIM_EXCLUIDO.getDescricao());
        escritorBanco.update(EnumTitulos.TABELA_EP_ASSISTIDO.getDescricao(), values, query, args);
        escritorBanco.close();
    }
    public ArrayList<Episodio> loadAssistidos(Temporada temporada, Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String idTemporada = String.valueOf(temporada.getId());
        String query =  "SELECT * FROM episodio_assistido AS ea " +
                        "JOIN episodio AS e " +
                        "ON ea.id_titulo = e.id " +
                        "WHERE ea.id_usuario = ? " +
                        "AND ea.id_temporada = ? " +
                        "AND ea.excluido = 'NAO'";
        String[] args = {idUsuario, idTemporada};
        this.loadEpisodios(query, args);
        return this.loadEpisodios(query, args);
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
        episodio.setNome(nome);
        episodio.setIdTemoporada(idTemp);
        episodio.setNumeroEpisodio(numEp);
        return episodio;
    }
    public void inserirEpisodio(Episodio episodio) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.ID_TEMPORADA.getDescricao(), episodio.getIdTemoporada());
        valores.put(EnumTitulos.NOME.getDescricao(), episodio.getNome());
        valores.put(EnumTitulos.NUMERO_EPISODIO.getDescricao(), episodio.getNumeroEpisodio());
        escritorBanco.insert(EnumTitulos.TABELA_EPISODIOS.getDescricao(), null, valores);
        escritorBanco.close();
    }
}
