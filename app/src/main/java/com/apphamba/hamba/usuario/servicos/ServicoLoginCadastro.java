package com.apphamba.hamba.usuario.servicos;

import com.apphamba.hamba.infra.HambaAppException;
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

    public void logar(Usuario usuario) throws HambaAppException {
        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {
            Pessoa pessoa = this.pessoaDAO.getByIdUsuario(usuarioLogado.getId());
            this.iniciarSessao(pessoa);
        } else {
            throw new HambaAppException("Usuário ou senha inválidos");
        }
    }

    private void iniciarSessao(Pessoa pessoa) {
        Sessao.instance.setPessoa(pessoa);
    }

    public void cadastrar(Pessoa pessoa) throws HambaAppException {
        if (verificarEmailExistente(pessoa.getUsuario().getEmail())) {
            throw new HambaAppException("Usuário já cadastrado");
        } else {
            long id = this.usuarioDAO.inserir(pessoa.getUsuario());
            pessoa.getUsuario().setId(id);
            this.pessoaDAO.inserirPessoa(pessoa);
        }
    }

    private boolean verificarEmailExistente(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        return usuario != null;

    }

}