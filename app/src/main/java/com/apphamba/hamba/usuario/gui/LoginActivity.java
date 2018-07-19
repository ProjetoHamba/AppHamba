package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.gui.TesteImagem;
import com.apphamba.hamba.infra.ServicoValidacao;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoLoginCadastro;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.campoEmail = findViewById(R.id.editTextEmail);
        this.campoSenha = findViewById(R.id.editTextSenha);
        this.campoEmail.requestFocus();

        Button botaoEntrar = findViewById(R.id.button_entrar);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        if (!this.verificarCampos()) {
            return;
        }

        ServicoLoginCadastro servicoLoginCadastro = new ServicoLoginCadastro();
        boolean isLogado = servicoLoginCadastro.logar(this.criarUsuario());
        if (isLogado) {
            Toast.makeText(getApplicationContext(),"Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
            proximaTela();
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Email ou senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario criarUsuario() {
        String email = this.campoEmail.getText().toString().trim();
        String senha = this.campoSenha.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    private boolean verificarCampos(){
        String email = this.campoEmail.getText().toString().trim();
        String senha = this.campoSenha.getText().toString().trim();
        if (this.servicoValidacao.verificarCampoEmail(email)) {
            this.campoEmail.setError("Email Inválido");
            return false;
        } else if (this.servicoValidacao.verificarCampoVazio(senha)) {
            this.campoSenha.setError("Senha Inválida");
            return false;
        } else {
            return true;
        }
    }

    private void proximaTela() {
        startActivity(new Intent(LoginActivity.this,EscolhaConfiguracaoActivity.class));
    }

}
