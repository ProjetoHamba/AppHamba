package com.apphamba.hamba.titulo.servicos;


import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.TituloDao;

import java.util.ArrayList;

public class ServicoTitulo {
    private TituloDao tituloDao;

    public ServicoTitulo() {
        tituloDao = new TituloDao();
    }

    public ArrayList<Titulo> getTitulos() {
        ArrayList<Titulo> titulos = this.tituloDao.loadTitulos();
        return titulos;
    }
}
