package com.apphamba.hamba.usuario.servicos;

import android.content.Context;

import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;

public class ServicoPessoa {

    public Pessoa criarPessoa(String nome,int idUsuario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setIdUsuario(idUsuario);
        return pessoa;
    }

    public void salvarPessoaBanco(Pessoa pessoa, Context context) {
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        pessoaDAO.inserirPessoa(pessoa);
    }

    public Pessoa criarPessoaIdUsuario(int idUsuario,Context context){
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        Pessoa pessoa = pessoaDAO.getByIdUsuario(idUsuario);
        return pessoa;

    }

}
