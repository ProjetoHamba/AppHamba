package com.apphamba.hamba.usuario.servicos;

import android.content.Context;

import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;

public class ServicoPessoa {

    public Pessoa criarPessoa(String nome) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        return pessoa;
    }

    public void salvarPessoaBanco(Pessoa pessoa, Context context) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.getBancoParaEscrita( context );
        pessoaDAO.inserirPessoa(pessoa);
    }

}
