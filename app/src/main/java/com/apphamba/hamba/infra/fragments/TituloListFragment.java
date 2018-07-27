package com.apphamba.hamba.infra.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.infra.adapter.TituloAdapter;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.gui.DetalhesActivity;
import com.apphamba.hamba.titulo.gui.TituloView;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import java.util.ArrayList;
import java.util.List;

public class TituloListFragment extends Fragment {



    private RecyclerView recyclerView;
    private List<TituloView> titulosView;
    private ActionMode actionMode;
    private ServicoTitulo servicoTitulo = new ServicoTitulo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulos_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        titulosView = this.tituloToTituloView(FiltroTitulo.instance.getTitulosList());
        recyclerView.setAdapter(new TituloAdapter(getContext(), titulosView, onClickTitulo()));


        return view;
    }

    private TituloAdapter.TituloOnClickListener onClickTitulo() {
        return new TituloAdapter.TituloOnClickListener() {
            @Override
            public void onClickTitulo(TituloAdapter.TitulosViewHolder holder, int indexTitulo) {

                Titulo titulo = titulosView.get(indexTitulo).getTitulo();
                if (actionMode == null) {
                    Toast.makeText(getContext(), titulo.getNome(), Toast.LENGTH_SHORT).show();
                    FiltroTitulo.instance.setTituloSelecionado(titulo);
                   Intent intent = new Intent(getContext(), DetalhesActivity.class);
                   startActivity(intent);

                } else {
                    titulosView.get(indexTitulo).setSelecionado(true);
                    updateActionModeTitle();
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onLongClickTitulo(TituloAdapter.TitulosViewHolder holder, int indexTitulo) {
                if (actionMode != null) {
                    return;
                }
                actionMode = getAppCompatActivity().
                        startSupportActionMode(getActionModeCallback());
                TituloView titulo = titulosView.get(indexTitulo);
                titulo.setSelecionado(true);
                recyclerView.getAdapter().notifyDataSetChanged();
                updateActionModeTitle();


            }
        };
    }

    private void updateActionModeTitle() {
        if (actionMode != null) {
            actionMode.setTitle("Selecione os titulos.");
            actionMode.setSubtitle(null);
            List<TituloView> selectedTitulos = getSelectedTitulos();
            if (selectedTitulos.size() == 1) {
                actionMode.setSubtitle("1 titulo selecionado");
            } else if (selectedTitulos.size() > 1) {
                actionMode.setSubtitle(selectedTitulos.size() + " titulos selecionados");
            }

        }
    }
    private List<TituloView> getSelectedTitulos() {
        List<TituloView> list = new ArrayList<TituloView>();
        for (TituloView titulo : this.titulosView) {
            if (titulo.getSelecionado()) {
                list.add(titulo);
            }
        }
        return list;
    }


    private ActionMode.Callback getActionModeCallback() {
        return new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getActivity().getMenuInflater();
                inflater.inflate(R.menu.menu_frag_titulos_cab, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                List<TituloView> selectedTitulos = getSelectedTitulos();
                if (item.getItemId() == R.id.action_adicionar_meu_hamba) {
                    try {
                        for (TituloView tituloView : selectedTitulos) {
                            if (!servicoTitulo.verificarMeuHamba(tituloView.getTitulo())){
                                servicoTitulo.adicionarMeuHamba(tituloView.getTitulo());
                            }
                        }
                    } finally {

                    }
                    snack(recyclerView, "Títulos adicionados com sucesso.");

                } else if (item.getItemId() == R.id.action_adicionar_meus_fav) {
                    try {
                        for (TituloView tituloView : selectedTitulos) {
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())){
                                servicoTitulo.adicionarFavorito(tituloView.getTitulo());
                            }
                        }
                    } finally {

                    }
                    snack(recyclerView, "Títulos adicionados com sucesso.");

                } else if (item.getItemId() == R.id.action_share) {

                } else if (item.getItemId() == R.id.action_remove_fav) {
                    try {
                        for (TituloView tituloView : selectedTitulos){
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())) {
                                servicoTitulo.removerFavorito(tituloView.getTitulo());
                            }
                        }
                    } finally {

                    }
                    snack(recyclerView, "Títulos excluídos com sucesso.");

                } else if (item.getItemId() == R.id.action_remove_meu_hamba) {

                    try {
                        for (TituloView tituloView : selectedTitulos) {
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())) {
                                servicoTitulo.removerMeuHamba(tituloView.getTitulo());
                            }
                        }
                    } finally {

                    }
                    snack(recyclerView, "Títulos excluídos com sucesso.");

                }

                mode.finish();
                return true;
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                for (TituloView titulo : titulosView) {
                    titulo.setSelecionado(false);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        };
    }
    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }
    protected void snack(View view, String msg) {
        snack(view, msg, null);
    }
    protected void snack(View view, String msg, final Runnable runnable) {
        Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                })
                .show();
    }

    private ArrayList<TituloView> tituloToTituloView(ArrayList<Titulo> titulos){
        ArrayList<TituloView> tituloViews = new ArrayList<>();
        for (Titulo titulo:titulos) {
            TituloView tituloView = new TituloView();
            tituloView.setTitulo(titulo);
            tituloViews.add(tituloView);
        }
        return tituloViews;
    }

}
