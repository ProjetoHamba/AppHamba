package com.apphamba.hamba.titulo.dominio;

import android.graphics.Bitmap;
import com.apphamba.hamba.infra.servicos.FormatadorImagem;
import java.io.Serializable;

public class Titulo implements Serializable {
    private int id;
    private String nome;
    private String sinopse;
    private int avaliacao;
    private Double avaliacaoUsuario;
    private String generos;
    private String tipo;
    private String criadores;
    private byte[] cartaz;

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

    public Double getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(Double avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
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

    public byte[] getCartaz() {
        return this.cartaz;
    }

    public Bitmap getImagemBitmap() {
        FormatadorImagem formatadorImagem = new FormatadorImagem();
        return formatadorImagem.byteArrayToBitmap(this.cartaz);
    }
    public void setCartaz(byte[] cartaz) {
        this.cartaz = cartaz;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
