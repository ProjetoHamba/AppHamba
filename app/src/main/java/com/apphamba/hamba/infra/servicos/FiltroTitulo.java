package com.apphamba.hamba.infra.servicos;

import com.apphamba.hamba.titulo.dominio.Titulo;

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

    public Titulo getTituloSelecionado() {
        return (Titulo) values.get("sessao.TituloSelecionado");
    }

    public void setTituloSelecionado(Titulo tituloSelecionado) {
        setValor("sessao.TituloSelecionado", tituloSelecionado);
    }

    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

}
