package com.apphamba.hamba.noticias.gui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumNoticia;
import com.apphamba.hamba.infra.adaptersFragmentos.noticiasAdapter.ListaNoticiasAdapter;
import com.apphamba.hamba.noticias.servico.ServicoNoticia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class NoticiasActivity extends AppCompatActivity {
    ListView listNews;
    ProgressBar loader;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listNews = findViewById(R.id.listNews);
        loader = findViewById(R.id.loader);
        listNews.setEmptyView(loader);
        isOnline();
    }

    private void isOnline() {
        if(ServicoNoticia.isNetworkAvailable(getApplicationContext()))
        {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        }else{
            Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_LONG).show();
        }
    }

    public class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... strings) {
            String xml;
            String urlParameters = "";
            xml = ServicoNoticia.excuteGet("https://newsapi.org/v2/everything?q=filmes&apiKey=f7d880a8399949f4b8c350b2d157533c", urlParameters);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {
            if(xml.length()>10){
                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        adicionarArtigo(jsonArray, i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erro não esperado", Toast.LENGTH_SHORT).show();
                }
                criarAdapter();
            } else{
                Toast.makeText(getApplicationContext(), "Nenhuma notícia encontrada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void adicionarArtigo(JSONArray jsonArray, int i) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(EnumNoticia.KEY_AUTHOR.getDescricao(), jsonObject.optString(EnumNoticia.KEY_AUTHOR.getDescricao().toString()));
        map.put(EnumNoticia.KEY_TITLE.getDescricao(), jsonObject.optString(EnumNoticia.KEY_TITLE.getDescricao()).toString());
        map.put(EnumNoticia.KEY_DESCRIPTION.getDescricao(), jsonObject.optString(EnumNoticia.KEY_DESCRIPTION.getDescricao().toString()));
        map.put(EnumNoticia.KEY_URL.getDescricao(), jsonObject.optString(EnumNoticia.KEY_URL.getDescricao()).toString());
        map.put(EnumNoticia.KEY_URLTOIMAGE.getDescricao(), jsonObject.optString(EnumNoticia.KEY_URLTOIMAGE.getDescricao()).toString());
        map.put(EnumNoticia.KEY_PUBLISHEDAT.getDescricao(), jsonObject.optString(EnumNoticia.KEY_PUBLISHEDAT.getDescricao()).toString());
        dataList.add(map);
    }

    private void criarAdapter() {
        ListaNoticiasAdapter adapter = new ListaNoticiasAdapter(NoticiasActivity.this, dataList);
        listNews.setAdapter(adapter);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NoticiasActivity.this, DetalhesNoticiaAcitvity.class);
                intent.putExtra("url", dataList.get(+position).get(EnumNoticia.KEY_URL.getDescricao()));
                startActivity(intent);
            }
        });
    }

}