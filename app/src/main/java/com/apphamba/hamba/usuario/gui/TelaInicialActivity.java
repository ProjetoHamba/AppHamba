package com.apphamba.hamba.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.Sessao;

public class TelaInicialActivity extends AppCompatActivity {
    private TextView textoNome, textoSaudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        textoNome = findViewById(R.id.textViewNomeUser);
        textoSaudacao = findViewById(R.id.textView2);
        textoNome.setText(Sessao.instance.getPessoa().getNome());
        textoSaudacao.setText("Bem Vindo");
    }
}
