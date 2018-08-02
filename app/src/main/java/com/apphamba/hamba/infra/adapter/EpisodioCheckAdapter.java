package com.apphamba.hamba.infra.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apphamba.hamba.R;

import java.util.List;
/* Versão ListView */

public class EpisodioCheckAdapter extends BaseAdapter {
    Activity activity;
    List<EpisodioView> episodiosView;
    LayoutInflater inflater;

    public EpisodioCheckAdapter(Activity activity){
        this.activity = activity;

    }
    public EpisodioCheckAdapter(Activity activity, List<EpisodioView> episodiosView){
        this.activity = activity;
        this.episodiosView = episodiosView;
        inflater = activity.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return episodiosView.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //@SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null){
            view = inflater.inflate(R.layout.list_item_episodio,viewGroup,false);
            holder = new ViewHolder();

            holder.textNumEpisodio = (TextView) view.findViewById(R.id.textViewNumEpisodio);
            holder.textDescEp =(TextView) view.findViewById(R.id.textViewEpisodioDescri);
            holder.checkBoxEp = (CheckBox) view.findViewById(R.id.checkBoxItemEpisodio);

            view.setTag(holder);

        }else
            holder = (ViewHolder) view.getTag();

        EpisodioView model = episodiosView.get(i);
        //holder.textNumEpisodio.setText(String"Episodio " + model.get);
        holder.textNumEpisodio.setText(episodiosView.get(i).toString());
        holder.textDescEp.setText(model.getDescEp());
        //if (model.isSelected())
        //    holder.checkBoxEp.setChecked(true);
        //else
        //    holder.checkBoxEp.setChecked(false);

        return view;
    }
    public void updateRecords(List<EpisodioView> episodiosView){
        this.episodiosView = episodiosView;
        notifyDataSetChanged();

    }
    class ViewHolder{
        TextView textNumEpisodio;
        TextView textDescEp;
        CheckBox checkBoxEp;

    }
}
