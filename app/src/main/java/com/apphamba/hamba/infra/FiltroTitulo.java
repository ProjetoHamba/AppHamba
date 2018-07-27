package com.apphamba.hamba.infra;

import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.gui.TituloView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiltroTitulo {

    public static final FiltroTitulo instance = new FiltroTitulo();
    private final Map<String, Object> values = new HashMap<>();

    public void setTitulosList(ArrayList<Titulo> titulosList) {
        setValor("FiltroTitulo.titulosList", titulosList);
    }

    public ArrayList<Titulo> getTitulosList() {
        return (ArrayList<Titulo>) values.get("FiltroTitulo.titulosList");
    }

    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

}
