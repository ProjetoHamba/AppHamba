package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Filme;
import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.ArrayList;

public class FilmeDao {
    private DataBase bancoDados;

    private FilmeDao() { bancoDados = new DataBase(); }

    private Filme criarFilme(Cursor cursor){
        TituloDao tituloDao = new TituloDao();
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);
        int indexDuracao = cursor.getColumnIndex((EnumTitulos.DURACAO.getDescricao()));
        int duracao = cursor.getInt(indexDuracao);
        int indexTitulo = cursor.getColumnIndex((EnumTitulos.ID_TITULO.getDescricao()));
        int idTitulo = cursor.getInt(indexTitulo);
        Titulo titulo = tituloDao.getByID(idTitulo);

        Filme filme = new Filme();
        filme.setId(id);
        filme.setDuracao(duracao);
        filme.setTitulo(titulo);
        return filme;
    }

    private Filme load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Filme filme = null;
        if (cursor.moveToNext()) {
            filme = criarFilme(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return filme;
    }

    public ArrayList<Filme> loadFilmes(String query, String[] args) {
        ArrayList<Filme> filmes = new ArrayList<>();
        SQLiteDatabase leitorBanco = bancoDados.getWritableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do{
                filmes.add(this.criarFilme(cursor));
            } while (cursor.moveToNext());
        }
        return filmes;
    }

   public void inserir(Filme filme) {
        TituloDao tituloDao = new TituloDao();
        tituloDao.inserir(filme.getTitulo());
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        Titulo titulo = tituloDao.getByNome(filme.getTitulo().getNome());
        String duracao = String.valueOf(filme.getDuracao());
        String idTitulo = String.valueOf(titulo.getId());
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.DURACAO.getDescricao(), duracao);
        valores.put(EnumTitulos.ID_TITULO.getDescricao(),idTitulo);
        escritorBanco.insert(EnumTitulos.TABELA_FILME.getDescricao(),null, valores);
        escritorBanco.close();
   }
   public Filme getByIdTitulo(String idTitulo) {
       String query = "SELECT * FROM filmes AS f " +
               "JOIN titulos AS t " +
               "ON f.id_titulo = t.id " +
               "WHERE f.id_titulo = ?";
       String[] args = {idTitulo};
       return this.load(query,args);
   }


}
