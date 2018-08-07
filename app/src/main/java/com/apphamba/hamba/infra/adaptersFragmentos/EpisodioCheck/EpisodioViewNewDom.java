package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

public class EpisodioViewNewDom {
    private String descEpis, tituloEpComNumero;
    private int numEp;

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
}
