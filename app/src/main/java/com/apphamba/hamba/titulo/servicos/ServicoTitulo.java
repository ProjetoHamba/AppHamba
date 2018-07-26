package com.apphamba.hamba.titulo.servicos;


import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.FavoritoDao;
import com.apphamba.hamba.titulo.persistencia.MeuHambaDao;
import com.apphamba.hamba.titulo.persistencia.TituloDao;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;

public class ServicoTitulo {

    public ArrayList<Titulo> getTitulos(){
        TituloDao tituloDao = new TituloDao();
        ArrayList<Titulo> titulos = tituloDao.loadTitulos();
        return titulos;
    }

    public Titulo buscarTituloPorNome(String nome) {
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getByNome(nome);
    }

    public ArrayList<Titulo> getFavoritos() {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FavoritoDao favoritoDao = new FavoritoDao();
        return favoritoDao.loadFavoritos(usuario);
    }

    public void adicionarFavorito(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FavoritoDao favoritoDao = new FavoritoDao();
        favoritoDao.inserir(titulo, usuario);
    }

    public void removerFavorito(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FavoritoDao favoritoDao = new FavoritoDao();
        favoritoDao.remover(titulo, usuario);
    }

    public void adicionarMeuHamba(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        meuHambaDao.inserir(titulo, usuario);
    }

    public Boolean verificarMeuHamba(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        return meuHambaDao.existeNoMeuHamba(String.valueOf(usuario.getId()), String.valueOf(titulo.getId()));
    }

    public Boolean verificarFavorito(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FavoritoDao favoritoDao = new FavoritoDao();
        return favoritoDao.existeNosFavoritos(String.valueOf(usuario.getId()), String.valueOf(titulo.getId()));
    }

    public void removerMeuHamba(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        meuHambaDao.remover(titulo, usuario);
    }

}
