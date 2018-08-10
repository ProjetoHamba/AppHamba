package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.titulo.dominio.Filme;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;

public class FilmeDao {
    private DataBase bancoDados;

    public FilmeDao() { bancoDados = new DataBase(); }

    public void inserirAssistido(Filme filme, Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.ID_FILME.getDescricao(), filme.getId());
        valores.put(EnumTitulos.ID_USUARIO.getDescricao(), usuario.getId());
        valores.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.NAO_EXCLUIDO.getDescricao());
        escritorBanco.insert(EnumTitulos.TABELA_FILME.getDescricao(), null, valores);
        escritorBanco.close();
    }

    public void removerAssistido(Filme filme, Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String idFilme = String.valueOf(filme.getId());
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id_usuario = ? AND id_filme = ?";
        String[] args = {idUsuario, idFilme};
        ContentValues values = new ContentValues();
        values.put(EnumTitulos.EXCLUIDO.getDescricao(), EnumTitulos.SIM_EXCLUIDO.getDescricao());
        escritorBanco.update(EnumTitulos.TABELA_FILME_ASSISTIDO.getDescricao(), values, query, args);
        escritorBanco.close();
    }

    public Filme getAssistido(Filme filme, Usuario usuario) {
        String query =  "SELECT * FROM filme_assistido AS fa " +
                        "JOIN filme AS f " +
                        "WHERE fa.id_usuario = ? " +
                        "AND excluido = 'NAO'";
        String idUsuario = String.valueOf(usuario.getId());
        String[] args = {idUsuario};
        return this.load(query, args);
    }

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

   public Filme getByTitulo(Titulo titulo) {
       String query =   "SELECT * FROM filme AS f " +
                        "JOIN titulo AS t " +
                        "ON f.id_titulo = t.id " +
                        "WHERE f.id_titulo = ?";
       String idTitulo = String.valueOf(titulo.getId());
       String[] args = {idTitulo};
       return this.load(query, args);
   }
}
