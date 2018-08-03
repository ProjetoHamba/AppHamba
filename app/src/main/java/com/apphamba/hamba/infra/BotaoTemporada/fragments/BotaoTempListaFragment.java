package com.apphamba.hamba.infra.BotaoTemporada.fragments;


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
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import java.util.List;

import static com.apphamba.hamba.infra.HambaApp.getContext;

public class BotaoTempListaFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<BotaoTemporada> titulos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butoes_temp, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        //chamar abaixo temporadas do titulo
        titulos = servicoTitulo.getTitulos();
        //passar como parâmetro abaixo
        recyclerView.setAdapter(new BotaoTemporadaAdapter(getContext(),titulos,onClickBotaoTemporada()));

        return view;
    }

    private BotaoTemporadaAdapter.BotaoTemporadaOnClickListener onClickBotaoTemporada() {
        return new BotaoTemporadaAdapter.BotaoTemporadaOnClickListener() {
            @Override
            public void onClickBotaoTemporada(BotaoTemporadaAdapter.BotoesTempViewHolder holder, int indexBotaoTemporada) {
                Titulo titulo = titulos.get(indexBotaoTemporada);
                Toast.makeText(getContext(), titulo.getNome(), Toast.LENGTH_SHORT).show();
                //Aqui vai enviar o título para a outra activity - dizendo qual temp escolhida
                //P dps ser recuperado os episódios da temporada no episodiosCheckLisa

            }
        };
    }
}