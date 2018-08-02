package com.apphamba.hamba.usuario.servicos;

import com.apphamba.hamba.infra.EnumUsuarioPessoa;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;
import com.apphamba.hamba.usuario.persistencia.UsuarioDAO;

public class ServicoLoginCadastro {
    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;

    public ServicoLoginCadastro() {
        pessoaDAO = new PessoaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public boolean logar(Usuario usuario) {
        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        boolean isLogado = false;
        if (usuarioLogado != null) {
            Pessoa pessoa = this.pessoaDAO.getByIdUsuario(usuarioLogado.getId());
            this.iniciarSessao(pessoa);
            isLogado = true;
        }
        return isLogado;
    }

    private void iniciarSessao(Pessoa pessoa) {
        Sessao.instance.setPessoa(pessoa);
    }

    public boolean cadastrar(Pessoa pessoa) {
        if (verificarEmailExistente(pessoa.getUsuario().getEmail())) {
            return false;
        } else {
            long id = this.usuarioDAO.inserir(pessoa.getUsuario());
            pessoa.getUsuario().setId(id);
            this.pessoaDAO.inserirPessoa(pessoa);
            return true;
        }
    }

    private boolean verificarEmailExistente(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        return usuario != null;

    }

}