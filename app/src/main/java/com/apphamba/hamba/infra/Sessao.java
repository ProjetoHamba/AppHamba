package com.apphamba.hamba.infra;

import android.app.Application;
import android.util.Log;

import com.apphamba.hamba.usuario.dominio.Pessoa;

import java.util.HashMap;
import java.util.Map;

//No Android é comum desenv ut variáveis de classe estátiscas para armazenar info globais da
//aplicação, mas no Android isso n é recomendado
//O correto é criar uma classe de Application customizada que herda de android.app.application
//a qual faz parte do ciclo de vida da aplicação, e o Android vai criar essa classe junto
// ao processo da aplicação
//A classe Application é um Singleton - pode ser utlizado para arm info de forma global no app
//Singleton - Singleton é um padrão de projeto de software (do inglês Design Pattern).
// Este padrão garante a existência de apenas uma instância de uma classe,
// mantendo um ponto global de acesso ao seu objeto
public class Sessao extends Application {
    private static final String TAG ="Sessao";
    public static final Sessao instance = new Sessao();
<<<<<<< HEAD
    private final Map<String,Object> values = new HashMap<>();
    
    
    //Sempre que precisarmos acessar essa classe para salvar ou ler info globais, basta utilizar
    //Sessao = Sessao.getInstance();
    public static Sessao getInstance(){
        return instance; //Singleton
    }

    public void setUsuario(Usuario usuario) {
        setValor("sessao.Usuario", usuario);
    }
=======
    private final Map<String, Object> values = new HashMap<>();
>>>>>>> 084df1349b3a16a3be6d215cddd401050b43908c

    public void setPessoa(Pessoa pessoa) {
        setValor("sessao.Pessoa", pessoa);
    }

    public Pessoa getPessoa() {
        return (Pessoa) values.get("sessao.Pessoa");
    }

    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }
<<<<<<< HEAD
    
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "Sessao.onCreate()"); //Salva a instância para termos acesso como singleton
        //instance = this;              --- dando erro nessa linha
    } 
    @Override
    //Quando o Android terminar o processo da aplicação esse método será chamado para limpar os recursos
    public void onTerminate(){
        super.onTerminate();
        Log.d(TAG,"Sessao.onTerminate()");
    }
=======

    public void reset() {
        this.values.clear();
    }

>>>>>>> 084df1349b3a16a3be6d215cddd401050b43908c
}
