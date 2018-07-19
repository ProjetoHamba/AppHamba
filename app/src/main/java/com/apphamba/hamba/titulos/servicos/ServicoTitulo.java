package com.apphamba.hamba.titulos.servicos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.apphamba.hamba.infra.HambaApp;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ServicoTitulo {
    private TituloDao tituloDao;

    public ServicoTitulo() {
        tituloDao = new TituloDao();
    }

    public ArrayList<String> getTitulos(){
        TituloDao tituloDao = new TituloDao();
        ArrayList<Titulo> titulos = tituloDao.loadTitulos();
        ArrayList<String> titulosNome = new ArrayList();
        for (Titulo titulo: titulos) {
            String nomeTitulo =  titulo.getNome();
            titulosNome.add(nomeTitulo);
        }
        return titulosNome;
    }

    public Titulo buscarTituloPorNome(String nome) {
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getByNome(nome);

    }

    public Bitmap byteArrayToBitmap(Titulo titulo) {
        byte[] byteArray =titulo.getImagem();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }

}
