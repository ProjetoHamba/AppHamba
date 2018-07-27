package com.apphamba.hamba.usuario.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.persistencia.PopularBanco;

public class SplashActivity extends Activity implements Runnable {

    /**
     * @param savedInstanceState Objeto da classe Bundle que contem o estado anterior da activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PopularBanco.criador.popularBanco();

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    /**
     * @see EscolhaCadOuLoginActivity
     */

    public void run() {
        startActivity(new Intent(this, EscolhaCadOuLoginActivity.class));
        finish();
    }
}
