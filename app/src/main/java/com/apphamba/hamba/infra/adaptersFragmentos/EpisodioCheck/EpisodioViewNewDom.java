package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import com.apphamba.hamba.titulo.dominio.Episodio;

public class EpisodioViewNewDom {
    private Episodio episodio;
    private boolean isAssistido;

    public EpisodioViewNewDom(Episodio episodio, boolean isAssistido) {
        this.episodio = episodio;
        this.isAssistido = isAssistido;
    }

    public Episodio getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Episodio episodio) {
        this.episodio = episodio;
    }

    public boolean isAssistido() {
        return isAssistido;
    }

    public void setAssistido(boolean assistido) {
        isAssistido = assistido;
    }
}
