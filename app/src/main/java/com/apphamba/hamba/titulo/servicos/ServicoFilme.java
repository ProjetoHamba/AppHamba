package com.apphamba.hamba.titulo.servicos;

import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.titulo.dominio.Filme;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.FilmeDao;
import com.apphamba.hamba.usuario.dominio.Usuario;

public class ServicoFilme {

    public Filme getFilme(Titulo titulo) {
        FilmeDao filmeDao = new FilmeDao();
        return filmeDao.getByTitulo(titulo);
    }

    public void addAssistido(Filme filme) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FilmeDao filmeDao = new FilmeDao();
        filmeDao.inserirAssistido(filme, usuario);
    }

    public void removeAssistido(Filme filme) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FilmeDao filmeDao = new FilmeDao();
        filmeDao.removerAssistido(filme, usuario);
    }

    public boolean isAssistido(Filme filme) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FilmeDao filmeDao = new FilmeDao();
        if (filmeDao.getAssistido(filme, usuario) != null) {
            return true;
        } else {
            return false;
        }
    }

}
