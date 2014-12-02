/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.DepartamentoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.dados.ProjetoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Projeto;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author JEAN CARLOS
 */
public class ProjetoBO {
    
    public String validaDados(String nome, String descricao, String data_inicio, String data_terminio){
        String mensagem = null;
        
        if(nome.length()<3){
            mensagem = "Nome muito curto! O nome deve conter mais de 3 caracteres.";
        }
        
        if(data_inicio.length()<10){
            mensagem = "Formato data de início inválido\nPadrão adotado DD/MM/AAAA";
        }
        
         if(data_terminio.length()<10){
            mensagem = "Formato data terminio inválido\nPadrão adotado DD/MM/AAAA";
        }
        
        if(nome.isEmpty() || descricao.isEmpty() || (data_inicio.length()==2) || (data_terminio.length()==2)){
            mensagem = "Preencha todos os campos!";
        }
        
        return mensagem;
    }
    
    //Criar novo projeto
    public void criarProjeto(Projeto projeto) throws SQLException {
        
        ProjetoDAO projetDAO = new ProjetoDAO();
        projetDAO.cadastrarProjeto(projeto);
        
    }
    
    //Selecionar departamento por nome
    public Departamento selecionaDepartamentoPorNome(String nome_departamento) throws SQLException{
        
        Departamento departamento = new Departamento();
        
        DepartamentoDAO depDAO = new DepartamentoDAO(); 
        
        departamento = depDAO.selecionarDepartamentoPorNome(nome_departamento);
        
        return departamento;
    }
    
}
