package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;
import java.util.ArrayList;

public class EpisodioCheckNewActivity extends AppCompatActivity {
    EpisodioCheckAdapterNew adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodio_check_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new EpisodioCheckAdapterNew(this, getEpisodios());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<EpisodioViewNewDom> getEpisodios(){
        ArrayList<Episodio> episodios = FiltroTitulo.instance.getTemporadaSelecionada().getEpisodios();
        ServicoSerie servicoSerie = new ServicoSerie();
        ArrayList<Episodio> episodiosAssistidos = servicoSerie.getAssistidos(FiltroTitulo.instance.getTemporadaSelecionada());
        ArrayList<EpisodioViewNewDom> episodioViewNewDoms = new ArrayList<>();
        for (Episodio episodio:episodios) {
            boolean isAssistido = this.isAssistido(episodio, episodiosAssistidos);
            episodioViewNewDoms.add(new EpisodioViewNewDom(episodio, isAssistido));
        }
        return episodioViewNewDoms;
    }
    private boolean isAssistido(Episodio episodio, ArrayList<Episodio> episodiosAssistido) {
        boolean isAssistido = false;
        for (Episodio episodioAssistido:episodiosAssistido) {
            if (episodioAssistido.getId() == episodio.getId()) {
                isAssistido = true;
            }
        }
        return isAssistido;
    }
}
