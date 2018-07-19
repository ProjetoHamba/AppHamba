package com.apphamba.hamba.titulos.gui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.servicos.ServicoTitulo;

public class TesteImagem extends AppCompatActivity {
    private ImageView imageView,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_imagem);

        ServicoTitulo servicoTitulo = new ServicoTitulo();
        Titulo titulo = servicoTitulo.buscarTituloPorNome("Vikings");
        Bitmap imagem = servicoTitulo.byteArrayToBitmap(titulo);

        Titulo titulo1 = servicoTitulo.buscarTituloPorNome("Narcos");
        Bitmap imagem1 = servicoTitulo.byteArrayToBitmap(titulo1);

        Titulo titulo2 = servicoTitulo.buscarTituloPorNome("Game of Thrones");
        Bitmap imagem2 = servicoTitulo.byteArrayToBitmap(titulo2);

        Titulo titulo3 = servicoTitulo.buscarTituloPorNome("Prison Break");
        Bitmap imagem3 = servicoTitulo.byteArrayToBitmap(titulo3);

        imageView = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView7);
        imageView3 = findViewById(R.id.imageView5);
        imageView4 = findViewById(R.id.imageView4);

        imageView.setImageBitmap(imagem);
        imageView2.setImageBitmap(imagem1);
        imageView3.setImageBitmap(imagem2);
        imageView4.setImageBitmap(imagem3);
    }
}
