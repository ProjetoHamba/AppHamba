package com.apphamba.hamba.infra.BotaoTemporada.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.BotaoTemporada.adapter.BotaoTemporadaAdapter;
import com.apphamba.hamba.infra.adapter.EpisodiosCheckListaActivity;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.gui.FavoritosActivity;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;
import com.apphamba.hamba.usuario.gui.EscolhaConfiguracaoActivity;

import java.util.ArrayList;
import java.util.List;


public class BotaoTempListaFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Temporada> temporadas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butoes_temp, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        //chamar abaixo temporadas do titulo
        Titulo titulo = FiltroTitulo.instance.getTituloSelecionado();
        ServicoSerie servicoSerie = new ServicoSerie();
        ArrayList<Temporada> temporadas = servicoSerie.getSerie(titulo).getTemporadas();
        //passar como parâmetro abaixo
        recyclerView.setAdapter(new BotaoTemporadaAdapter(getContext(),temporadas,onClickBotaoTemporada()));

        return view;
    }

    private BotaoTemporadaAdapter.BotaoTemporadaOnClickListener onClickBotaoTemporada() {
        return new BotaoTemporadaAdapter.BotaoTemporadaOnClickListener() {
            @Override
            public void onClickBotaoTemporada(BotaoTemporadaAdapter.BotoesTempViewHolder holder, int indexBotaoTemporada) {
                Temporada temporada = temporadas.get(indexBotaoTemporada);
                Toast.makeText(getContext(), temporada.getNome(), Toast.LENGTH_SHORT).show();
                //Aqui vai enviar o título para a outra activity - dizendo qual temp escolhida
                //P dps ser recuperado os episódios da temporada no episodiosCheckLisa
                Intent intent = new Intent(getContext(), FavoritosActivity.class);
                startActivity(intent);

            }
        };
    }
}