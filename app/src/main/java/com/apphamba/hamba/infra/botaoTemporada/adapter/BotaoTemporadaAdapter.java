package com.apphamba.hamba.infra.botaoTemporada.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.ArrayList;
import java.util.List;

public class BotaoTemporadaAdapter extends RecyclerView.Adapter<BotaoTemporadaAdapter.BotoesTempViewHolder> {
    private final List<Temporada> temporadas;
    private final Context context;
    private final BotaoTemporadaOnClickListener onClickListener;

    public interface BotaoTemporadaOnClickListener {
        void onClickBotaoTemporada(BotoesTempViewHolder holder, int indexBotaoTemporada);
    }

    public BotaoTemporadaAdapter(Context context, ArrayList<Temporada> temporadas, BotaoTemporadaOnClickListener onClickListener) {
        this.context = context;
        this.temporadas = temporadas;
        this.onClickListener = onClickListener;
    }

    @Override
    public BotoesTempViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_butao_temp, viewGroup, false);
        BotoesTempViewHolder holder = new BotoesTempViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BotoesTempViewHolder holder, final int position) {
        holder.buttonTemp.setText(temporadas.get(position).getNome());
        if (onClickListener != null) {
            holder.buttonTemp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickBotaoTemporada(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.temporadas != null ? this.temporadas.size() : 0;
    }

    public static class BotoesTempViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public Button buttonTemp;

        private BotoesTempViewHolder(View view) {
            super(view);
            this.view = view;
            buttonTemp = view.findViewById(R.id.button_temporada);
        }
    }

}