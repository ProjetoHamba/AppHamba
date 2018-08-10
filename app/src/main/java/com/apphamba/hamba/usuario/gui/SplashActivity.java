package com.apphamba.hamba.usuario.gui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.persistencia.PopularBanco;
import com.apphamba.hamba.infra.utils.Permissoes;

public class SplashActivity extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PopularBanco.criador.popularBanco();

        String permissions[] = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        boolean ok = Permissoes.validate(this, 0, permissions);

        if (ok) {
            Handler handler = new Handler();
            handler.postDelayed(this, 2000);
        }
    }
    public void run() {
        startActivity(new Intent(this, EscolhaCadOuLoginActivity.class));
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults){
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "É preciso autorizar as permissões para utilizar o app", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        run();
    }
}
