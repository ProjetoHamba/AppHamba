package com.apphamba.hamba.titulo.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.infra.persistencia.DataBase;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.ArrayList;

public class TituloDao {
    private DataBase bancoDados;

    public TituloDao(){
        bancoDados = new DataBase();
    }

    public Titulo getByNome(String nome){
        String query =  "SELECT * FROM titulo " +
                "WHERE nome = ?";
        String[] args = {nome};
        return this.load(query, args);
    }

    public Titulo getByID(int idTitulo){
        String query =  "SELECT * FROM titulo " +
                "WHERE id = ?";
        String[] args = {String.valueOf(idTitulo)};
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

    public ArrayList<Titulo> loadTitulos() {
        String query = "SELECT * FROM titulo";
        return this.loadTitulos(query, null);
    }

    public ArrayList<Titulo> loadTitulos(String query, String[] args) {
        ArrayList<Titulo> titulos = new ArrayList<>();
        SQLiteDatabase leitorBanco = bancoDados.getWritableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                titulos.add(this.criarTitulo(cursor));
            } while (cursor.moveToNext());
        }
        return titulos;
    }

    private Titulo criarTitulo(Cursor cursor){
        int indexId = cursor.getColumnIndex(String.valueOf(EnumTitulos.ID));
        int id = cursor.getInt(indexId);

        int indexNome = cursor.getColumnIndex(String.valueOf(EnumTitulos.NOME));
        String nome = cursor.getString(indexNome);

        int indexSinopse = cursor.getColumnIndex(String.valueOf(EnumTitulos.SINOPSE));
        String sinopse = cursor.getString(indexSinopse);

        int indexAvaliacao = cursor.getColumnIndex(String.valueOf(EnumTitulos.AVALIACAO));
        int avaliacao = cursor.getInt(indexAvaliacao);

        int indexGeneros = cursor.getColumnIndex(String.valueOf(EnumTitulos.GENEROS));
        String generos = cursor.getString(indexGeneros);

        int indexTipo = cursor.getColumnIndex(EnumTitulos.TIPO.getDescricao());
        String tipo = cursor.getString(indexTipo);

        int indexCriadores = cursor.getColumnIndex(String.valueOf(EnumTitulos.CRIADORES));
        String criadores = cursor.getString(indexCriadores);

        int indexImagem = cursor.getColumnIndex(String.valueOf(EnumTitulos.IMAGEM));
        byte[] imagem = cursor.getBlob(indexImagem);

        Titulo titulo = new Titulo();
        titulo.setId(id);
        titulo.setNome(nome);
        titulo.setSinopse(sinopse);
        titulo.setAvaliacao(avaliacao);
        titulo.setCriadores(criadores);
        titulo.setGeneros(generos);
        titulo.setCartaz(imagem);
        titulo.setTipo(tipo);
        return titulo;
    }

    public long inserir(Titulo titulo) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.NOME.getDescricao(), titulo.getNome());
        valores.put(EnumTitulos.SINOPSE.getDescricao(), titulo.getSinopse());
        valores.put(EnumTitulos.AVALIACAO.getDescricao(), titulo.getAvaliacao());
        valores.put(EnumTitulos.GENEROS.getDescricao(), titulo.getGeneros());
        valores.put(EnumTitulos.CRIADORES.getDescricao(), titulo.getCriadores());
        valores.put(EnumTitulos.TIPO.getDescricao(), titulo.getTipo());
        valores.put(String.valueOf(EnumTitulos.IMAGEM), titulo.getCartaz());
        long id = escritorBanco.insert(String.valueOf(EnumTitulos.TABELA_TITULOS), null, valores);
        escritorBanco.close();
        return id;
    }

    public void inserirImagemTitulo(int idTitulo, byte[] imagem) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EnumTitulos.ID_TITULO.getDescricao(), idTitulo);
        valores.put(EnumTitulos.IMAGEM.getDescricao(),imagem);
        valores.put(EnumTitulos.EXCLUIDO.getDescricao(),EnumTitulos.NAO_EXCLUIDO.getDescricao());
        escritorBanco.insert(EnumTitulos.TABELA_IMAGEM.getDescricao(),null, valores);
        escritorBanco.close();
    }

    public ArrayList<byte[]> getImagemByIdTitulo(long idTitulo){
       String query =  "SELECT * FROM titulo_imagem " +
                        "WHERE id_titulo = ?";
       String[] args = {String.valueOf(idTitulo)};
       return loadImagens(query, args);
    }

    private ArrayList<byte[]> loadImagens(String query, String[] args) {
        ArrayList<byte[]> imagens = new ArrayList<>();
        SQLiteDatabase leitorBanco = bancoDados.getWritableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                imagens.add(this.criarImagem(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return imagens;
    }

    private byte[] criarImagem(Cursor cursor) {
        int indexImagem = cursor.getColumnIndex(String.valueOf(EnumTitulos.IMAGEM));
        byte[] imagem = cursor.getBlob(indexImagem);
        return imagem;
    }
}
