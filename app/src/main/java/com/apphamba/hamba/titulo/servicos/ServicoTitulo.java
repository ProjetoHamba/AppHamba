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
import java.util.BitSet;
import java.util.HashMap;

public class ServicoTitulo {

    public ArrayList<Titulo> getTitulos(String tipo){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos(tipo);
    }
    public ArrayList<Titulo> getTitulos(){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.loadTitulos();
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
        if (titulo.getAvaliacaoUsuario().equals(null)) {
            tituloDao.inserirNota(titulo, usuario, nota);
        } else {
            tituloDao.updateNota(titulo, usuario, nota);
        }
    }
    public HashMap<Titulo,Double> getAvaliacaoByUsuario(Usuario usuario){
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoByUsuario(usuario);

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

    public ArrayList<Titulo> getRecomendacao(){//TODO VERIFICAR SE TA CERTO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = Sessao.instance.getPessoa().getUsuario();
        HashMap<Usuario, HashMap<Titulo, Double>> matrizTotal = new HashMap<>();
        ArrayList<Usuario> usuarios = usuarioDAO.loadUsuarios();
        for(Usuario usuario : usuarios){
            matrizTotal.put(usuario ,this.getAvaliacaoByUsuario(usuario));
        }
        ArrayList<Titulo> listaTitulos = this.getTitulos();

        for(Titulo titulo : listaFiltrada(listaTitulos)){
            HashMap<Titulo, Double> hashMap = new HashMap<>();
            hashMap.put(titulo, (double)titulo.getAvaliacao());
            matrizTotal.put(usuarioLogado,hashMap);
        }
        SlopeOne slope=new SlopeOne(matrizTotal, listaTitulos);
        slope.slopeOne();
        return slope.getListaRecomendados(usuarioLogado);

    }

    public HashMap<Titulo, Double> avaliacaoPorUsuario() {
        Usuario usuario = Sessao.instance.getPessoa().getUsuario();
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoUsuario(usuario);
    }

    public HashMap<Titulo, Double> avaliacaoPorTitulo(Titulo titulo) {
        TituloDao tituloDao = new TituloDao();
        return tituloDao.getAvaliacaoTitulo(titulo);
    }

}
