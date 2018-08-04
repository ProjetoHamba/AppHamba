package com.apphamba.hamba.infra.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;

import java.util.ArrayList;
import java.util.List;

public class EpisodiosCheckListaActivity extends AppCompatActivity {
    private final List<String> selecionados = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodios_check_lista);

        ListView listView =(ListView) findViewById(R.id.listView);
        Temporada temporada = FiltroTitulo.instance.getTemporadaSelecionada();
        ArrayList<Episodio> episodios = temporada.getEpisodios();

        ServicoSerie servicoSerie = new ServicoSerie();
        ArrayList<Episodio> episodiosAssistidos = servicoSerie.getAssistidos(temporada);

        final List<EpisodioView> checkEpisodios = this.epToEpView(episodios, episodiosAssistidos);

        //Na vdd ja ta criado, n precisa recriar , só era um exemplo
        final EpisodioCheckAdapterNaoOficial adapter = new EpisodioCheckAdapterNaoOficial(this,checkEpisodios);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EpisodioView model = checkEpisodios.get(position);
                if (model.isSelected())
                    model.setSelected(false);
                else
                    model.setSelected(true);
                checkEpisodios.set(position, model);
                //now update adapter
                adapter.updateRecords(checkEpisodios);
            }
        });
    }

    private ArrayList<EpisodioView> epToEpView(ArrayList<Episodio> episodios, ArrayList<Episodio> episodiosAssistidos){
        ArrayList<EpisodioView> episodioViews = new ArrayList<>();
        for (Episodio episodio:episodios) {
            Boolean isSelecionado = this.isAssistido(episodio, episodiosAssistidos);
            EpisodioView episodioView = new EpisodioView(isSelecionado, episodio.getNome());
            episodioViews.add(episodioView);
        }
        return episodioViews;
    }

    // Pode ser que seja melhor colocar isso na view, mas to fazendo pra vcs n precisarem pensar - brainstorm time
    private boolean isAssistido(Episodio episodio, ArrayList<Episodio> episodiosAssistido) {
        return episodiosAssistido.contains(episodio);
    } // PODE SER QUE N FUNFE :(

}
