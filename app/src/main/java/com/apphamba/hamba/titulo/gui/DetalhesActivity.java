package com.apphamba.hamba.titulo.gui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.botaoTemporada.fragments.BotaoTempListaFragment;
import com.apphamba.hamba.infra.adaptersFragmentos.DetalheTituloSlideFotos.ViewPagerAdapter;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Filme;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoFilme;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import me.relex.circleindicator.CircleIndicator;

public class DetalhesActivity extends CollapsingToolbarActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private TextView nomeTitulo, avaliacaoTitulo, sinopseTitulo, criadoresTitulo, generosTitulo;
    private Button botaoFilmeAssistido;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setUpToolbar();
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, servicoTitulo.getImagens() );
        encontrandoItensView();
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);
        setInformacoesTitulos();

        botaoFilmeAssistido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcarFilme();
                Log.d("tag", "click");
            }
        });

    }

    protected void encontrandoItensView(){
        this.nomeTitulo = findViewById(R.id.textViewNome);
        this.avaliacaoTitulo = findViewById(R.id.textViewAvaliacao);
        this.sinopseTitulo = findViewById(R.id.textViewSinopse);
        this.criadoresTitulo = findViewById(R.id.textViewCriadores);
        this.generosTitulo = findViewById(R.id.textViewGeneros);
        this.botaoFilmeAssistido = findViewById(R.id.filme_assistido);
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
            setUpSerie();
        } else {
            setUpFilme();
        }
    }

    private void setUpFilme() {
        botaoFilmeAssistido.setVisibility(View.VISIBLE);
        ServicoFilme servicoFilme = new ServicoFilme();
        Filme filme = servicoFilme.getFilme(FiltroTitulo.instance.getTituloSelecionado());
        FiltroTitulo.instance.setFilmeSelecionado(filme);
        if (servicoFilme.isAssistido(filme)) {
            botaoFilmeAssistido.setText("ASSISTIDO");
        }
    }

    private void marcarFilme() {
        ServicoFilme servicoFilme = new ServicoFilme();
        Filme filme = FiltroTitulo.instance.getFilmeSelecionado();
        if (!servicoFilme.isAssistido(filme)) {
            servicoFilme.addAssistido(filme);
            botaoFilmeAssistido.setText("ASSISTIDO");
        } else {
            servicoFilme.removeAssistido(filme);
            botaoFilmeAssistido.setText("NÃO ASSISTIDO");
        }
    }

    private void setUpSerie() {
        ServicoSerie servicoSerie = new ServicoSerie();
        servicoSerie.setSerieOnFiltro(FiltroTitulo.instance.getTituloSelecionado());
        criarFragmentoBotaoTemp();
    }

    private void criarFragmentoBotaoTemp(){
         BotaoTempListaFragment botaoTempListaFragment = new BotaoTempListaFragment();
         botaoTempListaFragment.setArguments(getIntent().getExtras());
         getSupportFragmentManager().beginTransaction().replace(R.id.containerbuttontemp, botaoTempListaFragment).commit();
     }


    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void telaAvaliar(View view){
        startActivity(new Intent(this,AvaliacaoActivity.class));
    }

}
