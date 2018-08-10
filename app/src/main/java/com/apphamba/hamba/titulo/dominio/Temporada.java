package com.apphamba.hamba.titulo.dominio;

import java.util.ArrayList;

public class Temporada {
    private int id;
    private long idSerie;
    private String nome;
    private String dataLancamento;
    private int numeroTemporada;
    private int quantidadeEdpisodios;
    private ArrayList<Episodio> episodios;

    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(ArrayList<Episodio> episodios) {
        this.episodios = episodios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(long idSerie) {
        this.idSerie = idSerie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public int getQuantidadeEdpisodios() {
        return quantidadeEdpisodios;
    }

    public void setQuantidadeEdpisodios(int quantidadeEdpisodios) {
        this.quantidadeEdpisodios = quantidadeEdpisodios;
    }
}
