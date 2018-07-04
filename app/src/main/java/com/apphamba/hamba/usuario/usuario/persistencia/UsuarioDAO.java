package com.apphamba.hamba.usuario.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.usuario.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.infra.DataBase;
import com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences;

public class UsuarioDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public SQLiteDatabase getBancoParaLeitura(Context context){
        DataBase auxDataBase = new DataBase(context);
        liteDatabase = auxDataBase.getReadableDatabase();
        return liteDatabase;
    }

    public SQLiteDatabase getBancoParaEscrita(Context context) {
        DataBase auxDataBase = new DataBase(context);
        liteDatabase = auxDataBase.getWritableDatabase();
        return liteDatabase;

    }

    public boolean dataBaseVerificarEmail(String email) {
        String where = "SELECT * FROM usuario WHERE email = '"+email+"'";
        Cursor cursor =liteDatabase.rawQuery(where,null);
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }

    public void inserir(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("email",usuario.getEmail());
        valores.put("senha",usuario.getSenha());
        liteDatabase.insert("usuario",null,valores);
        liteDatabase.close();
    }

    public boolean liberarLogin(String email,String senha){
        String onde = "SELECT * FROM usuario WHERE email = '"+email+"'"+"AND senha = '"+senha+"'";
        Cursor cursor = liteDatabase.rawQuery(onde,null);
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }


}
