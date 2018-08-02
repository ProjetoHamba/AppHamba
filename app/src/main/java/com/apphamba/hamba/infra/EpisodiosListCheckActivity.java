package com.apphamba.hamba.infra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.adapter.EpisodioCheckAdapter;
import com.apphamba.hamba.infra.adapter.EpisodioView;

import java.util.ArrayList;
import java.util.List;

public class EpisodiosListCheckActivity extends AppCompatActivity {
    private final List<String> selecionados = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodios_list_check);

        ListView listView =(ListView) findViewById(R.id.listView);
        //Chamar a lista aq
        final List<EpisodioView> users = new ArrayList<>();
        users.add(new EpisodioView(false, "Kim"));
        users.add(new EpisodioView(false, "Eita"));
        users.add(new EpisodioView(false, "Kim"));
        users.add(new EpisodioView(false, "Eita"));
        users.add(new EpisodioView(false, "Kim"));
        users.add(new EpisodioView(false, "Eita"));
        users.add(new EpisodioView(false, "Kim"));
        users.add(new EpisodioView(false, "Eita"));
        users.add(new EpisodioView(false, "Kim"));
        users.add(new EpisodioView(false, "Eita"));


        //Na vdd ja ta criado, n precisa recriar , só era um exemplo
        final EpisodioCheckAdapter adapter = new EpisodioCheckAdapter(this,users);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EpisodioView model = users.get(position);
                if (model.isSelected())
                    model.setSelected(false);
                else
                    model.setSelected(true);
                users.set(position, model);
                //now update adapter
                adapter.updateRecords(users);
            }
        });
    }
}
