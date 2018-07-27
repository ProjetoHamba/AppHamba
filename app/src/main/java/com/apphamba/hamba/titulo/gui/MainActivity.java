package com.apphamba.hamba.titulo.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.FiltroTitulo;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.fragments.TituloListFragment;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;
import com.apphamba.hamba.usuario.gui.EscolhaConfiguracaoActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {

            ServicoTitulo servicoTitulo = new ServicoTitulo();
            FiltroTitulo.instance.setTitulosList(servicoTitulo.getTitulos());

            getSupportFragmentManager().beginTransaction().add(R.id.fragContainer, new TituloListFragment(), null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        TextView nomeUsuario, email;
        nomeUsuario = findViewById(R.id.nomeView);
        email = findViewById(R.id.emailView);
        nomeUsuario.setText(Sessao.instance.getPessoa().getNome());
        email.setText(Sessao.instance.getPessoa().getUsuario().getEmail());
        getMenuInflater().inflate(R.menu.search_com_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {

        } else if (id == R.id.action_linear){

        } else if (id == R.id.action_grid){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle inicio action


        } else if (id == R.id.nav_meu_hamba) {
            startActivity(new Intent(this, MeuHambaActivity.class));

        } else if (id == R.id.nav_calendario) {
            //startActivity(new Intent(this,CalendarioActivity.class));


        } else if (id == R.id.nav_favoritos) {
            startActivity(new Intent(this,FavoritosActivity.class));

        } else if (id == R.id.nav_recomendacoes) {
            //startActivity(new Intent(this,RecomendacoesActivity.class));
            finish();

        } else if (id == R.id.nav_configuracoes) {
            startActivity(new Intent(this, EscolhaConfiguracaoActivity.class));

        } else if (id == R.id.nav_noticias) {
            //startActivity(new Intent(this, NoticiasActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
