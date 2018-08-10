package com.apphamba.hamba.infra.adaptersFragmentos.TituloLista;

import com.apphamba.hamba.titulo.dominio.Titulo;



public class TituloView {
    private Titulo titulo;
    private Boolean selecionado = false;

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }



}
