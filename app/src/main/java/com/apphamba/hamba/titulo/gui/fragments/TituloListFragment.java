package com.apphamba.hamba.titulo.gui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.gui.adapter.TituloAdapter;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import java.util.List;

public class TituloListFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Titulo> titulos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulos_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3 ));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        titulos = servicoTitulo.getTitulos();
        recyclerView.setAdapter(new TituloAdapter(getContext(), titulos, onClickTitulo()));

        return view;
    }

    private TituloAdapter.TituloOnClickListener onClickTitulo() {
        return new TituloAdapter.TituloOnClickListener() {
            @Override
            public void onClickTitulo(TituloAdapter.TitulosViewHolder holder, int indexTitulo) {
                Titulo titulo = titulos.get(indexTitulo);
                Toast.makeText(getContext(), titulo.getNome(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
