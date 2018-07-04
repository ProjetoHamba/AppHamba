package com.apphamba.hamba.usuario.usuario.usuarioservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


import com.apphamba.hamba.usuario.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.usuario.persistencia.UsuarioDAO;

import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;

import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.LOGIN_PREFERENCES;
import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.PASSWORD_PREFERENCES;
import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosUsuario {
    private UsuarioDAO usuarioDAO;
    private SharedPreferences sharedPreferences;

    public ServicosUsuario(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        usuarioDAO = new UsuarioDAO(context);
    }

    private long cadastrarUsuario(Usuario usuario){
        return usuarioDAO.inserirUsuário(usuario);
    }

    public long cadastrarUsuario(String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return cadastrarUsuario(usuario);
    }

    @SuppressLint("ApplySharedPref")
    public void modificarUsuario(String email, String senha){
        Long idUsuario = sharedPreferences.getLong(ID_USER_PREFERENCES, DEFAULT_ID_USER_PREFERENCES);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Usuario usuario = usuarioDAO.getUsuario(idUsuario);

        if(!email.equals("")){
            usuario.setEmail(email);
            editor.putString(LOGIN_PREFERENCES, usuario.getEmail());
        }
        if(!senha.equals("")){
            usuario.setSenha(senha);
            editor.putString(PASSWORD_PREFERENCES, usuario.getSenha());
        }

        usuarioDAO.atualizarUsuario(usuario);
        editor.commit();
    }

}
