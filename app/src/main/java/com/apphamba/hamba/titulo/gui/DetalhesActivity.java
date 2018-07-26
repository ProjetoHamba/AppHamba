package com.apphamba.hamba.titulo.gui;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.ComunicadorTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;

public class DetalhesActivity extends CollapsingToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Titulo dados = ComunicadorTitulo.instance.getTituloSelecionado(); //TODO ANDERSON VERIFICAR SE TA CERTO
        ImageView imageView = (ImageView) findViewById(R.id.appBarImg);
        imageView.setImageBitmap(dados.getImagemBitmap());
        TextView nome = (TextView) findViewById(R.id.textViewNome);
        nome.setText(dados.getNome());
        TextView avaliacao = (TextView) findViewById(R.id.textViewAvaliacao);
        avaliacao.setText(String.valueOf(dados.getAvaliacao()));
        TextView sinopse = (TextView) findViewById(R.id.textViewSinopse);
        sinopse.setText(dados.getSinopse());
        TextView criadores = (TextView) findViewById(R.id.textViewCriadores);
        criadores.setText(dados.getCriadores());
        TextView generos = (TextView) findViewById(R.id.textViewGeneros);
        generos.setText(dados.getGeneros());
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
