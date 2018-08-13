package com.apphamba.hamba.infra.adaptersFragmentos.EpisodioCheck;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.apphamba.hamba.R;

public class EpisodioCheckHolderNew extends RecyclerView.ViewHolder implements View.OnClickListener {
    CheckBox checkBox;
    TextView numeroEpisodio;
    TextView descricaoEpisodio;
    ItemClickListener itemClickListener;
    public EpisodioCheckHolderNew(View itemView) {
        super(itemView);
        numeroEpisodio = itemView.findViewById(R.id.textViewNumEpisodio);
        descricaoEpisodio = itemView.findViewById(R.id.textViewEpisodioDescri);
        checkBox = itemView.findViewById(R.id.checkBoxItemEpisodio);
        checkBox.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view,getLayoutPosition());
    }
}