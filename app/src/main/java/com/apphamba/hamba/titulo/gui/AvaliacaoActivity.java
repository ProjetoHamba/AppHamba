package com.apphamba.hamba.titulo.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

public class AvaliacaoActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView txtValorAvaliacao;
    private Button botaoAvaliar;
    private Double nota;
    private ImageView imageViewTituloAvaliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        setRatingBar();
        addListenerOnButton();
        setUpToolbar();
        resgatarFotoTituloAvaliado();
        mostrarNotaAtual();
    }

    public void setRatingBar() {
        ratingBar = findViewById(R.id.ratingBarUserAvaliar);
        txtValorAvaliacao = findViewById(R.id.txtValorAvaliacao);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
                nota = (double) avaliacao;
                txtValorAvaliacao.setText(String.valueOf(nota));
            }
        });
    }

    public void mostrarNotaAtual() {
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        Titulo titulo = FiltroTitulo.instance.getTituloSelecionado();
        Double avaliacao = servicoTitulo.avaliacaoTituloUsuario(titulo);
        if (avaliacao != null) {
            Float nota = avaliacao.floatValue();
            txtValorAvaliacao.setText(String.valueOf(avaliacao));
            ratingBar = findViewById(R.id.ratingBarUserAvaliar);
            ratingBar.setRating(nota);
        }
    }

    public void addListenerOnButton() {
        ratingBar = findViewById(R.id.ratingBarMediaPublicoEmGeral);
        botaoAvaliar = findViewById(R.id.botaoAvaliar);
        botaoAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            avaliar(FiltroTitulo.instance.getTituloSelecionado(), nota);
            Toast.makeText(AvaliacaoActivity.this, "Avaliação feita com sucesso " + nota, Toast.LENGTH_SHORT).show();
            Toast.makeText(AvaliacaoActivity.this, "Avaliação feita com sucesso para o título" + FiltroTitulo.instance.getTituloSelecionado().getNome(), Toast.LENGTH_SHORT).show();
            finish();
            }
        });
    }

    private Double regraDeTres(double nota) {
        double notaFinal = ((int) nota) / 5;
        return (notaFinal);
    }

    public void avaliar(Titulo titulo, Double nota) {
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        servicoTitulo.avaliar(titulo, nota);

    }
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void resgatarFotoTituloAvaliado(){
        imageViewTituloAvaliar = findViewById(R.id.imageViewTituloAvaliar);
        imageViewTituloAvaliar.setImageBitmap(FiltroTitulo.instance.getTituloSelecionado().getImagemBitmap());

    }
}
