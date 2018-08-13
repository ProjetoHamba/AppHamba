package com.apphamba.hamba.titulo.servicos;

import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.EpisodioDao;
import com.apphamba.hamba.titulo.persistencia.SerieDao;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;

public class ServicoSerie {

    public Serie getSerie(Titulo titulo) {
        SerieDao serieDao = new SerieDao();
        return serieDao.getByTitulo(titulo);
    }

    public void addAssistido(Episodio episodio, Temporada temporada) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        EpisodioDao episodioDao = new EpisodioDao();
        episodioDao.inserirAssistido(episodio, temporada, usuario);
    }

    public void removeAssistido(Episodio episodio) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        EpisodioDao episodioDao = new EpisodioDao();
        episodioDao.removerAssistido(episodio, usuario);
    }

    public ArrayList<Episodio> getAssistidos(Temporada temporada) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        EpisodioDao episodioDao = new EpisodioDao();
        return episodioDao.loadAssistidos(temporada, usuario);
    }

    public void setSerieOnFiltro(Titulo titulo) {
        Serie serie = this.getSerie(titulo);
        FiltroTitulo.instance.setSerieSelecionada(serie);
    }

}
