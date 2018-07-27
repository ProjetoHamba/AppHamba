package com.apphamba.hamba.usuario.servicos;

import com.apphamba.hamba.infra.HambaApp;
import com.apphamba.hamba.infra.HambaAppException;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.titulo.gui.MainActivity;
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

    public void atualizarEmail(Usuario usuario) throws HambaAppException{
        if (usuarioDAO.getByEmail(usuario.getEmail()) != null){
            throw new HambaAppException("Email em uso");
        } else if (!usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            throw new HambaAppException("Senha inválida");
        } else {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuarioDAO.update(usuario);
            Sessao.instance.getPessoa().setUsuario(usuario);
        }

    }

    public void atualizarSenha(Usuario usuario, String novaSenha) throws HambaAppException{
        if (usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuario.setSenha(novaSenha);
            usuario.setEmail(Sessao.instance.getPessoa().getUsuario().getEmail());
            usuarioDAO.update(usuario);
            Sessao.instance.getPessoa().setUsuario(usuario);
        } else {
            throw new HambaAppException("Senha inválida");
        }
    }

    public void desativarConta(Usuario usuario) throws HambaAppException {
        if (usuario.getSenha().equals(Sessao.instance.getPessoa().getUsuario().getSenha())) {
            usuario.setId(Sessao.instance.getPessoa().getUsuario().getId());
            usuarioDAO.desativarUsuario(usuario);
            Sessao.instance.reset();
        } else {
            throw new HambaAppException("Senha inválida");
        }
    }
}
