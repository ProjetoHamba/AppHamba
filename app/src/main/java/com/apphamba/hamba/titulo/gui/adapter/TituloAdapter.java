package com.apphamba.hamba.titulo.gui.adapter;

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

import java.util.List;

public class TituloAdapter extends RecyclerView.Adapter<TituloAdapter.TitulosViewHolder> {
    protected static final String TAG = "hamba";
    private final List<Titulo> titulos;
    private final Context context;
    private final TituloOnClickListener onClickListener;

    public interface TituloOnClickListener {
        void onClickTitulo(TitulosViewHolder holder, int indexTitulo);
        void onLongClickTitulo(TitulosViewHolder holder, int indexTitulo);
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
        //rECLAMANDO DO BYTE -- KKKK, TOU AJEITANDO A FUNÇÃO AINDA MAS.. ABAIXOOOOOOOO
        Bitmap imagemTitulo = titulo.getImagemBitmap();
        holder.imageView.setImageBitmap(imagemTitulo);

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chama o listener para informar que clicou no Titulo
                    onClickListener.onClickTitulo(holder, position);
                }
            });
            // Click longo
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClickListener.onLongClickTitulo(holder, position);
                    return true;
                }
            });
        }
        // Pinta o fundo de azul se a linha estiver selecionada
        int corFundo = context.getResources().getColor(titulo.selected ? R.color.colorBlue : R.color.colorWhite);
        holder.cardView.setCardBackgroundColor(corFundo);
        // A cor do texto é branca ou azul, depende da cor do fundo. - isso é para o texto dentro do card
        //int corFonte = context.getResources().getColor(titulo.selected ? R.color.colorWhite : R.color.colorBlue);
        //holder.tNome.setTextColor(corFonte);
;

    }

    @Override
    public int getItemCount() {
        return this.titulos != null ? this.titulos.size() : 0;
    }

    public static class TitulosViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View view;
        CardView cardView;

        private TitulosViewHolder(View view) {
            super(view);
            this.view = view;
            // Cria as views para salvar no ViewHolder
            imageView = (ImageView) view.findViewById(R.id.img);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

}
