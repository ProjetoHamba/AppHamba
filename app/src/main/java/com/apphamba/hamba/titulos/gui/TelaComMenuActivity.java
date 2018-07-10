package com.apphamba.hamba.titulos.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apphamba.hamba.R;

public class TelaComMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * O {@link android.support.v4.view.PagerAdapter} que irá fornecer
     * fragmentos para cada uma das seções. Nós usamos um
     * {@link FragmentPagerAdapter} derivado, que irá manter cada
     * fragmento carregado na memória. Se isso se tornar muito intensivo em memória,
     * pode ser melhor mudar para um
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private TelaComMenuActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * O {@link ViewPager} que hospedará o conteúdo da seção.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_com_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Cria o adaptador que retornará um fragmento para cada um dos três
        // seções principais da atividade
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());;
        // Configura o ViewPager com o adaptador de seções.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Infla o menu; isso adiciona itens à barra de ação, se estiver presente.
        getMenuInflater().inflate(R.menu.tela_com_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Lidar com cliques de itens da barra de ação aqui. A barra de ação será
        // lida automaticamente com cliques no botão Início / Acima, por muito tempo
        // conforme você especifica uma atividade pai em AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Lidar com item de visualização de navegação clica aqui.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle inicio action
        } else if (id == R.id.nav_meu_hamba) {
            //colocar aq a ação do clique

        } else if (id == R.id.nav_calendario) {

        } else if (id == R.id.nav_favoritos) {

        } else if (id == R.id.nav_recomendacoes) {

        } else if (id == R.id.nav_configuracoes) {

        } else if (id == R.id.nav_noticias) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* A placeholder fragment contém um simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * O argumento do fragmento representando o número da seção para este
         fragmento
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Retorna uma nova instância deste fragmento para a seção dada
         * número.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        //aqui é o texto e a mudança dele em cada tab AAAAAAAATENÇÃO ooooooooooooooooo
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tela_com_menu, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * Um {@link FragmentPagerAdapter} que retorna um fragmento correspondente a
     * uma das seções / guias / páginas
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem é chamado para instanciar o fragmento da página especificada.
            // Retorna um PlaceholderFragment (definido como uma classe interna estática abaixo).
            return TelaComMenuActivity.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Mostrar 3 paginas no total.
            return 3;
        }
    }
}
