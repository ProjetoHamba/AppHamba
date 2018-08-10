package com.apphamba.hamba.titulo.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.adaptersFragmentos.TituloLista.fragments.TituloListFragment;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;
import com.apphamba.hamba.titulo.servicos.SlopeOne;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecomendacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacao);
//        testerSlop();
        setUpToolbar();
        criarFragment(savedInstanceState);
    }

//    public void testerSlop(){
//        Titulo item1 = new Titulo();
//        item1.setNome("item1");
//        Titulo item2 = new Titulo();
//        item2.setNome("item2");
//        Titulo item3 = new Titulo();
//        item3.setNome("item3");
//
//        Usuario usuario1 = new Usuario();
//        usuario1.setSenha("123");
//        usuario1.setEmail("asdxxd@xd.xd");
//        Usuario usuario2 = new Usuario();
//        usuario2.setSenha("123");
//        usuario2.setEmail("asdxxd@xd.xd");
//        Usuario usuario3 = new Usuario();
//        usuario3.setSenha("123");
//        usuario3.setEmail("asdxxd@xd.xd");
//
//        Map<Usuario, Map<Titulo, Double>> data = new HashMap<>();
//        HashMap<Titulo, Double> user1 = new HashMap<>();
//        HashMap<Titulo, Double> user2 = new HashMap<>();
//
//        user1.put(item1, 5.0);
//        user1.put(item2, 3.0);
//        user1.put(item3, 2.0);
//        data.put(usuario1, user1);
//
//        user2.put(item1, 3.0);
//        user2.put(item2, 4.0);
//        data.put(usuario2, user2);
//
//        SlopeOne so = new SlopeOne(data);
//        HashMap<Titulo, Double> user = new HashMap<>();
//        user.put(item2,2.0);
//        user.put(item3,5.0);
//        Map<Titulo, Double> predicoes = so.predict(user);
//
//    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void criarFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ServicoTitulo servicoTitulo = new ServicoTitulo();
            ArrayList<Titulo> titulos = servicoTitulo.getRecomendacao();
            FiltroTitulo.instance.setTitulosList(titulos);
            TituloListFragment frag = new TituloListFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }

}
