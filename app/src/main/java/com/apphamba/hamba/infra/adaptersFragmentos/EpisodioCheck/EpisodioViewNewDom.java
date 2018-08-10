package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import com.apphamba.hamba.titulo.dominio.Episodio;

public class EpisodioViewNewDom {
    private Episodio episodio;
    private String descEpis, tituloEpComNumero;
    private int numEp;
    private boolean isAssistido;
    public String getDescEpis() {
        return descEpis;
    }
    public void setDescEpis(String descEpis) {
        this.descEpis = descEpis;
    }

    public EpisodioViewNewDom(String descEpis, String tituloEpComNumero, int numEp){
        this.descEpis = descEpis;
        this.tituloEpComNumero = tituloEpComNumero;
        this.numEp = numEp;
    }

    public EpisodioViewNewDom(Episodio episodio, boolean isAssistido) {
        this.episodio = episodio;
        this.isAssistido = isAssistido;
    }

    public String getTituloEpComNumero() {
        return tituloEpComNumero;
    }

    public void setTituloEpComNumero(String tituloEpComNumero) {
        this.tituloEpComNumero = tituloEpComNumero;
    }

    public int getNumEp() {
        return numEp;
    }
    public void setNumEp(int numEp) {
        this.numEp = numEp;
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
