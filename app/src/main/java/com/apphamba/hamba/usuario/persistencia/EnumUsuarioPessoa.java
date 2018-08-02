package com.apphamba.hamba.usuario.persistencia;

public enum EnumUsuarioPessoa {
    ID("id"), ID_USUARIO("id_usuario"), NOME("nome"), TABELA_PESSOA("pessoa"),
    SENHA("senha"), EMAIL("email"), SIM_EXCLUIDO("sim"), NAO_EXCLUIDO("nao"), TABELA_USUARIO("usuario"), EXCLUIDO("excluido") ;

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