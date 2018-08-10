package com.apphamba.hamba.infra;

import com.apphamba.hamba.usuario.dominio.Pessoa;
import java.util.HashMap;
import java.util.Map;

public class Sessao {
    public static final Sessao instance = new Sessao();
    private final Map<String, Object> values = new HashMap<>();
    private int tabAtiva = 0;

    public void setPessoa(Pessoa pessoa) {
        setValor("sessao.Pessoa", pessoa);
    }

    public Pessoa getPessoa() {
        return (Pessoa) values.get("sessao.Pessoa");
    }

    public int getTabAtiva() {
        return tabAtiva;
    }
    public void setTabAtiva(int tabAtiva) { this.tabAtiva = tabAtiva; }


    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

    public void reset() {
        this.values.clear();
    }

}