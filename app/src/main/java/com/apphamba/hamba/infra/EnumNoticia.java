package com.apphamba.hamba.infra;

public enum EnumNoticia {
    API_KEY("f7d880a8399949f4b8c350b2d157533c"),
    NEWS_SOURCE("google-news-br"), KEY_AUTHOR("author"), KEY_TITLE("title"),KEY_DESCRIPTION("description"),
    KEY_URL("url"), KEY_URLTOIMAGE("urlToImage"),KEY_PUBLISHEDAT("publishedAt");

    private final String descricao;

    EnumNoticia(String descricao) {
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