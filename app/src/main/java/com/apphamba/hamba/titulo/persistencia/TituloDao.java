package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.DataBase;
import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.ArrayList;

public class TituloDao {
    private DataBase bancoDados;

    public TituloDao(){
        bancoDados = new DataBase();
    }

    private Titulo criarTitulo(Cursor cursor){
        int indexId = cursor.getColumnIndex(EnumTitulos.ID.getDescricao());
        int id = cursor.getInt(indexId);

        int indexNome = cursor.getColumnIndex(EnumTitulos.NOME.getDescricao());
        String nome = cursor.getString(indexNome);

        int indexSinopse = cursor.getColumnIndex(EnumTitulos.SINOPSE.getDescricao());
        String sinopse = cursor.getString(indexSinopse);

        int indexAvaliacao = cursor.getColumnIndex(EnumTitulos.AVALIACAO.getDescricao());
        int avaliacao = cursor.getInt(indexAvaliacao);

        int indexGeneros = cursor.getColumnIndex(EnumTitulos.GENEROS.getDescricao());
        String generos = cursor.getString(indexGeneros);

        int indexCriadores = cursor.getColumnIndex(EnumTitulos.CRIADORES.getDescricao());
        String criadores = cursor.getString(indexCriadores);

        int indexImagem = cursor.getColumnIndex(EnumTitulos.IMAGEM.getDescricao());
        byte[] imagem = cursor.getBlob(indexImagem);

        Titulo titulo = new Titulo();
        titulo.setId(id);
        titulo.setNome(nome);
        titulo.setSinopse(sinopse);
        titulo.setAvaliacao(avaliacao);
        titulo.setCriadores(criadores);
        titulo.setGeneros(generos);
        titulo.setImagem(imagem);

        return titulo;
    }

    public ArrayList<Titulo> loadTitulos() {
        ArrayList<Titulo> titulos = new ArrayList<Titulo>();
        SQLiteDatabase leitorBanco = bancoDados.getWritableDatabase();
        String query = "SELECT * FROM titulo";
        Cursor cursor = leitorBanco.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                titulos.add(this.criarTitulo(cursor));
            } while (cursor.moveToNext());
        }
        return titulos;

    }

    public void inserir(Titulo titulo) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.NOME.getDescricao(), titulo.getNome());
        valores.put(EnumTitulos.SINOPSE.getDescricao(), titulo.getSinopse());
        valores.put(EnumTitulos.AVALIACAO.getDescricao(), titulo.getAvaliacao());
        valores.put(EnumTitulos.GENEROS.getDescricao(), titulo.getGeneros());
        valores.put(EnumTitulos.CRIADORES.getDescricao(), titulo.getCriadores());
        valores.put(EnumTitulos.IMAGEM.getDescricao(), titulo.getImagem());
        escritorBanco.insert(EnumTitulos.TABELA_TITULOS.getDescricao(), null, valores);
        escritorBanco.close();
    }

    public Titulo getByNome(String nome){
        String query =  "SELECT * FROM titulo " +
                        "WHERE nome = ?";
        String[] args = {nome};
        return this.load(query, args);
    }

    private Titulo load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Titulo titulo = null;

        if (cursor.moveToNext()) {
            titulo = criarTitulo(cursor);
        }

        cursor.close();
        leitorBanco.close();
        return titulo;
    }

}

