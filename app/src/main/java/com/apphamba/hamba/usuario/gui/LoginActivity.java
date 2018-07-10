package com.apphamba.hamba.usuario.gui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.gui.TelaComMenuActivity;
import com.apphamba.hamba.usuario.servicos.ServicoUsuario;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private String email, senha;
    private ServicoUsuario servicoUsuario = new ServicoUsuario();


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
        if (servicoUsuario.login(email,senha,this)){
            Toast Logado;
            Logado = Toast.makeText(getApplicationContext(),"Usuário logado com sucesso", Toast.LENGTH_SHORT);
            Logado.show();
            this.mudarTela(TelaComMenuActivity.class);
            //finish();
        }
        else{
            Erro.show();
        }
    }

    private boolean verificarCampos(){
        if(servicoUsuario.validarCampoEmail(email)){
            campoEmail.setError("Email Inválido");
        }
        if(servicoUsuario.verificarCampoVazio(senha)){
            campoSenha.setError("Senha Inválida");
        }
        else {
            return true;
        }
        return false;
    }

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

}
