package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import com.apphamba.hamba.titulo.dominio.Episodio;

public class EpisodioView {
    private Episodio episodio;
    private boolean isAssistido;

    public EpisodioView(Episodio episodio, boolean isAssistido) {
        this.episodio = episodio;
        this.isAssistido = isAssistido;
    }

    public Episodio getEpisodio() {
        return episodio;
    }

    public boolean isAssistido() {
        return isAssistido;
    }

}
