package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;

import java.util.ArrayList;

public class EpisodioCheckNewActivity extends AppCompatActivity {

    StringBuffer sb= null;
    EpisodioCheckAdapterNew adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodio_check_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Jogar abaixo os episódios  - e retirar o método lá embaixo do getEpisodios() a mão
        adapter = new EpisodioCheckAdapterNew(this, getEpisodios());

//        Button buttonMarcarEpAssist = (Button) findViewById(R.id.buttonMarcarEpAssist);
//        buttonMarcarEpAssist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sb = new StringBuffer();
//
//                for (EpisodioViewNewDom p : adapter.checkedEpisodios) {
//                    //abaixo pega a string position - método do EpisódioViewNewDom
//                    sb.append(p.getTituloEpComNumero());
//                    sb.append("\n");
//                }
//                if (adapter.checkedEpisodios.size() > 0) {
//                    Toast.makeText(EpisodioCheckNewActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(EpisodioCheckNewActivity.this, "Nenhum episódio marcado como assistido", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        //Recycler
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        //Set adapter
        rv.setAdapter(adapter);
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
