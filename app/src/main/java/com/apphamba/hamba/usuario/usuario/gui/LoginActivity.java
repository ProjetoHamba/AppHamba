package com.apphamba.hamba.usuario.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apphamba.hamba.R;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private String email, senha;


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
        Log.d("Email:", email);
        Log.d("Senha:", senha);
    }

    
}
