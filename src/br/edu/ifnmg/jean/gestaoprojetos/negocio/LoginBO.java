/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.LoginDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.CamposVaziosException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.EmailInvalidoException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JEAN CARLOS
 */
public class LoginBO {

    public void validaDados(String email, String senha){
                
        if(validaEmail(email)==false){
            throw new EmailInvalidoException();
        }
        
        if(email.isEmpty() || senha.isEmpty()){
           throw new CamposVaziosException();
        }
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
