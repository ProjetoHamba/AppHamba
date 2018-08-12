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

public class DesativarContaActivity extends AppCompatActivity {
    private Button botaoDesativar;
    private EditText campoSenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desativar_conta);
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
        this.campoSenha = findViewById(R.id.editTextSenhaAtual);
        this.botaoDesativar = findViewById(R.id.button_desativar_conta);
        this.botaoDesativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desativarConta();
            }
        });
    }
    private void desativarConta() {
        if (!this.verificarCampos()) {
            return;
        }
        String resultado = "Usuário desativado";
        Usuario usuario = this.criarUsuario();
        ServicoConfiguracao servicoConfiguracao = new ServicoConfiguracao();
        try {
            servicoConfiguracao.desativarConta(usuario);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), EscolhaCadOuLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            resultado = e.getMessage();
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarCampos() {
        String senha = campoSenha.getText().toString().trim();
        if (servicoValidacao.verificarCampoVazio(senha)) {
            this.campoSenha.setError("Campo Vazio");
            campoSenha.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private Usuario criarUsuario() {
        String senha = campoSenha.getText().toString().trim();
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
