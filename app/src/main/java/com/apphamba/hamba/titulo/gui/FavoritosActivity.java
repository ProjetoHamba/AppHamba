package com.apphamba.hamba.titulo.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.fragments.TituloListFragment;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        setUpToolbar();
        criarFragment(savedInstanceState);
    }

    private void criarFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ServicoTitulo servicoTitulo = new ServicoTitulo();
            FiltroTitulo.instance.setTitulosList(servicoTitulo.getFavoritos());
            TituloListFragment frag = new TituloListFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_com_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {

        } else if (id == R.id.action_linear){

        } else if (id == R.id.action_grid){

        }
        return super.onOptionsItemSelected(item);
    }

}
