package com.apphamba.hamba.infra;


import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.HashMap;
import java.util.Map;

public class ComunicadorTitulo {
    public static final ComunicadorTitulo instance = new ComunicadorTitulo();
    private final Map<String, Object> values = new HashMap<>();

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
