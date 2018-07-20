package com.apphamba.hamba.titulo.persistencia;

public enum EnumTitulos {
    ID("id"), ID_USUARIO("id_usuario"), NOME("nome"), SINOPSE("sinopse"), AVALIACAO("avaliacao"),
    GENEROS("generos"), CRIADORES("criadores"), IMAGEM("imagem"), TABELA_TITULOS("titulo"), DISTRIBUIDOR("distribuidor");

    private final String descricao;

    EnumTitulos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
