package com.apphamba.hamba.infra.EpisodioCheck.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apphamba.hamba.R;

public class EpisodioCheckHolderNew extends RecyclerView.ViewHolder implements View.OnClickListener {
    CheckBox chk;
    TextView numEp;
    TextView descEp;
    ItemClickListener itemClickListener;

    public EpisodioCheckHolderNew(View itemView) {
        super(itemView);

        numEp = itemView.findViewById(R.id.textViewNumEpisodio);
        descEp = itemView.findViewById(R.id.textViewEpisodioDescri);
        chk = itemView.findViewById(R.id.checkBoxItemEpisodio);

        chk.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
}
