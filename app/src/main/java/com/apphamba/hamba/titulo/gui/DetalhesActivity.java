package com.apphamba.hamba.titulo.gui;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.botaoTemporada.fragments.BotaoTempListaFragment;
import com.apphamba.hamba.infra.adaptersFragmentos.DetalheTituloSlideFotos.ViewPagerAdapter;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import me.relex.circleindicator.CircleIndicator;

public class DetalhesActivity extends CollapsingToolbarActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private TextView nomeTitulo, avaliacaoTitulo, sinopseTitulo, criadoresTitulo, generosTitulo;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, servicoTitulo.getImagens() );
        encontrandoItensView();
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);
        setInformacoesTitulos();
    }
    protected void encontrandoItensView(){

        this.nomeTitulo = findViewById(R.id.textViewNome);
        this.avaliacaoTitulo = findViewById(R.id.textViewAvaliacao);
        this.sinopseTitulo = findViewById(R.id.textViewSinopse);
        this.criadoresTitulo = findViewById(R.id.textViewCriadores);
        this.generosTitulo = findViewById(R.id.textViewGeneros);
    }

    private void setInformacoesTitulos(){
        Titulo dados = FiltroTitulo.instance.getTituloSelecionado();
        nomeTitulo.setText(dados.getNome());
        avaliacaoTitulo.setText(String.valueOf(dados.getAvaliacao()));
        sinopseTitulo.setText(dados.getSinopse());
        criadoresTitulo.setText(dados.getCriadores());
        generosTitulo.setText(dados.getGeneros());
        isSerie();
    }

    private void isSerie() {
        String tipoTitulo = FiltroTitulo.instance.getTituloSelecionado().getTipo();
        if (tipoTitulo.equals(EnumTitulos.SERIE.getDescricao())) {
            ServicoSerie servicoSerie = new ServicoSerie();
            servicoSerie.setSerieOnFiltro(FiltroTitulo.instance.getTituloSelecionado());
            criarFragmento();
        }
    }

     private void criarFragmento(){
         BotaoTempListaFragment botaoTempListaFragment = new BotaoTempListaFragment();
         botaoTempListaFragment.setArguments(getIntent().getExtras());
         getSupportFragmentManager().beginTransaction().replace(R.id.containerbuttontemp,botaoTempListaFragment).commit();
     }


    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
