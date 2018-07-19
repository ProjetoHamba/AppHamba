package com.apphamba.hamba.infra.fragments.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.servicos.ServicoTitulo;

import java.util.List;



public class TituloAdapter extends RecyclerView.Adapter<TituloAdapter.TitulosViewHolder> {
    protected static final String TAG = "hamba";
    private final List<Titulo> titulos;
    private final Context context;
    private final TituloOnClickListener onClickListener;
    ServicoTitulo servicoTitulo = new ServicoTitulo();

    public interface TituloOnClickListener {
        void onClickTitulo(TitulosViewHolder holder, int idx);
    }

    public TituloAdapter(Context context, List<Titulo> titulos, TituloOnClickListener onClickListener) {
        this.context = context;
        this.titulos = titulos;
        this.onClickListener = onClickListener;
    }

    @Override
    public TitulosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_titulo, viewGroup, false);
        TitulosViewHolder holder = new TitulosViewHolder(view);
        // (1) Chave da animação
        //ViewCompat.setTransitionName(holder.img, context.getString(R.string.transition_key));
        return holder;
    }

    @Override
    public void onBindViewHolder(final TitulosViewHolder holder, final int position) {
        // Atualizada a view
        Titulo titulo = titulos.get(position);
        //setando as infos do dom
        holder.tNome.setText(titulo.getNome());
        //rECLAMANDO DO BYTE -- KKKK, TOU AJEITANDO A FUNÇÃO AINDA MAS.. ABAIXOOOOOOOO
        Bitmap imagemTitilo = servicoTitulo.byteArrayToBitmap(titulo);
        holder.img.setImageBitmap(imagemTitilo);


        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chama o listener para informar que clicou no Titulo
                    onClickListener.onClickTitulo(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.titulos != null ? this.titulos.size() : 0;
    }

    public static class TitulosViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        public ImageView img;
        public View view;

        public TitulosViewHolder(View view) {
            super(view);
            this.view = view;
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.tNome);
            img = (ImageView) view.findViewById(R.id.img);


        }
    }


}