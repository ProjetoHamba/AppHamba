package com.apphamba.hamba.titulo.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.adapter.TabsAdapter;
import com.apphamba.hamba.infra.Sessao;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.fragments.TituloListFragment;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.noticias.gui.NoticiasActivity;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;
import com.apphamba.hamba.usuario.gui.EscolhaCadOuLoginActivity;
import com.apphamba.hamba.usuario.gui.EscolhaConfiguracaoActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolbarComMenuNavAbreEFecha();
        ViewDoMenuNavListaClicavel();
        DefinindoViewPagerComTab();

    }
    private void ToolbarComMenuNavAbreEFecha(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void ViewDoMenuNavListaClicavel(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void DefinindoViewPagerComTab() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.fragContainer);
        viewPager.setAdapter(new TabsAdapter(getContext(), getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(), R.color.colorWhite);
        tabLayout.setTabTextColors(cor, cor);
        int corTabSelecionada= ContextCompat.getColor(getContext(), R.color.colorBlack);
        tabLayout.setSelectedTabIndicatorColor(corTabSelecionada);
        int tabPosition = Sessao.instance.getTabAtiva();
        viewPager.setCurrentItem(tabPosition);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                Sessao.instance.setTabAtiva(position);
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

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
        int id = item.getItemId();
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
        int id = item.getItemId();
        if (id == R.id.nav_inicio) {
        } else if (id == R.id.nav_meu_hamba) {
            startActivity(new Intent(this, MeuHambaActivity.class));
        } else if (id == R.id.nav_sair) {
            startActivity(new Intent(this,EscolhaCadOuLoginActivity.class));
        } else if (id == R.id.nav_favoritos) {
            startActivity(new Intent(this, FavoritosActivity.class));
        } else if (id == R.id.nav_recomendacoes) {
            startActivity(new Intent(this,RecomendacaoActivity.class));
        } else if (id == R.id.nav_configuracoes) {
            startActivity(new Intent(this, EscolhaConfiguracaoActivity.class));
        } else if (id == R.id.nav_noticias) {
            startActivity(new Intent(this, NoticiasActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected Context getContext() {
        return this;
    }

}
