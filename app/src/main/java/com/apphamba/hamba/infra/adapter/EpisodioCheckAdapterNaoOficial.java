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


public class EpisodioCheckAdapterNaoOficial extends BaseAdapter {
    Activity activity;
    List<EpisodioView> episodiosView;
    LayoutInflater inflater;

    public EpisodioCheckAdapterNaoOficial(Activity activity){
        this.activity = activity;

    }
    public EpisodioCheckAdapterNaoOficial(Activity activity, List<EpisodioView> episodiosView){
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


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null){
            view = inflater.inflate(R.layout.adapter_episodio_check,viewGroup,false);
            holder = new ViewHolder();
            holder.textNumEpisodio = (TextView) view.findViewById(R.id.textViewNumEpisodio);
            holder.textDescEp =(TextView) view.findViewById(R.id.textViewEpisodioDescri);
            holder.checkBoxEp = (CheckBox) view.findViewById(R.id.checkBoxItemEpisodio);
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
            EpisodioView episodioView = episodiosView.get(i);
            holder.textNumEpisodio.setText(episodiosView.get(i).toString());
            holder.textDescEp.setText(episodioView.getDescEp());
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
