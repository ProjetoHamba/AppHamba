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
import com.apphamba.hamba.titulos.gui.TelaComMenuActivity;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoUsuario;

public class ConfiguracoesActivity extends AppCompatActivity {
    private Button botaoModificar, botaoApagarConta;
    private String edtEmail, edtConfEmail, edtSenha, edtConfSenha;
    private EditText campoEdtEmail,campoEdtConfEmail,campoEdtSenha,campoEdtConfSenha;
    private Usuario usuarioLogado = LoginActivity.getUsuarioLogado();
    private ServicoUsuario servicoUsuario = new ServicoUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        this.campoEdtEmail =(EditText) findViewById(R.id.edtEmailNovo);
        this.campoEdtConfEmail =(EditText) findViewById(R.id.edtConfEmail);
        this.campoEdtSenha =(EditText) findViewById(R.id.edtSenhaNova);
        this.campoEdtConfSenha =(EditText) findViewById(R.id.edtSenhaConf);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        onClickModificar();
    }
    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    private void retornoMenuPrincipal(){ this.mudarTela(TelaComMenuActivity.class);}
    public void retornoMenuPrincipal(View view){
        this.retornoMenuPrincipal();
    }
    @Override
    public void onBackPressed() {
        this.retornoMenuPrincipal();
    }
    public void onClickModificar() {
        this.botaoModificar = (Button) findViewById(R.id.btnModificar);
        botaoModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarInformacoes();
            }
        });
    }

    public void verificarInformacoes(){
        this.edtEmail = campoEdtEmail.getText().toString().trim();
        this.edtConfEmail = campoEdtConfEmail.getText().toString().trim();
        this.edtSenha = campoEdtSenha.getText().toString().trim();
        this.edtConfSenha = campoEdtConfSenha.getText().toString().trim();
        if(verificarCampos()) {
            liberarAlteracao();
        }
    }

    private boolean verificarCampos() {
        if (this.servicoUsuario.validarCampoEmail(this.edtEmail)){
            this.campoEdtEmail.setError("Email inválido");
            return false;
        }
        else if (!this.edtConfEmail.equals(this.edtEmail)) {
            this.campoEdtConfEmail.setError("Emails diferentes");
            return false;
        }
        else if (this.servicoUsuario.verificarCampoVazio(this.edtSenha)){
            this.campoEdtSenha.setError("Campo Vazio");
            return false;
        }
        else if (!this.edtConfSenha.equals(this.edtSenha)) {
                this.campoEdtConfSenha.setError("Senhas diferentes");
                return false;
        }
        else{
            return true;
        }
    }

    private void liberarAlteracao() {
        Usuario usuarioMudanca = new Usuario();
        usuarioMudanca = servicoUsuario.criaUsuarioParaLogin(usuarioLogado.getEmail(),this);
        usuarioMudanca.setEmail(edtEmail);
        usuarioMudanca.setSenha(edtSenha);
        servicoUsuario.alterarNoBanco(usuarioMudanca,this);
        usuarioLogado = usuarioMudanca;
        Toast.makeText(getApplicationContext(),"alterado com sucessso",Toast.LENGTH_SHORT).show();
        finish();
        }

    }
