package com.apphamba.hamba.titulos.gui;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;

public abstract class BaseActivity extends android.support.v7.app.AppCompatActivity {
    protected DrawerLayout drawerLayout;

    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
    protected Activity getActivity() {
        return this;
    }
    protected Context getContext() {
        return this;
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Ícone do menu do nav drawer
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            if (navigationView != null && drawerLayout != null) {
                // Atualiza os dados do header do Navigation View
                setNavViewValues(navigationView, R.string.nav_drawer_username,
                        R.string.nav_drawer_email, R.mipmap.ic_logo_launcher);

                // Trata o evento de clique no menu.
                navigationView.setNavigationItemSelectedListener(
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(MenuItem menuItem) {
                                // Seleciona a linha
                                menuItem.setChecked(true);
                                // Fecha o menu
                                drawerLayout.closeDrawers();
                                // Trata o evento do menu
                                onNavDrawerItemSelected(menuItem);
                                return true;
                            }
                        });
            }
        }
    }

    // Atualiza os dados do header do Navigation View
    public static void setNavViewValues(NavigationView navView, int nome, int email, int img) {
        View headerView = navView.getHeaderView(0);
        TextView tNome = (TextView) headerView.findViewById(R.id.textViewNavNome);
        TextView tEmail = (TextView) headerView.findViewById(R.id.textViewNavEmail);
        ImageView imgView = (ImageView) headerView.findViewById(R.id.NavImageView);
        tNome.setText(nome);
        tEmail.setText(email);
        imgView.setImageResource(img);
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_inicio:
                // Nada aqui pois somente a MainActivity possui menu lateral
                break;
            case R.id.nav_meu_hamba:
                //Intent intent = new Intent(getContext(), MeuHambaActivity.class);
                //intent.putExtra("tipo", R.string.nav_meu_hamba_text);
                //startActivity(intent);
                break;
            case R.id.nav_calendario:
                //intent = new Intent(getContext(), CalendarioActivity.class);
                //intent.putExtra("tipo", R.string.nav_calendario_text);
                //startActivity(intent);
                break;
            case R.id.nav_favoritos:
                //intent = new Intent(getContext(), FavoritosActivity.class);
                //intent.putExtra("tipo", R.string.nav_favoritos_text);
                //startActivity(intent);
                break;
            case R.id.nav_recomendacoes:
                //startActivity(new Intent(getContext(), RecomendacoesActivity.class));
                break;
            case(R.id.nav_configuracoes):
                //startActivity(new Intent(this, ConfiguracoesActivity.class));
                break;
            case(R.id.nav_noticias):
                //startActivity(new Intent(this, NoticiasActivity.class));
                break;
        }
    }

    // Adiciona o fragment no centro da tela
    //protected void replaceFragment(Fragment frag) {
    //toast("OK!");
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Trata o clique no botão que abre o menu.
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}