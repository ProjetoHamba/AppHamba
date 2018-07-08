package com.apphamba.hamba.usuario.titulos.dominio;

import java.util.ArrayList;

public class Titulo {
    private int id;
    private String nome;
    private String sinopse;
    private int avaliacao;
    private ArrayList<String> generos;
    private ArrayList<String> criadores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public ArrayList<String> getCriadores() {
        return criadores;
    }

    public void setCriadores(ArrayList<String> criadores) {
        this.criadores = criadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
