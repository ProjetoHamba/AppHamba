package com.apphamba.hamba.usuario.usuario.gui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.usuario.usuario.usuarioservices.ServicosUsuario;

public class CadastroActivity extends AppCompatActivity {
    private Button botaoCriar;
    private Toast contaCriada;
    private String nome, email, senha, repetirSenha;
    private EditText campoNome,campoEmail, campoSenha, campoResenha;
    private ServicosUsuario usuarioValido = new ServicosUsuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = (EditText) findViewById(R.id.editTextNome);
        campoEmail = (EditText) findViewById(R.id.editTextEmail);
        campoSenha = (EditText) findViewById(R.id.editTextSenhaCad);
        campoResenha = (EditText) findViewById(R.id.editTextConfSenha);

        clicarBotaoCriar();

    }

    private void clicarBotaoCriar(){
        botaoCriar = (Button) findViewById(R.id.button_criar_conta2);
        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarConta();

            }
        });
    }

    private void verificarConta() {
        nome = campoNome.getText().toString().trim();
        email = campoEmail.getText().toString().trim();
        senha = campoSenha.getText().toString().trim();
        repetirSenha = campoResenha.getText().toString().trim();
        if (verificarCampos()){
            verificarEmailBanco();
        }

    }

    private void verificarEmailBanco() {
        if(usuarioValido.verificarEmailExistente(email,this)){
            Toast erro;
            erro = Toast.makeText(getApplicationContext(),"O Email já está cadastrado",Toast.LENGTH_SHORT);
            erro.show();
        }
        else{
            usuarioValido.salvarUsuarioBanco(usuarioValido.criaUsuario(email,senha),usuarioValido.criarPessoa(nome),this);
            contaCriada = Toast.makeText(getApplicationContext(),"Conta Criada",Toast.LENGTH_SHORT);
            contaCriada.show();

            finish();

        }
    }

    private boolean verificarCampos() {
        if (usuarioValido.verificarCampoVazio(nome)){
            campoNome.setError("Campo Vazio");
            return false;
        }
        else if (usuarioValido.validarCampoEmail(email)) {
            campoEmail.setError("Campo Vazio");
            return false;
        }
        else if (usuarioValido.verificarCampoVazio(senha)){
            campoSenha.setError("Campo Vazio");
            return false;
        }
        else if (usuarioValido.verificarCampoVazio(repetirSenha)) {
            campoResenha.setError("Campo Vazio");
            return false;
        }
        else if (!repetirSenha.equals(senha)){
            campoResenha.setError("Senhas Diferentes");
            return false;
        }
        else{
            return true;
        }
    }

}
