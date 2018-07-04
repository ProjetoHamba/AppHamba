package com.apphamba.hamba.usuario.usuario.gui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.usuario.usuario.usuarioservices.ServicosUsuario;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private String email, senha;
    private ServicosUsuario usuarioValido = new ServicosUsuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = (EditText)findViewById(R.id.editTextEmail);
        campoSenha = (EditText)findViewById(R.id.editTextSenha);
        campoEmail.requestFocus();

        clicarBotaoEntrar();

    }

    private void clicarBotaoEntrar() {
        botaoEntrar = (Button) findViewById(R.id.button_entrar);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin();
            }
        });
    }

    private void verificarLogin() {
        email = campoEmail.getText().toString().trim();
        senha = campoSenha.getText().toString().trim();
        if (verificarCampos()){
            verificarEmailSenhaBanco();
        }
    }

    private void verificarEmailSenhaBanco(){
        email=campoEmail.getText().toString().trim();
        senha=campoSenha.getText().toString().trim();
        Toast Erro;
        Erro =Toast.makeText(getApplicationContext(),"Email ou senha inválidos", Toast.LENGTH_SHORT);

        if (usuarioValido.verficarEmailSenhaLiberarLogin(email,senha,this)){
            Toast Logado;
            Logado = Toast.makeText(getApplicationContext(),"Usuário logado com sucesso", Toast.LENGTH_SHORT);
            Logado.show();
            finish();
        }
        else{
            Erro.show();
        }
    }


    private boolean verificarCampos(){
        if(usuarioValido.validarCampoEmail(email)){
            campoEmail.setError("Email Inválido");
        }
        if(usuarioValido.verificarCampoVazio(senha)){
            campoSenha.setError("Senha Inválida");
        }
        else {
            return true;
        }
        return false;
    }
    
}
