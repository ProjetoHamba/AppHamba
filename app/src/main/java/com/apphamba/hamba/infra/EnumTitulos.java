package com.apphamba.hamba.infra;

public enum EnumTitulos {
    ID("id"), IDUSUARIO("id_usuario"), NOME("nome"),SINOPSE("sinopse"), AVALIACAO("avaliacao"),
    GENEROS("generos"), CRIADORES("criadores"), IMAGEM("imagem"), TABELATITULOS("titulo"), DISTRIBUIDOR("distribuidor");

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
