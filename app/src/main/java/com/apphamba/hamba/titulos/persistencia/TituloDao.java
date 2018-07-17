package com.apphamba.hamba.titulos.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.apphamba.hamba.infra.DataBase;
import com.apphamba.hamba.titulos.dominio.Titulo;

import java.util.ArrayList;

/**
 * Created by Bruno on 10/07/2018.
 */

public class TituloDao {
    private DataBase bancoDados;

    public TituloDao(Context context){bancoDados = new DataBase();}

    private Titulo createTitulo(Cursor cursor){
        int indexId = cursor.getColumnIndex("id");//Enum
        int id = cursor.getInt(indexId);//0

        int indexNome = cursor.getColumnIndex("nome");
        String nome = cursor.getString(indexNome);//1

        int indexSinopse = cursor.getColumnIndex("sinopse");
        String sinopse = cursor.getString(indexSinopse);

        int indexAvaliacao = cursor.getColumnIndex("avaliacao");
        int avaliacao = cursor.getInt(indexAvaliacao);

        int indexGeneros = cursor.getColumnIndex("generos");
        String generos = cursor.getString(indexGeneros);

        int indexCriadores = cursor.getColumnIndex("criadores");
        String criadores = cursor.getString(indexCriadores);

        Titulo titulo = new Titulo();
        titulo.setId(id);
        titulo.setNome(nome);
        titulo.setSinopse(sinopse);
        titulo.setAvaliacao(avaliacao);
        titulo.setCriadores(criadores);
        titulo.setGeneros(generos);

        return titulo;
    }
    //TODO loadtitulos falta verificar se esta null da responda.

    public ArrayList<Titulo> loadTitulos(){
        ArrayList<Titulo> titulos = new ArrayList<Titulo>();
        SQLiteDatabase leitorBanco = bancoDados.getWritableDatabase();
        String query = "SELECT * FROM titulo";
        Cursor cursor = leitorBanco.rawQuery(query,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                //cursor.moveToNext();
                titulos.add( this.createTitulo( cursor ) );
            }while(cursor.moveToNext());
        }
        return titulos;


    }

}

