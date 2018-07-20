package com.apphamba.hamba.titulos.servicos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ServicoTitulo {
    private TituloDao tituloDao;

    public ServicoTitulo() {
        tituloDao = new TituloDao();
    }

    public ArrayList<Titulo> getTitulos(){
        ArrayList<Titulo> titulos = this.tituloDao.loadTitulos();
        return titulos;
    }

    public Titulo buscarTituloPorNome(String nome) {
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getByNome(nome);
    }

}
