package com.apphamba.hamba.titulo.dominio;

import java.util.Date;

public class Serie {
    private int id;
    private String distribuidor;
    private int quantidadeTemporada;
    private Titulo titulo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getQuantidadeTemporada() {
        return quantidadeTemporada;
    }

    public void setQuantidadeTemporada(int quantidadeTemporada) {
        this.quantidadeTemporada = quantidadeTemporada;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

}
