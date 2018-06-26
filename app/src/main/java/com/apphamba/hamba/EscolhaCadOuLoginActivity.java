package com.apphamba.hamba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apphamba.hamba.usuario.gui.CadastroActivity;
import com.apphamba.hamba.usuario.gui.LoginActivity;

public class EscolhaCadOuLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_cad_ou_login);
    }
    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    public void cliqueBotaoEntrar(View view){
        this.mudarTela(LoginActivity.class);
    }
    public void cliqueBotaoCadastrar(View view){this.mudarTela(CadastroActivity.class);}
}
