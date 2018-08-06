package com.apphamba.hamba.infra.EpisodioCheck.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.apphamba.hamba.R;

import java.util.ArrayList;

public class EpisodioCheckNewActivity extends AppCompatActivity {

    StringBuffer sb= null;
    EpisodioCheckAdapterNew adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodio_check_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Jogar abaixo os episódios  - e retirar o método lá embaixo do getEpisodios() a mão
        adapter = new EpisodioCheckAdapterNew(this, getEpisodios());

        Button buttonMarcarEpAssist = (Button) findViewById(R.id.buttonMarcarEpAssist);
        buttonMarcarEpAssist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb = new StringBuffer();

                for (EpisodioViewNewDom p : adapter.checkedEpisodios) {
                    //abaixo pega a string position - método do EpisódioViewNewDom
                    sb.append(p.getTituloEpComNumero());
                    sb.append("\n");
                }
                if (adapter.checkedEpisodios.size() > 0) {
                    Toast.makeText(EpisodioCheckNewActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EpisodioCheckNewActivity.this, "Nenhum episódio marcado como assistido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Recycler
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        //Set adapter
        rv.setAdapter(adapter);
    }
    private ArrayList<EpisodioViewNewDom> getEpisodios(){
        ArrayList<EpisodioViewNewDom> episodioViewNewDoms = new ArrayList<>();
        EpisodioViewNewDom p= new EpisodioViewNewDom("Desc","Episódio 1 ex",10);
        episodioViewNewDoms.add(p);
        EpisodioViewNewDom d= new EpisodioViewNewDom("desc2","Episódio 2 ex",10);
        episodioViewNewDoms.add(d);
        EpisodioViewNewDom e= new EpisodioViewNewDom("desc3","Episódio 3 ex",10);
        episodioViewNewDoms.add(e);
        EpisodioViewNewDom f= new EpisodioViewNewDom("desc4","Episódio 4 ex",10);
        episodioViewNewDoms.add(f);
        EpisodioViewNewDom g= new EpisodioViewNewDom("desc5","Episódio 5 ex",10);
        episodioViewNewDoms.add(g);
        EpisodioViewNewDom h= new EpisodioViewNewDom("desc6","Episódio 6 ex",10);
        episodioViewNewDoms.add(h);
        EpisodioViewNewDom i= new EpisodioViewNewDom("desc7","Episódio 7 ex",10);
        episodioViewNewDoms.add(i);
        EpisodioViewNewDom j= new EpisodioViewNewDom("desc8","Episódio 8 ex",10);
        episodioViewNewDoms.add(j);

        return episodioViewNewDoms;
    }
}
