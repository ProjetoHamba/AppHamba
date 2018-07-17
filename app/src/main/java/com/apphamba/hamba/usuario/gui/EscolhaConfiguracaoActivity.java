package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.apphamba.hamba.R;

public class EscolhaConfiguracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_configuracao);
    }

    private void setSupportActionBar(Toolbar toolbar) {
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
