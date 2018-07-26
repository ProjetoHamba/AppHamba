package com.apphamba.hamba.titulo.gui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.ComunicadorTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;

public class DetalhesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Titulo dados = ComunicadorTitulo.instance.getTituloSelecionado(); //TODO ANDERSON VERIFICAR SE TA CERTO
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(dados.getImagemBitmap());
        TextView nome = (TextView) findViewById(R.id.textView3);
        nome.setText(dados.getNome());
        TextView avaliacao = (TextView) findViewById(R.id.textView7);
        avaliacao.setText(String.valueOf(dados.getAvaliacao()));
        TextView sinopse = (TextView) findViewById(R.id.textView);
        sinopse.setText(dados.getSinopse());
        TextView criadores = (TextView) findViewById(R.id.textView5);
        criadores.setText(dados.getCriadores());
        TextView generos = (TextView) findViewById(R.id.textView6);
        generos.setText(dados.getGeneros());
    }
}
