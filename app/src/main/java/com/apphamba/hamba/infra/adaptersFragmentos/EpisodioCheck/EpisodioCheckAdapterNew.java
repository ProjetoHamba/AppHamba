package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.servicos.ServicoSerie;

import java.util.ArrayList;

public class EpisodioCheckAdapterNew extends RecyclerView.Adapter<EpisodioCheckHolderNew> {

    Context c;
    ArrayList<EpisodioViewNewDom> episodioViewNewDoms;
    ArrayList<EpisodioViewNewDom> checkedEpisodios = new ArrayList<>();

    public EpisodioCheckAdapterNew(Context c, ArrayList<EpisodioViewNewDom> episodioViewNewDoms){
        this.c=c;
        this.episodioViewNewDoms = episodioViewNewDoms;
    }
    @Override
    public EpisodioCheckHolderNew onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_episodio_check,null);
        EpisodioCheckHolderNew holder = new EpisodioCheckHolderNew(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(EpisodioCheckHolderNew holder, int position) {
        EpisodioViewNewDom episodioNewDom = episodioViewNewDoms.get(position);
        holder.numEp.setText(episodioNewDom.getEpisodio().getNome());
        holder.descEp.setText("EP " + String.valueOf(episodioNewDom.getEpisodio().getNumeroEpisodio()));
        holder.chk.setChecked(episodioNewDom.isAssistido());
        Log.d("is assistido", String.valueOf(episodioNewDom.getEpisodio().getNome()));
        Log.d("is assistido", String.valueOf(episodioNewDom.isAssistido()));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk = (CheckBox) v;
                ServicoSerie servicoSerie = new ServicoSerie();
                Temporada temporada = FiltroTitulo.instance.getTemporadaSelecionada();
                EpisodioViewNewDom episodioClick = episodioViewNewDoms.get(pos);
                if (chk.isChecked() && !episodioClick.isAssistido()){
                    servicoSerie.addAssistido(episodioClick.getEpisodio(), temporada);
                } else if(!chk.isChecked() && episodioClick.isAssistido()){
                    servicoSerie.removeAssistido(episodioClick.getEpisodio());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return episodioViewNewDoms.size();
    }
}