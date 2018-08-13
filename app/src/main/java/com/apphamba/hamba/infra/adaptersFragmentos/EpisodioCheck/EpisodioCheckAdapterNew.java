package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    Context contexto;
    ArrayList<EpisodioView> episodioViews;

    public EpisodioCheckAdapterNew(Context contexto, ArrayList<EpisodioView> episodioViews){
        this.contexto = contexto;
        this.episodioViews = episodioViews;
    }
    @Override
    public EpisodioCheckHolderNew onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_episodio_check,null);
        EpisodioCheckHolderNew holder = new EpisodioCheckHolderNew(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(EpisodioCheckHolderNew holder, int position) {
        EpisodioView episodioNewDom = episodioViews.get(position);
        holder.numeroEpisodio.setText(episodioNewDom.getEpisodio().getNome());
        holder.descricaoEpisodio.setText("EP " + String.valueOf(episodioNewDom.getEpisodio().getNumeroEpisodio()));
        holder.checkBox.setChecked(episodioNewDom.isAssistido());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk = (CheckBox) v;
                ServicoSerie servicoSerie = new ServicoSerie();
                Temporada temporada = FiltroTitulo.instance.getTemporadaSelecionada();
                EpisodioView episodioClick = episodioViews.get(pos);
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
        return episodioViews.size();
    }
}