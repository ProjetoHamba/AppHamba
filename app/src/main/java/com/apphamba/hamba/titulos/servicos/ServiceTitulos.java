package com.apphamba.hamba.titulos.servicos;

import android.content.Context;

import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

import java.util.ArrayList;

/**
 * Created by Aluno on 10/07/2018.
 */

public class ServiceTitulos {
    public ArrayList<String> getAllTitulos(Context context){
        TituloDao tituloDao = new TituloDao(context);
        ArrayList<Titulo> titulos = tituloDao.loadTitulos();
        ArrayList<String> titulosNome = new ArrayList();
        for (Titulo titulo: titulos) {
           String s =  titulo.getNome();
            titulosNome.add(s);
        }

        return titulosNome;
    }
}
