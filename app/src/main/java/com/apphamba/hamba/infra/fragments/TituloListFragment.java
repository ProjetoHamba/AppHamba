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
    private List<TituloView> titulosView; //É UMA LISTA DE VIEWS AGR
    private ActionMode actionMode;
    private ServicoTitulo servicoTitulo = new ServicoTitulo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulos_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        //Função abaixo pega os titulos pela função do dominio e get()
        //titulos = Titulo.getTitulos();
        //ArrayList<Titulo> titulos = servicoTitulo.getTitulos(); // PEGA OS TITULOS DO BANCO
        //titulosView = tituloToTituloView(titulos); // TRANSFORMA EM TITULOVIEW CTRL+CLICK PRA VER A FUNÇÃO
        titulosView = this.tituloToTituloView(FiltroTitulo.instance.getTitulosList());
        recyclerView.setAdapter(new TituloAdapter(getContext(), titulosView, onClickTitulo())); //PASSO A LISTA NO ADAPTER

        return view;
    }

    private TituloAdapter.TituloOnClickListener onClickTitulo() {
        return new TituloAdapter.TituloOnClickListener() {
            @Override
            public void onClickTitulo(TituloAdapter.TitulosViewHolder holder, int indexTitulo) {

                Titulo titulo = titulosView.get(indexTitulo).getTitulo(); //O TITULO AGR É UM ATRIBUTO DO TITULOVIEW
                if (actionMode == null) {
                    Toast.makeText(getContext(), titulo.getNome(), Toast.LENGTH_SHORT).show();
                    FiltroTitulo.instance.setTituloSelecionado(titulo); //COLOCANDO O TITULO NA SESSÃO
                   Intent intent = new Intent(getContext(), DetalhesActivity.class);
                   startActivity(intent);

                } else { // Se a CAB está ativada
                    // Seleciona o titulo
                    titulosView.get(indexTitulo).setSelecionado(true); // SELECIONADO É UM ATRIBUTO DO TITLE VIEW
                    // Atualiza o título com a quantidade de titulos selecionados
                    updateActionModeTitle();
                    // Redesenha a lista
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onLongClickTitulo(TituloAdapter.TitulosViewHolder holder, int indexTitulo) {
                if (actionMode != null) {
                    return;
                }
                // Liga a action bar de contexto (CAB)
                actionMode = getAppCompatActivity().
                        startSupportActionMode(getActionModeCallback());
                TituloView titulo = titulosView.get(indexTitulo);
                titulo.setSelecionado(true); // Seleciona o tituloVIEW
                // Solicita ao Android para desenhar a lista novamente
                recyclerView.getAdapter().notifyDataSetChanged();
                // Atualiza o título para mostrar a quantidade de titulos selecionados
                updateActionModeTitle();


            }
        };
    }
    // Atualiza o título da action bar (CAB)
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
            //função abaixo que chama o metódo de compartilhamento, usando a lista de selecionados, ainda n colocado
           // updateShareIntent(selectedTitulos);
        }
    }
    // Retorna a lista de titulos selecionados
    //MUDEI APENAS OS PARAMETROS DE ENTRA E DE SAÍDA
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
                // Infla o menu específico da action bar de contexto (CAB)
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
                        // db.close();
                    }
                    snack(recyclerView, "Títulos adicionados com sucesso.");

                } else if (item.getItemId() == R.id.action_adicionar_meus_fav) {
                    //TituloDB db = new TituloDB(getContext());
                    try {
                        for (TituloView tituloView : selectedTitulos) {
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())){
                                servicoTitulo.adicionarFavorito(tituloView.getTitulo());
                            }
                        }
                    } finally {
                        // db.close();
                    }
                    snack(recyclerView, "Títulos adicionados com sucesso.");

                } else if (item.getItemId() == R.id.action_share) {
                    // Dispara a tarefa para fazer download das fotos
                    //startTask("compartilhar", new CompartilharTask(selectedTitulos));
                } else if (item.getItemId() == R.id.action_remove_fav) {
                    try {
                        for (TituloView tituloView : selectedTitulos){
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())) {
                                servicoTitulo.removerFavorito(tituloView.getTitulo());
                            }
                        }
                    } finally {
                        // db.close();
                    }
                    snack(recyclerView, "Títulos excluídos com sucesso.");

                } else if (item.getItemId() == R.id.action_remove_meu_hamba) {
                    //TituloDB db = new TituloDB(getContext());
                    try {
                        for (TituloView tituloView : selectedTitulos) {
                            if (!servicoTitulo.verificarFavorito(tituloView.getTitulo())) {
                                servicoTitulo.removerMeuHamba(tituloView.getTitulo());
                            }
                        }
                    } finally {
                        // db.close();
                    }
                    snack(recyclerView, "Títulos excluídos com sucesso.");

                }
                // Encerra o action mode

                mode.finish();
                return true;
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Limpa o estado
                actionMode = null;
                // Configura todos os titulos para não selecionados
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

    private ArrayList<TituloView> tituloToTituloView(ArrayList<Titulo> titulos){ //FUNÇÃO PARA TRANSFORMAR A LISTA QUE VEM DO SERVIÇO EM UMA LISTA DE VIEWS
        ArrayList<TituloView> tituloViews = new ArrayList<>();
        for (Titulo titulo:titulos) {
            TituloView tituloView = new TituloView();
            tituloView.setTitulo(titulo);
            tituloViews.add(tituloView);
        }
        return tituloViews;
    }

}
