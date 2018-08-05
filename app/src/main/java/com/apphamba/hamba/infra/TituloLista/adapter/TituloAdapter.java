package com.apphamba.hamba.infra.TituloLista.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.gui.TituloView;

import java.util.List;

public class TituloAdapter extends RecyclerView.Adapter<TituloAdapter.TitulosViewHolder> {
    private final List<TituloView> titulos;
    private final Context context;
    private final TituloOnClickListener onClickListener;

    public interface TituloOnClickListener {
        void onClickTitulo(TitulosViewHolder holder, int indexTitulo);
        void onLongClickTitulo(TitulosViewHolder holder, int indexTitulo);
    }

    public TituloAdapter(Context context, List<TituloView> titulos, TituloOnClickListener onClickListener) {
        this.context = context;
        this.titulos = titulos;
        this.onClickListener = onClickListener;
    }

    @Override
    public TitulosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_titulo, viewGroup, false);
        TitulosViewHolder holder = new TitulosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TitulosViewHolder holder, final int position) {
        Titulo titulo = titulos.get(position).getTitulo();
        Bitmap imagemTitulo = titulo.getImagemBitmap();
        holder.imageView.setImageBitmap(imagemTitulo);

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickTitulo(holder, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClickListener.onLongClickTitulo(holder, position);
                    return true;
                }
            });
        }
        int corFundo = context.getResources().getColor(titulos.get(position).getSelecionado() ? R.color.colorBlue : R.color.colorWhite);
        holder.cardView.setCardBackgroundColor(corFundo);
    }

    @Override
    public int getItemCount() {
        return this.titulos != null ? this.titulos.size() : 0;
    }

    public static class TitulosViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View view;
        CardView cardView;
        public TitulosViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.img);
            cardView = view.findViewById(R.id.card_view);
        }
    }
}
