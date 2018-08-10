package com.apphamba.hamba.titulo.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.fragments.TituloListFragment;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;
import com.apphamba.hamba.titulo.servicos.SlopeOne;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecomendacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacao);

        setUpToolbar();
        criarFragment(savedInstanceState);
    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void criarFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ServicoTitulo servicoTitulo = new ServicoTitulo();
            ArrayList<Titulo> titulos = servicoTitulo.getRecomendacao();
            FiltroTitulo.instance.setTitulosList(titulos);
            TituloListFragment frag = new TituloListFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }

}
