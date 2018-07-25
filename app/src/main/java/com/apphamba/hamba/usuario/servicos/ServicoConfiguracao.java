package com.apphamba.hamba.usuario.servicos;

import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;
import com.apphamba.hamba.usuario.persistencia.UsuarioDAO;

public class ServicoConfiguracao {
    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;

    public ServicoConfiguracao() {
        pessoaDAO = new PessoaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public boolean atualizarEmail(Usuario usuario) {
        if (usuarioDAO.getByEmail(usuario.getEmail()) == null && usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuarioDAO.update(usuario);
            Sessao.instance.getPessoa().setUsuario(usuario);
            return true;
        }
        return false;
    }

    public boolean atualizarSenha(Usuario usuario, String novaSenha) {
        if (usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuario.setSenha(novaSenha);
            usuario.setEmail(Sessao.instance.getPessoa().getUsuario().getEmail());
            usuarioDAO.update(usuario);
            Sessao.instance.getPessoa().setUsuario(usuario);
            return true;
        }
        return false;
    }

    public boolean desativarConta(Usuario usuario) {
        if (usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuarioDAO.desativarUsuario(usuario);
            Sessao.instance.reset();
            return true;
        }
        return false;
    }
}
