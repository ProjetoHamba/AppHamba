package com.apphamba.hamba.infra.EpisodioCheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.gui.TituloView;

import java.util.List;

public class EpisodioCheckAdapter extends RecyclerView.Adapter<EpisodioCheckAdapter.EpisodiosCheckViewHolder> {
    protected static final String TAG = "hamba";
    private final List<TituloView> titulos;
    private final Context context;
    private final EpisodioCheckOnClickListener onClickListener;

    public interface EpisodioCheckOnClickListener {
        void onClickEpisodioCheck(EpisodiosCheckViewHolder holder, int indexTitulo);
        void onLongClickEpisodioCheck(EpisodiosCheckViewHolder holder, int indexTitulo);
    }

    public EpisodioCheckAdapter(Context context, List<TituloView> titulos, EpisodioCheckOnClickListener onClickListener) {
        this.context = context;
        this.titulos = titulos;
        this.onClickListener = onClickListener;
    }

    @Override
    public EpisodiosCheckViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_episodio_check, viewGroup, false);
        EpisodiosCheckViewHolder holder = new EpisodiosCheckViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final EpisodiosCheckViewHolder holder, final int position) {
        // Atualizada a view
        //holder.textNumEpisodio.setText();
        //if holder.checkBoxEp.isChecked()
       // holder.checkBoxEp.setChecked(true);
        //holder.checkBoxEp.isChecked();

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chama o listener para informar que clicou no Titulo
                    onClickListener.onClickEpisodioCheck(holder, position);

                }
            });
            // Click longo
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClickListener.onLongClickEpisodioCheck(holder, position);
                    return true;
                }
            });
        }
        // Pinta o fundo de azul se a linha estiver selecionada
        int corFundo = context.getResources().getColor(titulos.get(position).getSelecionado() ? R.color.colorBlue : R.color.colorWhite);
        //holder.cardView.setCardBackgroundColor(corFundo);


    }

    @Override
    public int getItemCount() {
        return this.titulos != null ? this.titulos.size() : 0;
    }

    public static class EpisodiosCheckViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textNumEpisodio;
        TextView textDescEp;
        CheckBox checkBoxEp;

        public EpisodiosCheckViewHolder(View view) {
            super(view);
            this.view = view;
            // Cria as views para salvar no ViewHolder

            textNumEpisodio = (TextView) view.findViewById(R.id.textViewNumEpisodio);
            textDescEp =(TextView) view.findViewById(R.id.textViewEpisodioDescri);
            checkBoxEp = (CheckBox) view.findViewById(R.id.checkBoxItemEpisodio);

        }
    }

}
