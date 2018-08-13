package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.gui.MainActivity;

public class EscolhaConfiguracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_configuracao);
        setupToolbar();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
    }
    public void cliqueBotaoAlterarDados(View view) {
        this.mudarTela(AlterarEmailActivity.class);
        finish();
    }

    public void cliqueBotaoAlterarSenha(View view) {
        this.mudarTela(AlterarSenhaActivity.class);
        finish();
    }

    public void cliqueBotaoDestivarConta(View view) {
        this.mudarTela(DesativarContaActivity.class);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
