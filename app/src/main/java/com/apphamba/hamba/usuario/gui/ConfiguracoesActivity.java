package com.apphamba.hamba.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.gui.TelaComMenuActivity;

public class ConfiguracoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    private void retornoMenuPrincipal(){ this.mudarTela(TelaComMenuActivity.class);}
    public void retornoMenuPrincipal(View view){
        this.retornoMenuPrincipal();
    }
    @Override
    public void onBackPressed() {
        this.retornoMenuPrincipal();
    }

    //Modificar aqui para modificar no Banco
    public void onClickModificar(View view) {
        //Por enq resgatando os dados escritos em cada text/ id do xml
        EditText edtEmailNovo = findViewById(R.id.edtEmailNovo);
        EditText edtConfEmail = findViewById(R.id.edtConfEmail);
        EditText edtSenhaNova = findViewById(R.id.edtSenhaNova);
        EditText edtConfSenhaNova = findViewById(R.id.edtSenhaConf);
        String emailNovo = edtEmailNovo.getText().toString();
        String emailNovoConf = edtConfEmail.getText().toString();
        String senhaNova = edtSenhaNova.getText().toString();
        String senha = edtSenhaNova.getText().toString();

        //falta os ifs e as chamadas dos outros metodos para finalizar atualizacao

    }

}
