package com.apphamba.hamba.titulo.persistencia;

import com.apphamba.hamba.infra.DataBase;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;

public class FavoritoDao {
    private DataBase bancoDados;

    public FavoritoDao() {
        this.bancoDados = new DataBase();
    }

    public ArrayList<Titulo> loadFavoritos(Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String query = "SELECT * FROM favorito AS f " +
                       "JOIN titulo AS t " +
                       "ON f.id_titulo = t.id " +
                       "WHERE f.id_usuario = ?;";
        String[] args = {idUsuario};
        TituloDao tituloDao = new TituloDao();
        ArrayList<Titulo> favoritos = tituloDao.loadTitulos(query, args);
        return favoritos;
    }

    public void inserirFavorito(Titulo titulo, Usuario usuario) {
        String idUsuario = String.valueOf(usuario.getId());
        String idTitulo = String.valueOf(titulo.getId());
        // TODO terminar inserir
    }

}
