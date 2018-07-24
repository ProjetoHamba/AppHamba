package com.apphamba.hamba.infra;

public enum EnumTitulos {
    ID("id"), ID_TITULO("id_titulo"), ID_USUARIO("id_usuario"), NOME("nome"), SINOPSE("sinopse"), AVALIACAO("avaliacao"),
    GENEROS("generos"), CRIADORES("criadores"), IMAGEM("imagem"),TABELA_FAVORITO("favorito"), TABELA_TITULOS("titulo"), DISTRIBUIDOR("distribuidor");

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
