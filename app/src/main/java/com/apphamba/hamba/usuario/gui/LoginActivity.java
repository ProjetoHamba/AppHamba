package com.apphamba.hamba.usuario.gui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.ServicoValidacao;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoUsuario;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        campoEmail.requestFocus();

        Button botaoEntrar = findViewById(R.id.button_entrar);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        if (!verificarCampos()){
            return;
        }

        ServicoUsuario servicoUsuario = new ServicoUsuario(this);
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        Usuario usuario = servicoUsuario.logar(email , senha);
        if (usuario == null){
            Toast.makeText(getApplicationContext(),"Email ou senha inválidos", Toast.LENGTH_SHORT).show();
        } else {
            Pessoa pessoa = servicoUsuario.getPessoa(usuario.getId());
            servicoUsuario.iniciarSessao(pessoa);
            Toast.makeText(getApplicationContext(),"Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
            proximaTela();
        }

    }

//    private void solicitarLogin(){
//        ServicoUsuario servicoUsuario = new ServicoUsuario(this);
//        String email = campoEmail.getText().toString().trim();
//        String senha = campoSenha.getText().toString().trim();
//        Usuario usuario = servicoUsuario.confirmarUsuario(email , senha);
//        if (usuario == null){
//            Toast.makeText(getApplicationContext(),"Email ou senha inválidos", Toast.LENGTH_SHORT).show();
//        } else{
//            Pessoa pessoa = servicoUsuario.getPessoa(usuario.getId());
//            servicoUsuario.logar(pessoa);
//            Toast.makeText(getApplicationContext(),"Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
//            proximaTela();
//        }
//    }

    private boolean verificarCampos(){
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        if(servicoValidacao.verificarCampoEmail(email)){
            campoEmail.setError("Email Inválido");
            return false;
        } else if(servicoValidacao.verificarCampoVazio(senha)){
            campoSenha.setError("Senha Inválida");
            return false;
        } else {
            return true;
        }
    }

    private void proximaTela(){
        startActivity(new Intent(LoginActivity.this,TelaInicialActivity.class));
    }

}
