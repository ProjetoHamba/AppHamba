package com.apphamba.hamba.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.ServicoValidacao;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.servicos.ServicoLoginCadastro;

public class CadastroActivity extends AppCompatActivity {
    private Button botaoCriar;
    private EditText campoNome,campoEmail, campoSenha, campoResenha;
    private ServicoValidacao servicoValidacao = new ServicoValidacao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.campoNome = findViewById(R.id.editTextNome);
        this.campoEmail = findViewById(R.id.editTextEmail);
        this.campoSenha = findViewById(R.id.editTextSenhaCad);
        this.campoResenha = findViewById(R.id.editTextConfSenha);

        this.botaoCriar = findViewById(R.id.button_criar_conta2);
        this.botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();

            }
        });
    }

    private void cadastrar() {
        if (!this.verificarCampos()) {
            return;
        }

        ServicoLoginCadastro servicoLoginCadastro = new ServicoLoginCadastro();

        if (servicoLoginCadastro.cadastrar(this.criarPessoa())) {
            Toast.makeText(getApplicationContext(),"Conta Criada",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"O Email já está cadastrado",Toast.LENGTH_SHORT).show();
        }

    }

    private Pessoa criarPessoa() {
        String nome = campoNome.getText().toString().trim();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setUsuario(this.criarUsuario());
        return pessoa;
    }

    private Usuario criarUsuario() {
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    private boolean verificarCampos() {
        String nome = campoNome.getText().toString().trim();
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        String repetirSenha = campoResenha.getText().toString().trim();
        if (servicoValidacao.verificarCampoVazio(nome)){
            this.campoNome.setError("Campo vazio");
            return false;
        } else if (servicoValidacao.verificarCampoEmail(email)) {
            this.campoEmail.setError("Formato de email inválido");
            return false;
        } else if (servicoValidacao.verificarCampoVazio(senha)){
            this.campoSenha.setError("Campo vazio");
            return false;
        } else if (servicoValidacao.verificarCampoVazio(repetirSenha)) {
            this.campoResenha.setError("Campo vazio");
            return false;
        } else if (!repetirSenha.equals(senha)){
            this.campoResenha.setError("Senhas diferentes");
            return false;
        } else{
            return true;
        }
    }

}
