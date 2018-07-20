package com.apphamba.hamba.titulos.dominio;

import android.graphics.Bitmap;

import com.apphamba.hamba.infra.FormatadorImagem;

public class Titulo {
    private int id;
    private String nome;
    private String sinopse;
    private int avaliacao;
    private String generos;
    private String criadores;
    private byte[] imagem;

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

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getCriadores() {
        return criadores;
    }

    public void setCriadores(String criadores) {
        this.criadores = criadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImagem() {
        FormatadorImagem formatadorImagem = new FormatadorImagem();
        return formatadorImagem.byteArrayToBitmap(this.imagem);
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }






}
