package com.apphamba.hamba.titulo.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.fragments.TituloListFragment;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

public class RecomendacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacoes);
        criarFragment(savedInstanceState);
        setUpToolbar();
    }
    private void criarFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ServicoTitulo servicoTitulo = new ServicoTitulo();
            //Abaixo esse getRecomendados ainda n tem então mudem aq bjs, talvez fosse melhor colocar
            //um "ComumActivity" com os métodos comuns, pq ta repetindo código - n coloquei pq
            //estoy a perguntarem p vcs se acham oks
           // FiltroTitulo.instance.setTitulosList(servicoTitulo.getRecomendados());
            //TituloListFragment frag = new TituloListFragment();
            //frag.setArguments(getIntent().getExtras());
            //getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
