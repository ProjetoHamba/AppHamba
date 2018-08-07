package com.apphamba.hamba.infra.adaptersFragmentos.noticiasAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumNoticia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaNoticiasAdapter extends BaseAdapter {
    private  Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListaNoticiasAdapter(Activity act, ArrayList<HashMap<String, String>> dados) {
        activity = act;
        data = dados;
    }
    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder;
        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = getView(parent, holder);
        } else {
            holder = (ListNewsViewHolder) convertView.getTag();
        }
        HashMap<String, String> newsMap = getStringStringHashMap(position, holder);
        setHolder(holder, newsMap);
        setNewImage(holder, newsMap);
        return convertView;
    }

    private void setNewImage(ListNewsViewHolder holder, HashMap<String, String> newsMap) {
        if(newsMap.get(EnumNoticia.KEY_URLTOIMAGE.getDescricao()).toString().length() < 5){
            holder.galleryImage.setVisibility(View.GONE);
        } else{
            Picasso.with(activity)
                    .load(newsMap.get(EnumNoticia.KEY_URLTOIMAGE.getDescricao()).toString())
                    .resize(300, 200).into(holder.galleryImage);
        }
    }

    private void setHolder(ListNewsViewHolder holder, HashMap<String, String> song) {
        holder.author.setText(song.get(EnumNoticia.KEY_AUTHOR.getDescricao()));
        holder.title.setText(song.get(EnumNoticia.KEY_TITLE.getDescricao()));
        holder.time.setText(song.get(EnumNoticia.KEY_PUBLISHEDAT.getDescricao()));
        holder.sdetails.setText(song.get(EnumNoticia.KEY_DESCRIPTION.getDescricao()));
    }

    @NonNull
    private View getView(ViewGroup parent, ListNewsViewHolder holder) {
        View convertView;
        convertView = LayoutInflater.from(activity).inflate(
                R.layout.lista_noticia, parent, false);
        holder.galleryImage = convertView.findViewById(R.id.galleryImage);
        holder.author =  convertView.findViewById(R.id.author);
        holder.title =  convertView.findViewById(R.id.title);
        holder.sdetails = convertView.findViewById(R.id.sdetails);
        holder.time = convertView.findViewById(R.id.time);
        convertView.setTag(holder);
        return convertView;
    }

    private HashMap<String, String> getStringStringHashMap(int position, ListNewsViewHolder holder) {
        holder.galleryImage.setId(position);
        holder.author.setId(position);
        holder.title.setId(position);
        holder.sdetails.setId(position);
        holder.time.setId(position);

        HashMap<String, String> song;
        song = data.get(position);
        return song;
    }

    class ListNewsViewHolder {
        ImageView galleryImage;
        TextView author, title, sdetails, time;
        }
}