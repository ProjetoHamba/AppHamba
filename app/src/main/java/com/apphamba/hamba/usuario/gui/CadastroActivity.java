package com.apphamba.hamba.usuario.gui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoPessoa;
import com.apphamba.hamba.usuario.servicos.ServicoUsuario;

public class CadastroActivity extends AppCompatActivity {
    private Button botaoCriar;
    private Toast contaCriada;
    private String nome, email, senha, repetirSenha;
    private EditText campoNome,campoEmail, campoSenha, campoResenha;
    private ServicoUsuario servicoUsuario = new ServicoUsuario();
    private ServicoPessoa servicoPessoa = new ServicoPessoa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.campoNome = (EditText) findViewById(R.id.editTextNome);
        this.campoEmail = (EditText) findViewById(R.id.editTextEmail);
        this.campoSenha = (EditText) findViewById(R.id.editTextSenhaCad);
        this.campoResenha = (EditText) findViewById(R.id.editTextConfSenha);

        clicarBotaoCriar();

    }

    private void clicarBotaoCriar(){
        this.botaoCriar = (Button) findViewById(R.id.button_criar_conta2);
        this.botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarConta();

            }
        });
    }

    private void verificarConta() {
        this.nome = campoNome.getText().toString().trim();
        this.email = campoEmail.getText().toString().trim();
        this.senha = campoSenha.getText().toString().trim();
        this.repetirSenha = campoResenha.getText().toString().trim();
        if (verificarCampos()){
            verificarEmailBanco();
        }

    }

    private void verificarEmailBanco() {
        if (this.servicoUsuario.verificarEmailExistente(this.email,this)) {
            Toast erro;
            erro = Toast.makeText(getApplicationContext(),"O Email já está cadastrado",Toast.LENGTH_SHORT);
            erro.show();
        } else {
            try {
                Usuario usuario = this.servicoUsuario.criaUsuario(this.email, this.senha);
                this.servicoUsuario.salvarUsuarioBanco(usuario, this);
                Usuario usuarioAtual = this.servicoUsuario.criaUsuarioCompleto(this.email,this);
                Pessoa pessoa = this.servicoPessoa.criarPessoa(this.nome, usuarioAtual.getId());
                this.servicoPessoa.salvarPessoaBanco(pessoa,this);
                this.contaCriada = Toast.makeText(getApplicationContext(),"Conta Criada",Toast.LENGTH_SHORT);
                this.contaCriada.show();
            } catch (Exception e) {
                this.contaCriada = Toast.makeText(getApplicationContext(),"Erro ao cadastrar",Toast.LENGTH_SHORT);
                this.contaCriada.show();
            }
            finish();
        }
    }

    private boolean verificarCampos() {
        if (this.servicoUsuario.verificarCampoVazio(this.nome)){
            this.campoNome.setError("Campo vazio");
            return false;
        }
        else if (this.servicoUsuario.validarCampoEmail(this.email)) {
            this.campoEmail.setError("Formato de email inválido");
            return false;
        }
        else if (this.servicoUsuario.verificarCampoVazio(this.senha)){
            this.campoSenha.setError("Campo vazio");
            return false;
        }
        else if (this.servicoUsuario.verificarCampoVazio(this.repetirSenha)) {
            this.campoResenha.setError("Campo vazio");
            return false;
        }
        else if (!this.repetirSenha.equals(this.senha)){
            this.campoResenha.setError("Senhas diferentes");
            return false;
        }
        else{
            return true;
        }
    }

}
