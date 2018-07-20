package com.apphamba.hamba.titulo.gui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.dominio.Titulo;

import java.util.List;

public class TituloAdapter extends RecyclerView.Adapter<TituloAdapter.TitulosViewHolder> {
    protected static final String TAG = "hamba";
    private final List<Titulo> titulos;
    private final Context context;
    private final TituloOnClickListener onClickListener;

    public interface TituloOnClickListener {
        void onClickTitulo(TitulosViewHolder holder, int indexTitulo);
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
        return holder;
    }

    @Override
    public void onBindViewHolder(final TitulosViewHolder holder, final int position) {
        Titulo titulo = titulos.get(position);
        Bitmap imagemTitulo = titulo.getImagemBitmap();
        holder.imageView.setImageBitmap(imagemTitulo);

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        public ImageView imageView;
        public View view;

        public TitulosViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.img);
        }
    }

}
