package com.apphamba.hamba.titulo.gui;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;

public class DetalhesActivity extends CollapsingToolbarActivity {
    private ImageView imagemTitulo;
    private TextView nomeTitulo, avaliacaoTitulo, sinopseTitulo, criadoresTitulo, generosTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.imagemTitulo = findViewById(R.id.appBarImg);
        this.nomeTitulo = findViewById(R.id.textViewNome);
        this.avaliacaoTitulo = findViewById(R.id.textViewAvaliacao);
        this.sinopseTitulo = findViewById(R.id.textViewSinopse);
        this.criadoresTitulo = findViewById(R.id.textViewCriadores);
        this.generosTitulo = findViewById(R.id.textViewGeneros);
        setInformacoesTitulos();
    }

    private void setInformacoesTitulos(){
        Titulo dados = FiltroTitulo.instance.getTituloSelecionado();
        imagemTitulo.setImageBitmap(dados.getImagemBitmap());
        nomeTitulo.setText(dados.getNome());
        avaliacaoTitulo.setText(String.valueOf(dados.getAvaliacao()));
        sinopseTitulo.setText(dados.getSinopse());
        criadoresTitulo.setText(dados.getCriadores());
        generosTitulo.setText(dados.getGeneros());
    }

    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Up Navigation - voltando com animação
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
