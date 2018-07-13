package com.apphamba.hamba.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.Sessao;

public class tela_mostrar_nome extends AppCompatActivity {
    private TextView textoNome,textoSaudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mostrar_nome);

        textoNome = (TextView) findViewById(R.id.textViewNomeUser);
        textoSaudacao = (TextView) findViewById(R.id.textView2);
        textoNome.setText(Sessao.instance.getPessoa().getNome());
        textoSaudacao.setText("Bem Vindo");
    }
}
