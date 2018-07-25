package com.apphamba.hamba.titulo.dominio;

import java.util.Date;

public class Temporada {
    private int id;
    private int idSerie;
    private String nome;
    private Date dataLancamento;
    private int numeroTemporada;
    private int quantidadeEdpisodios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
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
