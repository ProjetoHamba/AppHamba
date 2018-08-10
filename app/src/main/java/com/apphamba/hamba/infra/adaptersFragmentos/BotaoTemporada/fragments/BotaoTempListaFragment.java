package com.apphamba.hamba.infra.adaptersFragmentos.BotaoTemporada.fragments;

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
import com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck.EpisodioCheckNewActivity;
import com.apphamba.hamba.infra.adaptersFragmentos.BotaoTemporada.adapter.BotaoTemporadaAdapter;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Temporada;
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
        ArrayList<Temporada> temporadas = FiltroTitulo.instance.getSerieSelecionada().getTemporadas();
        this.temporadas = temporadas;
        recyclerView.setAdapter(new BotaoTemporadaAdapter(getContext(),temporadas,onClickBotaoTemporada()));
        return view;
    }

    private BotaoTemporadaAdapter.BotaoTemporadaOnClickListener onClickBotaoTemporada() {
        return new BotaoTemporadaAdapter.BotaoTemporadaOnClickListener() {
            @Override
            public void onClickBotaoTemporada(BotaoTemporadaAdapter.BotoesTempViewHolder holder, int indexBotaoTemporada) {
                Temporada temporada = temporadas.get(indexBotaoTemporada);
                Toast.makeText(getContext(), temporada.getNome(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), EpisodioCheckNewActivity.class);
                startActivity(intent);
            }
        };
    }
}