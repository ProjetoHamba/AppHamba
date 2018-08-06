package com.apphamba.hamba.infra.EpisodioCheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.apphamba.hamba.R;

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
        holder.numEp.setText(episodioViewNewDoms.get(position).getTituloEpComNumero());
        holder.descEp.setText(episodioViewNewDoms.get(position).getDescEpis());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk = (CheckBox) v;

                //CKE se está checado ou n
                if(chk.isChecked()){
                    checkedEpisodios.add(episodioViewNewDoms.get(pos));

                }else if(!chk.isChecked()){
                    checkedEpisodios.remove(episodioViewNewDoms.get(pos));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return episodioViewNewDoms.size();
    }
}
