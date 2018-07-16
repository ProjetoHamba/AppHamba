package com.apphamba.hamba.usuario.servicos;

import android.content.Context;


import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;
import com.apphamba.hamba.usuario.persistencia.UsuarioDAO;

public class ServicoUsuario {
    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;

    public ServicoUsuario(Context context) {
        pessoaDAO = new PessoaDAO(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public boolean verificarEmailExistente(String email){
        Usuario usuario = usuarioDAO.getByEmail(email);
        if (usuario != null) {
            return true;
        }
        return  false;
    }

    public Usuario criaUsuario(String email, String senha){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    public void iniciarSessao(Pessoa pessoa){
        Sessao.instance.setPessoa(pessoa);
    }

    public Usuario logar(String email, String senha){
        Usuario usuario = usuarioDAO.getByEmailSenha(email, senha);
        return usuario;
    }

    public boolean cadastrar(String nome, String email, String senha){
        if (verificarEmailExistente(email)){
            return false;
        } else {
            Usuario usuario = criaUsuario(email,senha);
            long id = usuarioDAO.inserir(usuario);
            Pessoa pessoa = criarPessoa(nome, usuarioDAO.getByID(String.valueOf(id)));
            pessoaDAO.inserirPessoa(pessoa);
            return  true;
        }

    }

    public void alterarNoBanco(Usuario usuario){
        usuarioDAO.update(usuario);
    }

    public Pessoa criarPessoa(String nome, Usuario usuario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setUsuario(usuario);
        return pessoa;
    }

    public void salvarPessoaBanco(Pessoa pessoa) {
        pessoaDAO.inserirPessoa(pessoa);
    }

    public Pessoa getPessoa(long idUsuario){
        Pessoa pessoa = pessoaDAO.getByIdUsuario(idUsuario);
        return pessoa;

    }

}