package com.apphamba.hamba.usuario.usuario.usuarioservices;


import android.content.Context;

import com.apphamba.hamba.usuario.usuario.dominio.Pessoa;
import  com.apphamba.hamba.usuario.usuario.persistencia.PessoaDAO;

public class ServicosPessoa {
    private PessoaDAO pessoaDAO;

    public ServicosPessoa(Context context) {
        pessoaDAO = new PessoaDAO(context);
    }

    private long cadastrarPessoa(Pessoa pessoa){
        return pessoaDAO.inserirPessoa(pessoa);
    }

    public long cadastrarPessoa(String nome, String sexo, String dataNasc, String cpf, long idUsuario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setSexo(sexo);
        pessoa.setDataNasc(dataNasc);
        pessoa.setCpf(cpf);
        pessoa.setIdUsuario(idUsuario);

        return cadastrarPessoa(pessoa);
    }

    public Pessoa searchPessoaByIdUsuario(long idUsuario){
        return pessoaDAO.getPessoaByIdUsuario(idUsuario);
    }
}
