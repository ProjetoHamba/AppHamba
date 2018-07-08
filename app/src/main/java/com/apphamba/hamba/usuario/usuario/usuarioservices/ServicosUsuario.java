package com.apphamba.hamba.usuario.usuario.usuarioservices;

import android.content.Context;
import android.util.Patterns;

import com.apphamba.hamba.usuario.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.usuario.persistencia.UsuarioDAO;

import java.util.regex.Pattern;

public class ServicosUsuario {

    public boolean verificarCampoVazio(String campo) {
        if (campo.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean verificarEmailExistente(String email, Context context){
        UsuarioDAO verificador = new UsuarioDAO();
        verificador.getBancoParaLeitura(context);
        return verificador.dataBaseVerificarEmail(email);
    }

    public Usuario criaUsuario(String email, String senha){
        Usuario user = new Usuario();
        user.setEmail(email);
        user.setSenha(senha);
        return user;
    }

    public Pessoa criarPessoa(String nome){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        return pessoa;
    }

    public void salvarUsuarioBanco(Usuario usuario, Pessoa pessoa, Context context){
        UsuarioDAO banco = new UsuarioDAO();
        banco.getBancoParaEscrita(context);
        banco.inserir(usuario);
    }

    public boolean validarCampoEmail(String email){
        if(verificarCampoVazio(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verificarEmailBanco(String email, Context context){
        UsuarioDAO verificador = new UsuarioDAO();
        verificador.getBancoParaLeitura(context);
        return verificador.dataBaseVerificarEmail(email);
    }

    public boolean verficarEmailSenhaLiberarLogin(String email, String senha, Context context){
        UsuarioDAO verificador = new UsuarioDAO();
        verificador.getBancoParaLeitura(context);
        if(verificador.liberarLogin(email,senha)){
            return true;
        }
        else{
            return false;
        }


    }

}