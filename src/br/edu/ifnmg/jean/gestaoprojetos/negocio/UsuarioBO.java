/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.UsuarioDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JEAN CARLOS
 */
public class UsuarioBO {
    
    public String validaDados(String nome, String email, String senha, String connfirmaSenha, String departamento) {
        String mensagem = null;
        
        if(departamento.equals("Selecione")){
            mensagem = "Selecione um departamento!";
        }
        
        if (validaSenha(senha, connfirmaSenha)== false) {
            mensagem = "As senhas digitadas não conferem!!";
        }
        
        
        if (validaEmail(email)==false) {
            mensagem = "E-mail inválido";
        }
        
        
        if(nome.length()<3){
            mensagem = "Nome muito curto";
        } 
        
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || connfirmaSenha.isEmpty() || departamento.equals("Selecione")) {
            mensagem = "Prrencha todos os campos!";
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
        
        // Validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    //Vericar gerente
    public void validarGerente(Usuario Gerente) throws SQLException{
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario GerenteExistente = userDAO.selecionarGerente(Gerente.getNome(), Gerente.getCargo());

        //VERIFICA SE HA ALGUM GERENTE CADASTRADO COM O MESMO NOME
        if (GerenteExistente == null) {
            System.out.println("entrou no  if (GerenteExistente == null)");
            Usuario gerentePorDepartmento = userDAO.selecionarGerentePorDepartamento(Gerente.getDepartamento().getCodigo(), Gerente.getCargo());
            //VERIFICA SE JA EXISTE UM GERENTE CADASTRADO PARA O DEPARTAMENTO SELECIONADO
            if (gerentePorDepartmento == null) {
                System.out.println("entrou no if (gerentePorDepartmento == null)");
                userDAO.CadastrarUsuario(Gerente);
            }
        }

    }
    
    //Verifica encarregado
}
