package com.apphamba.hamba.titulo.servicos;


import android.graphics.Bitmap;

import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.infra.servicos.FormatadorImagem;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.FavoritoDao;
import com.apphamba.hamba.titulo.persistencia.MeuHambaDao;
import com.apphamba.hamba.titulo.persistencia.TituloDao;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.persistencia.UsuarioDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ServicoTitulo {

    public ArrayList<Titulo> getTitulos(){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos();
    }

    public ArrayList<Titulo> getTitulos(String tipo){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos(tipo);
    }

    public Titulo getTituloById(String idTitulo){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getByID(Integer.parseInt(idTitulo));
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

    public Boolean isMeuHamba(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        return meuHambaDao.existeNoMeuHamba(String.valueOf(usuario.getId()), String.valueOf(titulo.getId()));
    }

    public Boolean isFavorito(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        FavoritoDao favoritoDao = new FavoritoDao();
        return favoritoDao.existeNosFavoritos(String.valueOf(usuario.getId()), String.valueOf(titulo.getId()));
    }

    public ArrayList<Titulo> getMeuHamba() {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        return meuHambaDao.loadMeuHamba(usuario);
    }

    public void removerMeuHamba(Titulo titulo) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        MeuHambaDao meuHambaDao = new MeuHambaDao();
        meuHambaDao.remover(titulo, usuario);
    }

    public ArrayList<Bitmap> getImagens(){
        Titulo titulo = FiltroTitulo.instance.getTituloSelecionado();
        TituloDao tituloDao = new TituloDao();
        ArrayList<byte[]> imagensByte = tituloDao.getImagemByIdTitulo(titulo.getId());
        FormatadorImagem formatadorImagem = new FormatadorImagem();
        return formatadorImagem.listByteToListBitmap(imagensByte);
    }

    public void avaliar(Titulo titulo, Double nota) {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        TituloDao tituloDao = new TituloDao();
        if (tituloDao.getNotaTitulo(usuario, titulo) != null) {
            tituloDao.updateNota(titulo, usuario, nota);
        } else {
            tituloDao.inserirNota(titulo, usuario, nota);
        }
    }

    public ArrayList<Titulo> getRecomendacao() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = Sessao.instance.getPessoa().getUsuario();
        Map<Usuario, Map<String, Double>> dados = getAvaliacoesUsuarios();
        HashMap<String, Double> avaliacoesUsuario = avaliacaoPorUsuario(usuarioLogado);
        SlopeOne slopeOne = new SlopeOne(dados);
        Map<String, Double> predicoes = slopeOne.predict(avaliacoesUsuario);
        return getTitulosRecomendados(predicoes);
    }

    private ArrayList<Titulo> getTitulosRecomendados(Map<String, Double> predicoes) {
        ArrayList<Titulo> recomendados = new ArrayList<>();
        for (String titulo : predicoes.keySet()) {
            Titulo tituloAtual = getTituloById(titulo);
            tituloAtual.setAvaliacaoUsuario(predicoes.get(titulo));
            Double notaTituloUsuario = avaliacaoTituloUsuario(tituloAtual);
            if (notaTituloUsuario == null) {
                recomendados.add(tituloAtual);
            }
        }
        Collections.sort(recomendados, new Comparator<Titulo>() {
            @Override
            public int compare(Titulo t1, Titulo t2) {
                return t2.getAvaliacaoUsuario().intValue() - t1.getAvaliacaoUsuario().intValue();
            }
        });
        return recomendados;
    }

    private  Map<Usuario, Map<String, Double>> getAvaliacoesUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = Sessao.instance.getPessoa().getUsuario();
        Map<Usuario, Map<String, Double>> dados = new HashMap<>();
        ArrayList<Usuario> usuarios = usuarioDAO.loadUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getId() != usuarioLogado.getId()) {
                dados.put(usuario, avaliacaoPorUsuario(usuario));
            }
        }
        return dados;
    }

    private HashMap<String,Double> avaliacaoPorUsuario(Usuario usuario){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoTituloString(usuario);
    }

    public Double avaliacaoTituloUsuario(Titulo titulo){
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getNotaTitulo(usuario, titulo);
    }
}
