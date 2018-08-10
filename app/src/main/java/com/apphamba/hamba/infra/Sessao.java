package com.apphamba.hamba.infra;

import com.apphamba.hamba.usuario.dominio.Pessoa;
import java.util.HashMap;
import java.util.Map;

public class Sessao {
    public static final Sessao instance = new Sessao();
    private final Map<String, Object> values = new HashMap<>();

    public void setPessoa(Pessoa pessoa) {
        setValor("sessao.Pessoa", pessoa);
    }

    public Pessoa getPessoa() {
        return (Pessoa) values.get("sessao.Pessoa");
    }

    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

    public void reset() {
        this.values.clear();
    }
}