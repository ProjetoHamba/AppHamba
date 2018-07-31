package com.apphamba.hamba.titulo.dominio;

public class Episodio {
    private int id;
    private int idTemoporada;
    private String nome;
    private int numeroEpisodio;

    public int getIdTemoporada() {
        return idTemoporada;
    }

    public void setIdTemoporada(int idTemoporada) {
        this.idTemoporada = idTemoporada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
