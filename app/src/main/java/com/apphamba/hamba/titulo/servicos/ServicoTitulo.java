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
import java.util.HashMap;
import java.util.Map;

public class ServicoTitulo {

    public ArrayList<Titulo> getTitulos(String tipo){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos(tipo);
    }
    public ArrayList<Titulo> getTitulos(){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos();
    }

    public Titulo getTituloById(String idTitulo){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getByID(Integer.parseInt(idTitulo));
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

    public ArrayList<Titulo> listaFiltrada(ArrayList<Titulo> titulos){//PEGAR TÍTULOS QUE N SAO FAVORITOS E N ESTÃO NO MEU HAMBA
        ArrayList<Titulo> listaFiltrada = new ArrayList<>();
        for(Titulo titulo : titulos){
            if(!isFavorito(titulo) && !isMeuHamba(titulo)){
                listaFiltrada.add(titulo);
            }
        }
        return listaFiltrada;
    }

    public ArrayList<Titulo> getRecomendacao() { //TODO VERIFICAR SE TA CERTO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = Sessao.instance.getPessoa().getUsuario();
        Map<Usuario, Map<String, Double>> dados = new HashMap<>();
        ArrayList<Usuario> usuarios = usuarioDAO.loadUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() != usuarioLogado.getId()) {
                dados.put(usuario, avaliacaoPorUsuario(usuario));
            }
        }
        HashMap<String, Double> avaliacoesUsuario = avaliacaoPorUsuario(usuarioLogado);
        SlopeOne slopeOne = new SlopeOne(dados);
        Map<String, Double> predicoes = slopeOne.predict(avaliacoesUsuario);
        ArrayList<Titulo> recomendados = new ArrayList<>();

        for (String titulo : predicoes.keySet()) {
            Titulo tituloAtual = getTituloById(titulo);
            Double notaTituloUsuario = avaliacaoTituloUsuario(tituloAtual);
            if (notaTituloUsuario == null) {
                recomendados.add(tituloAtual);
            }
        }
        return recomendados;
    }

    public HashMap<Titulo, Double> avaliacaoPorUsuario() {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoUsuario(usuario);
    }

    public HashMap<String,Double> avaliacaoPorUsuario(Usuario usuario){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoTituloString(usuario);
    }

    public Double avaliacaoTituloUsuario(Titulo titulo){
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getNotaTitulo(usuario, titulo);
    }

    public HashMap<Titulo, Double> avaliacaoPorTitulo(Titulo titulo) {
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoTitulo(titulo);
    }

}
