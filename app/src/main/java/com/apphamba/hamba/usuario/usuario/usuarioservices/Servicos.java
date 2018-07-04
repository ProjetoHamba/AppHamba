package com.apphamba.hamba.usuario.usuario.usuarioservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.apphamba.hamba.usuario.infra.FormataData;
import com.apphamba.hamba.usuario.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.usuario.dominio.Usuario;

import com.apphamba.hamba.usuario.usuario.persistencia.PessoaDAO;
import com.apphamba.hamba.usuario.usuario.persistencia.UsuarioDAO;

import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;
import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.LOGIN_PREFERENCES;
import static com.apphamba.hamba.usuario.infra.ConstanteSharedPreferences.PASSWORD_PREFERENCES;


public class Servicos {
    private UsuarioDAO usuarioDAO;
    private ServicosPessoa servicosPessoa;
    private ServicosUsuario servicosUsuario;
    private PessoaDAO pessoaDAO;
    private SharedPreferences sharedPreferences;

    public Servicos(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        usuarioDAO = new UsuarioDAO(context);
        pessoaDAO = new PessoaDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        servicosUsuario = new ServicosUsuario(context);

    }
    //           refazer
    //public long cadastrarUser(String email, String senha, String nome, String sexo, String dataNasc, String cpf, String tipoSangue) throws Exception {
        //Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);
        //Pessoa verificarCpf = pessoaDAO.getPessoaByCpf(cpf);
        //dataNasc = FormataData.americano(dataNasc);

        //if(verificarEmail != null) {
            //throw new Exception("Email já cadastrado");
        //}
        //if (verificarCpf != null) {
                //throw new Exception("CPF já cadastrado");
        //}else{

            //long idUsuario = servicosUsuario.cadastrarUsuario(email, senha);
            //servicosPessoa.cadastrarPessoa(nome, sexo, dataNasc, cpf, idUsuario);
            //servicosPaciente.cadastrarPaciente(idUsuario, tipoSangue);

           // return idUsuario;
        //}
    //}

    //refazer
    //@SuppressLint("ApplySharedPref")
    //public void login(String email, String senha) throws Exception {
      //  Usuario usuario = usuarioDAO.getUsuarioByEmail(email);

        //if(usuario == null){
          //  throw new Exception("E-mail não cadastrado");
        //} else if(!senha.equals(usuario.getSenha())){
          //  throw new Exception("E-mail ou Senha incorretos");
        //}
        //SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putLong(ID_USER_PREFERENCES, usuario.getId());
        //editor.putString(LOGIN_PREFERENCES, usuario.getEmail());
        //editor.putString(PASSWORD_PREFERENCES, usuario.getSenha());
        //editor.putBoolean(IS_MEDICO_PREFERENCES, medico != null);
        //if ( medico != null){
          //  editor.putLong(ID_MEDICO_PREFERENCES, medico.getId()); // armazena o id do médico para  recuperar na criação das consultas.
        //}
        //else {
          //  editor.putLong(ID_PACIENTE_PREFERENCES, paciente.getId());  // armazena o id do paciênte para  recuperar na marcação das consultas.
        //}

        //editor.commit();
    //}

    public void atualizarPerfil(String email, String senha) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);

        if(verificarEmail != null) {
            throw new Exception("Email já cadastrado");
        } else {
            servicosUsuario.modificarUsuario(email, senha);
        }


    }
}

