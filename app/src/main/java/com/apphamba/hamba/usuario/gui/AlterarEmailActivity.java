package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.ServicoValidacao;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoConfiguracao;

public class AlterarEmailActivity extends AppCompatActivity {
    private Button botaoAlterarDadosPessoais;
    private EditText campoAlterarEmail, campoSenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_email);

        this.campoAlterarEmail = findViewById(R.id.editTextNovoEmail);
        this.campoSenha = findViewById(R.id.editTextSenhaMudarEmail);

        this.botaoAlterarDadosPessoais = findViewById(R.id.button_criar_AlterarDados2);
        this.botaoAlterarDadosPessoais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarEmail();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void alterarEmail() {
        if (!this.verificarCampos()) {
            return;
        }
        Usuario usuario = this.criarUsuario();
        ServicoConfiguracao servicoConfiguracao = new ServicoConfiguracao();
        if (servicoConfiguracao.atualizarEmail(usuario)) {
            Toast.makeText(getApplicationContext(), "Email atualizado com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Dados inválidos: Email já cadastrado ou senha inválida", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarCampos() {
        String email = campoAlterarEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        if (servicoValidacao.verificarCampoEmail(email)) {
            this.campoAlterarEmail.setError("Formato de email inválido");
            campoAlterarEmail.requestFocus();
            return false;
        } else if (servicoValidacao.verificarCampoVazio(senha)) {
            this.campoSenha.requestFocus();
            this.campoSenha.setError("Campo Vazio");
            return false;
        } else {
            return true;
        }
    }

    private Usuario criarUsuario() {
        String email = campoAlterarEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, EscolhaConfiguracaoActivity.class);
        startActivity(intent);
        finish();
    }

}

