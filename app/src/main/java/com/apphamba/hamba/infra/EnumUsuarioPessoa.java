package com.apphamba.hamba.infra;

public enum EnumUsuarioPessoa {
    ID("id"), ID_USUARIO("id_usuario"), NOME("nome"), TABELA_PESSOA("pessoa"),
    SENHA("senha"), EMAIL("email"), ATIVO("ativo"), TABE_LAUSUARIO("usuario");

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