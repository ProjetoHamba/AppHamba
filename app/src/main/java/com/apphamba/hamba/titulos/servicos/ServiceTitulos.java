package com.apphamba.hamba.titulos.servicos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.apphamba.hamba.infra.HambaApp;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ServiceTitulos {

    public ArrayList<String> getAllTitulos(){
        TituloDao tituloDao = new TituloDao(HambaApp.getContext());
        ArrayList<Titulo> titulos = tituloDao.loadTitulos();
        ArrayList<String> titulosNome = new ArrayList();
        for (Titulo titulo: titulos) {
           String s =  titulo.getNome();
            titulosNome.add(s);
        }
        return titulosNome;
    }

    public Titulo buscarTituloPorNome(String nome, Context context) {
        TituloDao tituloDao = new TituloDao(context);
        return tituloDao.getByNome(nome);

    }

    public Bitmap byteArrayToBitmap(Titulo titulo) {
        byte[] byteArray =titulo.getImagem();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
