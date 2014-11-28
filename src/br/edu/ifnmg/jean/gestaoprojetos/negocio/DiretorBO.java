/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.DiretorDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JEAN CARLOS
 */
public class DiretorBO {

    // Verificar se existe diretor cadastrado
    public Usuario selecionarDiretor() throws SQLException {

        DiretorDAO diretorDAO = new DiretorDAO();
        Usuario user = diretorDAO.selecionarDiretor();

        return user;

    }

    //Criar um diretor no BD
    public void criarDiretor(Usuario Diretor) throws SQLException {
        DiretorDAO diretorDAO = new DiretorDAO();

        Usuario user = diretorDAO.selecionarDiretor();

        if (user == null) {
            diretorDAO.criaDiretor(Diretor);

        }
    }

    //Valida campos diretor
    public String validaDados(String nome, String email, String senha, String connfirmaSenha){
        String mensagem = null;
        
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || connfirmaSenha.isEmpty()){
            mensagem = "Prrencha todos os campos!";
        }else if(validaEmail(email)){
           // continuar aki            
        }
        
        return mensagem;
    }
    
    //Validar se as senhas digitadas são iguais
    public boolean validaSenha(String senha, String confirmaSenha) {

        if (senha.equals(confirmaSenha)) {
            return true;
        }

        return false;
    }

    public boolean validaEmail(String email) {
        boolean retorno = false;
       // Validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            retorno = true;
        }
        return retorno;
    }
}
