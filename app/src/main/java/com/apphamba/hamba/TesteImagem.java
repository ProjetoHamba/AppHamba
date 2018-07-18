package com.apphamba.hamba;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.apphamba.hamba.infra.PopularBanco;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.servicos.ServiceTitulos;

public class TesteImagem extends AppCompatActivity {
    private ImageView imageView,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_imagem);

        ServiceTitulos serviceTitulos = new ServiceTitulos();
        Titulo titulo = serviceTitulos.buscarTituloPorNome("Vikings",this);
        Bitmap imagem = serviceTitulos.byteArrayToBitmap(titulo);

        Titulo titulo1 = serviceTitulos.buscarTituloPorNome("Narcos",this);
        Bitmap imagem1 = serviceTitulos.byteArrayToBitmap(titulo1);

        Titulo titulo2 = serviceTitulos.buscarTituloPorNome("Game of Thrones",this);
        Bitmap imagem2 = serviceTitulos.byteArrayToBitmap(titulo2);

        Titulo titulo3 = serviceTitulos.buscarTituloPorNome("Prison Break",this);
        Bitmap imagem3 = serviceTitulos.byteArrayToBitmap(titulo3);



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
