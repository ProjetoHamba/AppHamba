package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apphamba.hamba.R;

public class EscolhaConfiguracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_configuracao);

    }

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
    }
    public void cliqueBotaoAlterarDados(View view){
        this.mudarTela(AlterarEmailActivity.class);
    }

    public void cliqueBotaoAlterarSenha(View view) {this.mudarTela(AlterarSenhaActivity.class);}


}
