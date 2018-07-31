package com.apphamba.hamba.titulo.dominio;

public class Episodio {
    private int id;
    private int idTemoporada;
    private String nome;
    private int numeroEpisodio;
    private boolean assistido;

    public boolean isAssistido() {
        return assistido;
    }

    public void setAssistido(boolean assistido) {
        this.assistido = assistido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSerie() {
        return idTemoporada;
    }

    public void setIdSerie(int idTemoporada) {
        this.idTemoporada = idTemoporada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

}
