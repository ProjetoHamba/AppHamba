package com.apphamba.hamba.infra;

public enum EnumTitulos {
    ID("id"), ID_TITULO("id_titulo"), ID_USUARIO("id_usuario"), NOME("nome"), SINOPSE("sinopse"), AVALIACAO("avaliacao"),
    GENEROS("generos"), CRIADORES("criadores"), IMAGEM("imagem"), TABELA_MEU_HAMBA("meu_hamba"), TABELA_FAVORITO("favorito"), TABELA_TITULOS("titulo"), DISTRIBUIDOR("distribuidor"),
    SIM_EXCLUIDO("sim"), NAO_EXCLUIDO("nao"), EXCLUIDO("excluido"), DURACAO("Dduracao"), FILME("FILME"), TABELA_FILME("filme"), TABELA_SERIE("serie"), DISTRIBUIDORES("distribuidores"),
    QUANTIDADE_TEMPORADAS("quantidade_temporada"), ID_SERIE("id_serie"), DATA_LANCAMENTO("data_lancamento"),NUMERO_TEMPORADA("numero_temporada"), QUANTIDADE_EPISODIOS("quantidade_episodio"),
    ID_TEMPORADA("id_temporada"), NUMERO_EPISODIO("numero_episodio");

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
