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
import com.apphamba.hamba.infra.servicos.ServicoValidacao;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoConfiguracao;

public class AlterarSenhaActivity extends AppCompatActivity {
    private Button botaoAterarSenha;
    private EditText campoSenhaAtual, campoNovaSenha, campoConfirmNovaSenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        encontrandoElementosView();
        setupToolbar();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void encontrandoElementosView(){
        this.campoSenhaAtual = findViewById(R.id.editTextSenhaAtual);
        this.campoNovaSenha = findViewById(R.id.editTextSenhaNovaSenha);
        this.campoConfirmNovaSenha = findViewById(R.id.editTextConfirmarNovaSenha);

        this.botaoAterarSenha = findViewById(R.id.button_criar_AlterarSenha2);
        this.botaoAterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarSenha();
            }
        });
    }

    private void alterarSenha() {
        if (!this.verificarCampos()) {
            return;
        }
        String novaSenha = campoNovaSenha.getText().toString().trim();
        String resultado = "Senha atualizada com sucesso";
        Usuario usuario = this.criarUsuario();
        ServicoConfiguracao servicoConfiguracao = new ServicoConfiguracao();
        try {
            servicoConfiguracao.atualizarSenha(usuario, novaSenha);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            resultado = e.getMessage();
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            this.campoSenhaAtual.requestFocus();
        }
    }

    private boolean verificarCampos() {
        String senhaAtual = campoSenhaAtual.getText().toString().trim();
        String novaSenha = campoNovaSenha.getText().toString().trim();
        String confirmarNovaSenha = campoConfirmNovaSenha.getText().toString().trim();
        if (servicoValidacao.verificarCampoVazio(senhaAtual)) {
            this.campoSenhaAtual.setError("Campo Vazio");
            campoSenhaAtual.requestFocus();
            return false;
        } else if (servicoValidacao.verificarCampoVazio(novaSenha)) {
            this.campoNovaSenha.setError("Campo Vazio");
            campoSenhaAtual.requestFocus();
            return false;
        } else if (servicoValidacao.verificarCampoVazio(confirmarNovaSenha)) {
            this.campoConfirmNovaSenha.setError("Campo Vazio");
            campoConfirmNovaSenha.requestFocus();
            return false;
        } else if (!confirmarNovaSenha.equals(novaSenha)) {
            campoConfirmNovaSenha.setError("Senhas diferentes");
            campoConfirmNovaSenha.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private Usuario criarUsuario() {
        String senha = campoSenhaAtual.getText().toString().trim();
        Usuario usuario = new Usuario();
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
