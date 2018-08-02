package com.apphamba.hamba.titulo.dominio;

import java.util.Date;

public class Serie {
    private int id;
    private int idTitulo;
    private String distribuidor;
    private int quantidadeTemporada;
    private Titulo titulo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTitulo() {
        return idTitulo;
    }

    private Date dataEstreia;

    public Date getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(Date dataEstreia) {
        this.dataEstreia = dataEstreia;
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
