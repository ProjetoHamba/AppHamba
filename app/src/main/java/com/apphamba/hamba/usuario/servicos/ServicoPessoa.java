package com.apphamba.hamba.usuario.servicos;

import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;

public class ServicoPessoa {

    public Pessoa criarPessoa(String nome) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        return pessoa;
    }

    public void salvarPessoaBanco(Pessoa pessoa) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.inserirPessoa(pessoa);
    }

}
