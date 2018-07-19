package com.apphamba.hamba.infra;

public enum EnumUsuarioPessoa {
    ID("id"), IDUSUARIO("id_usuario"), NOME("nome"), TABELAPESSOA("pessoa"),
    SENHA("senha"), EMAIL("email"), ATIVO("ativo"), TABELAUSUARIO("usuario");

    private final String descricao;

    EnumUsuarioPessoa(String descricao) {
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