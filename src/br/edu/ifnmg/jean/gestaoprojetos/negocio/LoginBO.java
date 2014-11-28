/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.LoginDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JEAN CARLOS
 */
public class LoginBO {

    public String validaDados(String email, String senha){
        String mensagem = null;
        
        if(validaEmail(email)==false){
            mensagem = "E-mail inválido!";
        }
        
        if(email.isEmpty() || senha.isEmpty()){
            mensagem = "Preencha todos os campos!";
        }
        
        return mensagem;
    }
    
    public boolean validaEmail(String email) {
        
        // Validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    public Usuario Logar(String Email, String Senha) throws SQLException {

        Usuario userLogado = new Usuario();

        LoginDAO LoginDAO = new LoginDAO();

        userLogado = LoginDAO.selecionarLogin(Email, Senha);

        return userLogado;
    }
}
