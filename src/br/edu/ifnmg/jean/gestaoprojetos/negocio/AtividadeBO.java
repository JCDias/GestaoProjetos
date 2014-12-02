/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.ProjetoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.dados.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class AtividadeBO {
    
    //Valida campos
    public String validaDados(String nome, String projeto, String encarregado, int duracao){
        String mensagem = null;
        
        if(nome.length()<3){
            mensagem = "Nome muito curto! O nome deve conter no mínimo 3 caracteres.";
        }
        
        if(duracao==0){
            mensagem = "Duração deve ser maior que 0";
        }
        
        if(nome.isEmpty() || projeto.equals("Selecione") || encarregado.equals("Selecione")){
            mensagem = "Preencha todos os campos!";
        }
        return mensagem;
    }
    
    //Preencher combo box departamento
    public ArrayList<String> ComboBoxProjeto(String CodDepartamento) throws SQLException {
        
        ProjetoDAO projetoDAO = new ProjetoDAO();
        ArrayList<String> Projeto = new ArrayList<>();
        
        Projeto = projetoDAO.ComboBoxProjeto(CodDepartamento);
                
        return Projeto;
        
    }
    
    //Preencher combo box encarregado
    public ArrayList<String> ComboBoxEncarregadoPorDepartamento(String CodDepartamento) throws SQLException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<String> Encarregado = new ArrayList<>();

        Encarregado = usuarioDAO.ComboBoxEncarregadosPorDepartamento(CodDepartamento);

        return Encarregado;

    }
    
}
